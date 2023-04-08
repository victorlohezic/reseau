import java.util.ArrayList;

/**
 * Close the socket with the server
 **/
public class AddFishPrompt implements ParametersCommande {
    private static final AddFishPrompt ADD_FISH = new AddFishPrompt();
    private static Logging logging;
    private static Prompt prompt;
    private static Fish fish;
    private static AddFish addFishNetwork;
    private static View view;

    private AddFishPrompt() {
    }

    /*
     * Initialise AddFishPrompt but it needs to use the method setFish to add a Fish
     */
    public static AddFishPrompt initAddFish(AddFish networkCommande, View v, Logging log, Prompt p) {
        addFishNetwork = networkCommande;
        view = v;
        logging = log;
        prompt = p;
        return ADD_FISH;
    }

    /**
     * Cast a commande to AddFishPrompt commande
     * 
     * @param commande : Commande to cast
     * @return AddFishPrompt
     * @throws FishException
     * 
     */
    public static AddFishPrompt castCommandToFish(Commande commande) throws CommandeException {
        if (commande == ADD_FISH) {
            return ADD_FISH;
        } else {
            throw new CommandeException("The command which needs to be casted isn't an AddFishPrompt command.");
        }
    }

    /**
     * Launch the work of the commande
     * Before calling this method, you must call setFish in order to add a Fish
     **/
    public void execute() {
        addFishNetwork.setFish(fish);
        addFishNetwork.execute();
        if (addFishNetwork.getResult()) {
            try {
                view.addFish(fish);
                prompt.print("-> OK");
            } catch (FishViewException e) {
                logging.warning(e.getMessage());
            }
        } else {
            logging.warning("The fish wasn't added sucessfully");
        }
    }

    /**
     * This method sets the attribute fish to the @f in order to ask to the server
     * to add the good fish
     * It needs to call this method before execute
     * 
     * @param p : Parameters ArrayList<String>
     */
    public void setParameters(ArrayList<String> p) {
        String name = p.get(0);
        int[] coordinates = new int[] { Integer.parseInt(p.get(1)), Integer.parseInt(p.get(2)) };
        int[] size = new int[] { Integer.parseInt(p.get(3)), Integer.parseInt(p.get(4)) };
        String mobility = p.get(5);
        try {
            fish = new Fish(name, coordinates, size, mobility);
        } catch (FishException e) {
            logging.warning(e.getMessage());
        }
    }

}