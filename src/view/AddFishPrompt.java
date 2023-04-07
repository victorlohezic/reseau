import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;

/** 
 * Close the socket with the server
**/
public class AddFishPrompt implements Commande {
    private static final AddFishPrompt ADD_FISH = new AddFishPrompt();
    private static Logging logging;
    private static BufferedReader input; 
    private static PrintWriter output;
    private static ArrayList<String> parameters; 
    private static AddFish addFishNetwork;
    private static View view;

    private AddFishPrompt(){
    }

    /*
     * Initialise AddFishPrompt but it needs to use the method setFish to add a Fish
     */
    public static AddFishPrompt initAddFish(AddFish networkCommande, ArrayList<String> p, View v) {
        addFishNetwork = networkCommande;
        parameters = p;
        view = v;
        return ADD_FISH;
    }

    /**
     * Cast a commande to AddFishPrompt commande
     * @param commande : Commande to cast
     * @return  AddFishPrompt
     * @throws FishException 
     * 
     */
    public static AddFishPrompt castCommandToFish(Commande commande) throws CommandeException {
        if (commande == ADD_FISH) {
            return ADD_FISH;
        } else {
            throw new CommandeException("La commande à caster n'est pas un AddFishPrompt");
        }
    }

    /** 
     * Launch the work of the commande
     * Before calling this method, you must call setFish in order to add a Fish
    **/
    public void execute() {
        try {
            Fish fish = new Fish("Chouchou", new int[]{0, 0}, new int[]{2, 4}, "RandomPathWay");
            addFishNetwork.setFish(fish);
            addFishNetwork.execute();
            if (addFishNetwork.getResult()) {
                try {
                    view.addFish(fish);
                } catch (FishViewException e){
                    logging.warning(e.getMessage());
                }
            } else {
                logging.warning("Le poisson n'a pas ajouté avec succès.");
            }
        } catch (FishException e) {
            logging.warning(e.getMessage());
        }
    }   

    /**
     * This method sets the attribute fish to the @f  in order to ask to the server to add the good fish
     * It needs to call this method before execute
     * @param p : Parameters ArrayList<String>
     */
    public void setParameters(ArrayList<String> p) {
        parameters = p;
    }

}