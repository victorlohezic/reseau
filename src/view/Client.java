import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Client {
    private Logging logging;
    private Prompt prompt; 
    private Socket socket;
    private HashMap<String, Commande> promptCommand = new HashMap<>();  
    private HashMap<String, Commande> networkCommand = new HashMap<>();  
    private View view;
    private int displayTimeOutValue;

    private void initNetwork() {
        /* To implement */
        return;
    }

    private void closeNetwork() {
        /* To implement */
        return;
    }

    public static void main(String[] argv) {
        try {
            int port = Integer.parseInt(argv[1]);
            Socket s = new Socket(argv[0], port);
            System.out.println("SOCKET = " + s);

            BufferedReader plec = new BufferedReader(new InputStreamReader(s.getInputStream()));

            PrintWriter pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);

            Status status = Status.initStatus(plec, pred);
            status.execute();

            System.out.println("END");
            pred.println("END");
            plec.close();
            pred.close();
            s.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
