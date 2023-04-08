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
        this.displayTimeoutValue = config.getDisplayTimeoutValue();

        this.prompt = new Prompt(scanner);
        try {
            this.view = new View(this.id, new int[]{0, 0}, new int[]{100, 100});
        } catch (Exception e) {
            logging.warning(e.getMessage());
        }
    }



    private void initNetwork() {
            try{
            String address = this.controllerAddress; // get controllerAddress ( /!\ CAST IN INT BCAUSE IT'S A STR)
            int port = this.controllerPort; // get controllerPort 
            this.socket = new Socket(address, port); // create socket with @ip and port
            logging.info("SOCKET = " + this.socket);

            this.plec = new BufferedReader(new InputStreamReader(this.socket.getInputStream())); // Initialize data flow allowing to read what is sent by server

            this.pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())), true); // Initialize data flow allowing to write to server

            // Status status = Status.initStatus(plec, pred);
            // status.execute();
            networkCommands.put("log out", LogOut.initLogOut(plec, pred, socket, logging));
            networkCommands.put("AddFish", AddFish.initAddFish(plec, pred, logging));
            networkCommands.put("Hello", Hello.initHello(plec, pred, logging));
            networkCommands.put("ping", Ping.initPing(plec, pred, logging, socket, controllerPort, displayTimeoutValue));
            networkCommands.put("getFishes", getFishes.initGetFishes(plec, pred, logging));
            networkCommands.put("DelFish", DelFish.initDelFish(plec, pred, logging));
            networkCommands.put("ls", Ls.initLs(plec, pred, logging, prompt));
            Hello.castCommandToHello(networkCommands.get("Hello")).execute();

            return;
            } catch (IOException e) {
                logging.debug(e.getMessage());
            } catch (CommandeException e) {
                logging.warning(e.getMessage());
            }
    }

    /*
     * Initialize the promp commands with network commands
     */
    public void initPromptCommands() {
        try{
            promptCommands.put("addFish", AddFishPrompt.initAddFish(AddFish.castCommandToFish(networkCommands.get("AddFish")), view, logging, prompt));
            promptCommands.put("delFish", DelFishPrompt.initDelFish(DelFish.castCommandToFish(networkCommands.get("DelFish")), view, logging, prompt));
            promptCommands.put("status", Status.initStatus(Ping.castCommandToPing(networkCommands.get("ping")), logging, prompt));
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
            } else {
                logging.warning("NOK : commande introuvable");
            }
        } while (!result.get(0).equals("quit"));        
    }   

    private void closeNetwork() {
        try {
            networkCommands.get("log out").execute();
        } catch (CommandeException e) {
            logging.warning(e.getMessage());
        }
        scanner.close();
    }

    public static void main(String[] argv) {
         Client client = new Client();
         client.initNetwork();
         client.initPromptCommands();
         client.run();
         client.closeNetwork();
     }
}
