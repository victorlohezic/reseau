import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;


public class Ls implements Commande {
    private static final Ls Ls = new Ls();
    private static Logging logging;
    private static BufferedReader input; 
    private static PrintWriter output;
    private static Prompt P = new Prompt();
    private static ArrayList<String> result; 

    private Ls(){}

    public static Ls initLs(BufferedReader in, PrintWriter out, Logging log, Prompt prompt) {
        input = in;
        output = out;
        logging = log;
        P = prompt;
        return Ls;
    }

    /**
     * Cast a command to Ls command
     * @param command : Command to cast
     * @return  AddFish
     * @throws LsExeception 
     * 
     */
    public static Ls castCommandToLs(Commande command) throws CommandeException {
        if (command == Ls) {
            return Ls;
        } else {
            throw new CommandeException("La commande à caster n'est pas un Ls");
        }
    }

    public void execute(){
        logging.info("ls");
        String answer;
        try {
            output.println("ls");
            while ((answer = input.readLine()) != null ) {
                logging.info((answer));   
                result.addAll(P.parse(answer,"["));
            }
        }

        catch (IOException e) {
            logging.debug(e.getMessage());
        }
    }

    public ArrayList<String> getResult(){
        return result;
    }
}
