import java.util.ArrayList;

/** 
 * Commande from the view to interect with the controller 
**/
interface ParametersCommande extends Commande {
    /** 
     * Change the parameters  @p used by the commande
     * @p : ArrayList<String>
    **/
    public void setParameters(ArrayList<String> p);
}
