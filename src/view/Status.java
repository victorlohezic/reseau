import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

/** 
 * Give information about the state of the aquarium  
**/
public class Status implements Commande {
    private static final Status STATUS = new Status();
    private static Logging logging;
    private static BufferedReader input; 
    private static PrintWriter output;

    private Status(){
    }

    /*
     * Initialise Status command and return this command
     */
    public static Status initStatus(BufferedReader in, PrintWriter out, Logging log) {
        input = in;
        output = out;
        logging = log;
        return STATUS;
    }

    /** 
     * Launch the work of the commande
    **/
    public void execute() {
        output.println("status");
        try {
            String answer = input.readLine();
            logging.info(answer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ;
    }

}