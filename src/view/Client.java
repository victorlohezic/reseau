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
        ConfigParser config = new ConfigParser("view.cfg");

        this.id = config.getId();
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
            System.out.println("SOCKET = " + this.socket);

            this.plec = new BufferedReader(new InputStreamReader(this.socket.getInputStream())); // Initialize data flow allowing to read what is sent by server

            this.pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())), true); // Initialize data flow allowing to write to server

            Status status = Status.initStatus(plec, pred);
            status.execute();
            return;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
    }

    private void closeNetwork() {
        try{
            System.out.println("END"); // End of communication
            this.pred.println("END"); // Indicate end of communication to server 
            this.plec.close(); // close reading flow
            this.pred.close(); // close wrinting flow
            this.socket.close(); // close socket
            return;
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // public static void main() {
    //     try {
           

    //     } catch (IOException e) {
    //         System.out.println(e.getMessage());
    //     }
    // }
}
