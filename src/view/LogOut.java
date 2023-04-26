import java.io.PrintWriter;
import java.net.Socket;

/** 
 * Close the socket with the server
**/
public class LogOut implements Commande {
    private static final LogOut LOGOUT = new LogOut();
    private static Logging logging;
    private static Listener input; 
    private static PrintWriter output;
    private static Socket socket;

    private LogOut(){
    }

    /*
     * Initialise Status command and return this command
     */
    public static LogOut initLogOut(Listener in, PrintWriter out, Socket s, Logging log) {
        input = in;
        output = out;
        logging = log;
        socket = s;
        return LOGOUT;
    }

    /** 
     * Launch the work of the commande
    **/
    public void execute() {
        logging.info("log out");
        try{
            String answer = "";
            output.println("log out"); // Indicate end of communication to server 
            answer = input.readLine();
            if (answer.equals("bye")) {
                logging.info(answer);
            } else {
                logging.warning("Le client n'a pas répondu bye");
            }
            output.close(); // close wrinting flow
            socket.close(); // close socket
            return;
        }catch (Exception e) {
            logging.debug(e.getMessage());
        }
    }

}