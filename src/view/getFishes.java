import java.io.BufferedReader;
import java.io.IOException;

public class getFishes {

    private static final getFishes GET_FISHES = new getFishes();
    private static Logging logging;
    private static BufferedReader input;

    private getFishes() {}

    public static getFishes initGetFishes(BufferedReader in, Logging log) {
        input = in;
        logging = log;
        return GET_FISHES;
    }

    public void execute() {

        logging.info("getFishes");
        try {
            String answer = input.readLine();
                logging.info((answer));
        }

        catch (IOException e) {
            logging.debug(e.getMessage());
        }
    }

}