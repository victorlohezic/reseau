import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Client {
    private Logging logging;
    private Prompt prompt; 
    private HashMap<String, Commande> promptCommand = new HashMap<>();  
    private HashMap<String, Commande> networkCommand = new HashMap<>();  
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
            networkCommand.put("log out", LogOut.initLogOut(plec, pred, socket, logging));
            networkCommand.put("AddFish", AddFish.initAddFish(plec, pred, logging));
            networkCommand.put("Hello", Hello.initHello(plec, pred, logging));
            networkCommand.put("ping", Ping.initPing(plec, pred, logging,controllerPort));
            networkCommand.put("getFishes", getFishes.initGetFishes(plec, pred, logging));
            networkCommand.put("DelFish", DelFish.initDelFish(plec, pred, logging));
            Hello.castCommandToHello(networkCommand.get("Hello")).execute();

            return;
            } catch (IOException e) {
                logging.debug(e.getMessage());
            } catch (CommandeException e) {
                logging.warning(e.getMessage());
            }
    }

    /**
     * Run functional test
     */
    private void run() {
        try {
            Fish fish = new Fish("Chouchou", new int[]{0, 0}, new int[]{2, 3}, "RandomPathWay");
            AddFish.castCommandToFish(networkCommand.get("AddFish")).setFish(fish);
            DelFish.castCommandToFish(networkCommand.get("DelFish")).setFish(fish);
            networkCommand.get("AddFish").execute();
            networkCommand.get("DelFish").execute();
            networkCommand.get("ping").execute();
            networkCommand.get("getFishes").execute();
        } catch (CommandeException e) {
            logging.warning(e.getMessage());
        } catch (FishException e) {
            logging.warning(e.getMessage());
        }
    }   

    private void closeNetwork() {
        try {
            networkCommand.get("log out").execute();
        } catch (CommandeException e) {
            logging.warning(e.getMessage());
        }
    }

    public static void main(String[] argv) {
         Client client = new Client();
         client.initNetwork();
         client.run();
         client.closeNetwork();
     }
}
