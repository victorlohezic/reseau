/**
 * Exception when we a add to a view a fish with a position out of the view
 */
public class FishException extends Exception {

    public FishException(String errorMessage) {
        super(errorMessage);
    }
}
