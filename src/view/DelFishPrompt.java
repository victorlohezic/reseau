import java.util.ArrayList;

/**
 * Close the socket with the server
 **/
public class DelFishPrompt implements ParametersCommande {
    private static final DelFishPrompt DEL_FISH = new DelFishPrompt();
    private static Logging logging;
    private static String nameFish;
    private static DelFish delFishNetwork;
    private static View view;
    private static Prompt prompt;

    private DelFishPrompt() {
    }

    /*
     * Initialise DelFishPrompt but it needs to use the method setFish to add a Fish
     */
    public static DelFishPrompt initDelFish(DelFish networkCommande, View v, Logging log, Prompt p) {
        delFishNetwork = networkCommande;
        logging = log;
        view = v;
        prompt = p;
        return DEL_FISH;
    }

    /**
     * Cast a commande to DelFishPrompt commande
     * 
     * @param commande : Commande to cast
     * @return DelFishPrompt
     * @throws FishException
     * 
     */
    public static DelFishPrompt castCommandToFish(Commande commande) throws CommandeException {
        if (commande == DEL_FISH) {
            return DEL_FISH;
        } else {
            throw new CommandeException("La commande à caster n'est pas un DelFishPrompt");
        }
    }

    /**
     * Launch the work of the commande
     * Before calling this method, you must call setFish in order to add a Fish
     **/
    public void execute() {
        delFishNetwork.setFish(nameFish);
        delFishNetwork.execute();
        if (delFishNetwork.getResult()) {
            try {
                Fish fishToRemove = view.getFish(nameFish);
                view.removeFish(fishToRemove);
                prompt.print("-> OK");
            } catch (FishViewException e) {
                logging.warning(e.getMessage());
                prompt.print("-> NOK : Poisson Inexistant");

            }
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