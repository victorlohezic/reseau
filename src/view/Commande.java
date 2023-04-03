/** 
 * Commande from the view to interect with the controller 
**/
interface Commande {
    /** 
     * Launch the work of the commande
    **/
    public void execute() throws CommandeException;
}
