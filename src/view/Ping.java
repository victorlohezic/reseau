import java.io.PrintWriter;
import java.net.Socket;


public class Ping implements Commande {
    private static final Ping PING = new Ping();
    private static Logging logging;
    private static Listener input;
    private static PrintWriter output;
    private static int port;
    private static Socket socket;
    private static boolean result = false;
    private static int displayTimeoutValue;

    private Ping() {}

    /**
     * Cast a commande to Pingcommande
     * 
     * @param commande : Commande to cast
     * @return Ping
     * @throws CommandeException
     * 
     */
    public static Ping castCommandToPing(Commande commande) throws CommandeException {
        if (commande == PING) {
            return PING;
        } else {
            throw new CommandeException("La commande à caster n'est pas un Ping");
        }
    }

    public static Ping initPing(Listener in, PrintWriter out, Logging log, Socket s, int controllerPort, int timeoutValue) {
        input = in;
        output = out;
        logging = log;
        port = controllerPort;
        socket = s;
        displayTimeoutValue = timeoutValue;
        return PING;
    }

    public void execute() {
        logging.info("ping " + port);
        try {
            output.println("ping "+ port);
            int previousTimeout = socket.getSoTimeout();
            logging.debug(String.format("%s", previousTimeout));
            socket.setSoTimeout(displayTimeoutValue);
            String answer = input.readLine();
            socket.setSoTimeout(previousTimeout);
            logging.info(answer);
            String goodAnswer = "pong " + port;
            if (answer.equals(goodAnswer)) {
                result = true;
            }
        } catch (Exception e) {
            logging.debug(e.getMessage());
        } 
    }


    /**
     * This methods return true if the fish is added else false 
     * If the result is true after the call of this method the attribute is false
     */
    public boolean getResult() {
        boolean currentResult = result;
        if (result == true) {
            result = false;
        }
        return currentResult;
    }
}
