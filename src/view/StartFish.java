import java.util.ArrayList;
import java.io.PrintWriter;

/**
 * Close the socket with the server
 **/
public class StartFish implements ParametersCommande {
    private static final StartFish START_FISH = new StartFish();
    private static Logging logging;
    private static String nameFish;
    private static View view;
    private static Prompt prompt;
    private static PrintWriter output;
    private static Listener input; 

    private StartFish() {
    }

    /*
     * Initialise StartFish but it needs to use the method setFish to add a Fish
     */
    public static StartFish initStartFish(View v, Logging log, Prompt p, PrintWriter out, Listener in) {
        logging = log;
        view = v;
        prompt = p;
        output = out;
        input = in;
        return START_FISH;
    }

    /**
     * Cast a commande to StartFish commande
     * 
     * @param commande : Commande to cast
     * @return StartFish
     * @throws FishException
     * 
     */
    public static StartFish castCommandToFish(Commande commande) throws CommandeException {
        if (commande == START_FISH) {
            return START_FISH;
        } else {
            throw new CommandeException("La commande à caster n'est pas un StartFish");
        }
    }

    /**
     * Launch the work of the commande
     * Before calling this method, you must call setFish in order to add a Fish
     **/
    public void execute() {
        try{
            Fish fish = view.getFish(nameFish);
            fish.setState(State.STARTED);
            output.println("startFish " + fish.getName());
            logging.info("startFish " + fish.getName());
            String answer = input.readLine();
            if (answer.equals("OK")) {
                logging.info(answer);
            } else {
                logging.warning(answer);
            }
        } catch (Exception e) {
            logging.warning(e.getMessage());
        }
    }

    /**
     * This method sets the attribute nameFish to the first element of ArrayList<String> p
     * to del the good fish
     * It needs to call this method before execute
     * 
     * @param p : Parameters ArrayList<String>
     */
    public void setParameters(ArrayList<String> p) {
        nameFish = p.get(0);
    }

}