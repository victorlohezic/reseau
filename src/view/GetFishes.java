import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

public class GetFishes implements Commande{

    private static final GetFishes GET_FISHES = new GetFishes();
    private static Logging logging;
    private static BufferedReader input;
    private static PrintWriter output;
    private static String result;

    private GetFishes() {}

    

    public static GetFishes initGetFishes(BufferedReader in, PrintWriter out, Logging log) {
        input = in;
        output = out;
        logging = log;
        return GET_FISHES;
    }

    /**
     * Cast a commande to GetFishes commande
     * @param commande : Commande to cast
     * @return  GetFishes
     * @throws FishException 
     * 
     */
    public static GetFishes castCommandToFish(Commande commande) throws CommandeException {
        if (commande == GET_FISHES) {
            return GET_FISHES;
        } else {
            throw new CommandeException("La commande à caster n'est pas un GetFishes");
        }
    }

    public void execute() {

        logging.info("getFishes");
        try {
            output.println("getFishes");
            String answer = input.readLine();
            result = answer;
            logging.info((answer));
        }

        catch (IOException e) {
            logging.debug(e.getMessage());
        }
    }

    /**
     * This method returns the @result 
     * You must call this method after calling the execute method
     * @return String
     */
    public String getResult() {
        return result;
    }

}