import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

public class getFishes implements Commande{

    private static final getFishes GET_FISHES = new getFishes();
    private static Logging logging;
    private static BufferedReader input;
    private static PrintWriter output;

    private getFishes() {}

    public static getFishes initGetFishes(BufferedReader in, PrintWriter out, Logging log) {
        input = in;
        output = out;
        logging = log;
        return GET_FISHES;
    }

    public void execute() {

        logging.info("getFishes");
        try {
            output.println("getFishes");
            String answer = input.readLine();
                logging.info((answer));
        }

        catch (IOException e) {
            logging.debug(e.getMessage());
        }
    }

}