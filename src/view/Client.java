import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Client {
    private Logging logging;
    private Scanner scanner = new Scanner(System.in);;
    private Prompt prompt; 
    private HashMap<String, ParametersCommande> promptCommands = new HashMap<>();  
    private HashMap<String, Commande> networkCommands = new HashMap<>();  
    private View view;

    /* Socket Attributes */
    private Socket socket;
    BufferedReader plec;
    Listener listener;
    PrintWriter pred;

    /*Config data*/ 
    private String id;
    private String controllerAddress;
    private int controllerPort;
    private String resources;
    private int displayTimeoutValue;


    public Client(){
        this.logging = new Logging("view.log");
        this.logging.enableSaving(false);
        this.logging.enable(true, true, true);
        
        ConfigParser config = new ConfigParser("build/view.cfg");

        this.id = config.getId();
        logging.info(this.id);
        this.controllerAddress = config.getControllerAddress();
        this.controllerPort = config.getControllerPort();
        this.resources = config.getResources();
        this.displayTimeoutValue = config.getDisplayTimeoutValue()*1000;
        this.prompt = new Prompt(scanner);
        try {
            this.view = new View(this.id, new int[]{0, 0}, new int[]{100, 100});
        } catch (Exception e) {
            logging.warning(e.getMessage());
        }
    }



    private boolean initNetwork() {
            try{
            String address = this.controllerAddress; // get controllerAddress ( /!\ CAST IN INT BCAUSE IT'S A STR)
            int port = this.controllerPort; // get controllerPort 
            this.socket = new Socket(address, port); // create socket with @ip and port
            logging.info("SOCKET = " + this.socket);

            this.plec = new BufferedReader(new InputStreamReader(this.socket.getInputStream())); // Initialize data flow allowing to read what is sent by server
            this.listener = new Listener(plec, prompt, view, logging);
            this.listener.start();
            this.pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())), true); // Initialize data flow allowing to write to server

            // Status status = Status.initStatus(plec, pred);
            // status.execute();
            networkCommands.put("log out", LogOut.initLogOut(listener, pred, socket, logging));
            networkCommands.put("AddFish", AddFish.initAddFish(listener, pred, logging));
            networkCommands.put("Hello", Hello.initHello(listener, pred, logging));
            networkCommands.put("getFishesContinuously", GetFishesContinuously.initGetFishesContinuously(pred, logging));
            networkCommands.put("ping", Ping.initPing(listener, pred, logging, socket, controllerPort, displayTimeoutValue));
            networkCommands.put("getFishes", GetFishes.initGetFishes(listener, pred, logging));
            networkCommands.put("DelFish", DelFish.initDelFish(listener, pred, logging));
            networkCommands.put("ls", Ls.initLs(listener, pred, logging, prompt));
            Hello.castCommandToHello(networkCommands.get("Hello")).execute();
            GetFishesContinuously.castCommandToFish(networkCommands.get("getFishesContinuously")).execute();;
            // GetFishesContinuously getFishesContinuously = GetFishesContinuously.initGetFishes();
            // networkCommands.put("getFishesContinuously", getFishesContinuously);
            // getFishesContinuously.execute();

            return true;
            } catch (IOException e) {
                logging.debug(e.getMessage());
                return false;
            } catch (CommandeException e) {
                logging.warning(e.getMessage());
                return false;
            }
    }

    /*
     * Initialize the promp commands with network commands
     */
    public void initPromptCommands() {
        try{
            promptCommands.put("addFish", AddFishPrompt.initAddFish(AddFish.castCommandToFish(networkCommands.get("AddFish")), view, logging, prompt));
            promptCommands.put("delFish", DelFishPrompt.initDelFish(DelFish.castCommandToFish(networkCommands.get("DelFish")), view, logging, prompt));
            promptCommands.put("status", Status.initStatus(Ping.castCommandToPing(networkCommands.get("ping")), GetFishes.castCommandToFish(networkCommands.get("getFishes")), logging, prompt));
            promptCommands.put("startFish", StartFish.initStartFish(view, logging, prompt));
        } catch (CommandeException e) {
            logging.warning(e.getMessage());
        }
    }

    /**
     * Run functional tests
     */
    private void run_functional_tests() {
        try {
            Fish fish = new Fish("Chouchou", new int[]{0, 0}, new int[]{2, 3}, "RandomPathWay");
            AddFish.castCommandToFish(networkCommands.get("AddFish")).setFish(fish);
            DelFish.castCommandToFish(networkCommands.get("DelFish")).setFish(fish.getName());
            networkCommands.get("AddFish").execute();
            networkCommands.get("DelFish").execute();
            networkCommands.get("ping").execute();
            networkCommands.get("getFishes").execute();
            Ls.castCommandToLs(networkCommands.get("ls")).getResult();
        } catch (CommandeException e) {
            logging.warning(e.getMessage());
        } catch (FishException e) {
            logging.warning(e.getMessage());
        }
    }   

    /**
     * Run
     */
    private void run() {
        ArrayList<String> result;
        do {
            result = prompt.read();
            ParametersCommande promptCommand = promptCommands.get(result.get(0));
            if (promptCommand != null) {
                int resultSize = result.size();
                if (resultSize > 0) {
                    ArrayList<String> parameters = new ArrayList<String>(result.subList(1, resultSize));
                    promptCommand.setParameters(parameters);
                }
                try {
                    promptCommand.execute();
                } catch(Exception e) {
                    logging.warning(e.getMessage());
                }  
            } else if (result.get(0).equals("quit") == false) {
                logging.warning("NOK : commande introuvable");
            }
        } while (!result.get(0).equals("quit"));        
    } 
    
    /**
     * Run GUI
     */
    private void runGUI(){
        try {
        Fish fish = new Fish("Chouchou", new int[]{0, 0}, new int[]{2, 3}, "RandomPathWay");
        Fish fish2 = new Fish("SmileyFleur", new int[]{0, 0}, new int[]{10, 50}, "RandomPathWay");
        AddFish.castCommandToFish(networkCommands.get("AddFish")).setFish(fish);
        AddFish.castCommandToFish(networkCommands.get("AddFish")).execute();
        Thread.sleep(1000);
        AddFish.castCommandToFish(networkCommands.get("AddFish")).setFish(fish2);
        AddFish.castCommandToFish(networkCommands.get("AddFish")).execute();
        } catch (Exception e) {
            logging.warning(e.getMessage());
        }
        run();
    }

    private void closeNetwork() {
        this.listener.stopRunning();
        try {
            // GetFishesContinuously getFishesContinuously = GetFishesContinuously.castCommandToFish(networkCommands.get("getFishesContinuously"));
            // getFishesContinuously.stopRunning();
            networkCommands.get("log out").execute();
        } catch (CommandeException e) {
            logging.warning(e.getMessage());
        }
        scanner.close();
    }

    public static void main(String[] argv) {
         Client client = new Client();
         if (client.initNetwork()){
             client.initPromptCommands();
             //client.run();
             client.runGUI();
             client.closeNetwork();
         }
     }
}
