/**
 * Exception when we a add to a view a fish with a position out of the view
 */
public class FishViewException extends Exception {

    public FishViewException(String errorMessage) {
        super(errorMessage);
    }
}
