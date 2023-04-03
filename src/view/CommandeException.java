/**
 * Exception when we a add to a view a fish with a position out of the view
 */
public class CommandeException extends Exception {

    public CommandeException(String errorMessage) {
        super(errorMessage);
    }
}
