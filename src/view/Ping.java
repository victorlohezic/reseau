import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;


public class Ping implements Commande {
    private static final Ping PING = new Ping();
    private static Logging logging;
    private static BufferedReader input;
    private static PrintWriter output;
    private static int port;

    private Ping() {}

    public static Ping initPing(BufferedReader in, PrintWriter out, Logging log, int controllerPort) {
        input = in;
        output = out;
        logging = log;
        port = controllerPort;
        return PING;
    }

    public void execute() {

        logging.info("ping " + port );
        try {
            output.println("ping "+ port);
            String answer = input.readLine();
                logging.info((answer));
        }

        catch (IOException e) {
            logging.debug(e.getMessage());
        }
    }

}
