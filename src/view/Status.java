import java.io.BufferedReader;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.io.IOException;

/** 
 * Give information about the state of the aquarium  
**/
public class Status implements Commande {
    private static final Status STATUS = new Status();
    private static final Prompt PROMPT = new Prompt();
    private static BufferedReader input; 
    private static PrintWriter output;

    private Status(){
    }

    /*
     * Initialise Status command and return this command
     */
    public static Status initStatus(BufferedReader in, PrintWriter out) {
        input = in;
        output = out;
        return STATUS;
    }

    /** 
     * Launch the work of the commande
    **/
    public void execute() {
        output.println("status");
        try {
            String answer = input.readLine();
            PROMPT.print(answer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ;
    }

}