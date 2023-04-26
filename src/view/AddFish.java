import java.io.PrintWriter;

/** 
 * Close the socket with the server
**/
public class AddFish implements Commande {
    private static final AddFish ADD_FISH = new AddFish();
    private static Logging logging;
    private static Listener input; 
    private static PrintWriter output;
    private static Fish fish; 
    private static boolean result = false;

    private AddFish(){
    }

    /*
     * Initialise AddFish but it needs to use the method setFish to add a Fish
     */
    public static AddFish initAddFish(Listener in, PrintWriter out, Logging log) {
        input = in;
        output = out;
        logging = log;
        return ADD_FISH;
    }

    /**
     * Cast a commande to AddFish commande
     * @param commande : Commande to cast
     * @return  AddFish
     * @throws FishException 
     * 
     */
    public static AddFish castCommandToFish(Commande commande) throws CommandeException {
        if (commande == ADD_FISH) {
            return ADD_FISH;
        } else {
            throw new CommandeException("La commande à caster n'est pas un AddFish");
        }
    }

    /** 
     * Launch the work of the commande
     * Before calling this method, you must call setFish in order to add a Fish
    **/
    public void execute() {
        if (fish == null) {
            logging.warning("Aucun poisson n'a été ajouté en paramètre");
        }
        String commandValue = String.format("addFish %s at %dx%d, %dx%d, %s", 
        fish.getName(), fish.getPosition()[0], fish.getPosition()[1], 
        fish.getSize()[0], fish.getSize()[1], fish.getMobility());

        logging.info(commandValue);
        try{
            String answer = "";
            output.println(commandValue); // Indicate end of communication to server 
            answer = input.readLine();
            if (answer.equals("OK")) {
                logging.info(answer);
                result = true;
            } else {
                logging.warning(answer);
            }
            return;
        }catch (Exception e) {
            logging.debug(e.getMessage());
        }
    }

    /**
     * This method sets the attribute fish to the @f  in order to ask to the server to add the good fish
     * It needs to call this method before execute
     * @param f : Fish
     */
    public void setFish(Fish f) {
        fish = f;
    }

    /**
     * This methods return true if the fish is added else false 
     * If the result is true after the call of this method the attribute is false
     */
    public boolean getResult() {
        boolean currentResult = result;
        if (result == true) {
            result = false;
        }
        return currentResult;
    }

}