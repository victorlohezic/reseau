import java.io.PrintWriter;

/** 
 * Close the socket with the server
**/
public class DelFish implements Commande {
    private static final DelFish DEL_FISH = new DelFish();
    private static Logging logging;
    private static Listener input; 
    private static PrintWriter output;
    private static String nameFish; 
    private static boolean result = false;

    private DelFish(){
    }

    /*
     * Initialise DelFish but it needs to use the method setFish to add a Fish
     */
    public static DelFish initDelFish(Listener in, PrintWriter out, Logging log) {
        input = in;
        output = out;
        logging = log;
        return DEL_FISH;
    }

    /**
     * Cast a commande to DelFish commande
     * @param commande : Commande to cast
     * @return  DelFish
     * @throws FishException 
     * 
     */
    public static DelFish castCommandToFish(Commande commande) throws CommandeException {
        if (commande == DEL_FISH) {
            return DEL_FISH;
        } else {
            throw new CommandeException("La commande à caster n'est pas un DelFish");
        }
    }

    /** 
     * Launch the work of the commande
     * Before calling this method, you must call setFish in order to add a Fish
    **/
    public void execute()  {
        if (nameFish == "") {
            logging.warning("Aucun poisson n'a été ajouté en paramètre");
        }
        String commandValue = String.format("delFish %s", nameFish);

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
                result = false;
            }
            return;
        }catch (Exception e) {
            logging.debug(e.getMessage());
        }
    }

    /**
     * This method sets the attribute fish to the @f  in order to ask to the server to remove this fish
     * It needs to call this method before execute
     * @param f : Fish
     */
    public void setFish(String name) {
        nameFish = name;
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