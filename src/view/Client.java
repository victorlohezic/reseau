import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Client {
    private Logging logging;
    private Scanner scanner = new Scanner(System.in);;
    private Prompt prompt; 
    private HashMap<String, ParametersCommande> promptCommands = new HashMap<>();  
    private HashMap<String, Commande> networkCommands = new HashMap<>();  
    private View view;

    /* Socket Attributes */
    private Socket socket;
    private BufferedReader plec;
    private Listener listener;
    private PrintWriter pred;

    /*Config data*/ 
    private String id;
    private String controllerAddress;
    private int controllerPort;
    private String resources;
    private int displayTimeoutValue;


    public Client(String configFileName){
        ConfigParser config = new ConfigParser(configFileName);
        initClient(config);

    }

    public Client(){
        ConfigParser config = new ConfigParser("build/view.cfg");
        initClient(config);
    }

    private void initClient(ConfigParser config){
        
        this.logging = new Logging("view.log");
        this.logging.enableSaving(true);
        this.logging.enable(true, false, false);
        
        this.id = config.getId();
        logging.info(this.id);
        this.controllerAddress = config.getControllerAddress();
        this.controllerPort = config.getControllerPort();
        this.resources = config.getResources();
        this.displayTimeoutValue = config.getDisplayTimeoutValue()*1000;
        this.prompt = new Prompt(scanner);
        try {
            this.view = new View(this.id, new int[]{0, 0}, new int[]{100, 100}, resources);
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
            Hello.castCommandToHello(networkCommands.get("Hello")).execute(id);
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
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    networkCommands.get("ping").execute();
                } catch(Exception e) {
                    logging.warning(e.getMessage());
                }
            }
        };

        timer.schedule(task, 0, displayTimeoutValue);

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
        
        // Stop the timer
        timer.cancel();
    } 
    
    /**
     * Run GUI
     */
    private void runGUI(){
        try {
        Fish fish = new Fish("chouchou", new int[]{20, 90}, new int[]{20, 10}, "RandomWayPoint");
        ArrayList<String> fishArrayList= new ArrayList<String>();
        fishArrayList.add(fish.getName());
        fishArrayList.add(String.format("%d", fish.getPosition()[0]));
        fishArrayList.add(String.format("%d", fish.getPosition()[1]));
        fishArrayList.add(String.format("%d", fish.getSize()[0]));
        fishArrayList.add(String.format("%d", fish.getSize()[1]));
        fishArrayList.add(fish.getMobility());
        Fish fish2 = new Fish("toutou", new int[]{20, 11}, new int[]{22, 12}, "RandomWayPoint");
        ArrayList<String> fishArrayList2= new ArrayList<String>();
        fishArrayList2.add(fish2.getName());
        fishArrayList2.add(String.format("%d", fish2.getPosition()[0]));
        fishArrayList2.add(String.format("%d", fish2.getPosition()[1]));
        fishArrayList2.add(String.format("%d", fish2.getSize()[0]));
        fishArrayList2.add(String.format("%d", fish2.getSize()[1]));
        fishArrayList2.add(fish2.getMobility());
        AddFishPrompt.castCommandToFish(promptCommands.get("addFish")).setParameters(fishArrayList);
        AddFishPrompt.castCommandToFish(promptCommands.get("addFish")).execute();
        Thread.sleep(1000);
        AddFishPrompt.castCommandToFish(promptCommands.get("addFish")).setParameters(fishArrayList2);;
        AddFishPrompt.castCommandToFish(promptCommands.get("addFish")).execute();
        } catch (Exception e) {
            logging.warning(e.getMessage());
        }
        run_functional_tests();
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
        Client client;
        if (argv.length == 5){
            client = new Client(argv[4]);
        } 
        else{
            client = new Client();
        }
        if (client.initNetwork()){
            client.initPromptCommands();
            client.run();
            //client.runGUI();
            client.closeNetwork();
        }
     }
}
