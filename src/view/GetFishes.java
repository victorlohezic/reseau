import java.io.PrintWriter;
import java.io.IOException;

public class GetFishes implements Commande{

    private static final GetFishes GET_FISHES = new GetFishes();
    private static Logging logging;
    private static Listener input;
    private static PrintWriter output;
    private static String result;

    private GetFishes() {}

    

    public static GetFishes initGetFishes(Listener in, PrintWriter out, Logging log) {
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
            String answer = input.getFishes();
            result = answer;
            logging.info((answer));
        }

        catch (Exception e) {
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