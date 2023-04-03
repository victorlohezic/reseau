import java.io.BufferedReader;
import java.io.IOException;


public class Ping {
    private static final Ping PING = new Ping();
    private static Logging logging;
    private static BufferedReader input;
    private static int port;

    private Ping() {}

    public static Ping initPing(BufferedReader in, Logging log, int controllerPort) {
        input = in;
        logging = log;
        port = controllerPort;
        return PING;
    }

    public void execute() {

        logging.info("ping" + port );
        try {
            String answer = input.readLine();
                logging.info((answer));
        }

        catch (IOException e) {
            logging.debug(e.getMessage());
        }
    }

}
