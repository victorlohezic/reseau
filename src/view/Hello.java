import java.io.BufferedReader;
import java.io.IOException;

public class Hello implements Commande {

    private static final Hello HELLO = new Hello();
    private static Logging logging;
    private static BufferedReader input;

    private Hello() {}

    public static Hello initHello(BufferedReader in, Logging log) {
        input = in;
        logging = log;
        return HELLO;
    }

     /**
     * Cast a commande to Hello commande
     * @param commande : Commande to cast
     * @return  Hello
     * @throws CommandeException
     * 
     */
    public static Hello castCommandToHello(Commande commande) throws CommandeException {
        if (commande == HELLO) {
            return HELLO;
        } else {
            throw new CommandeException("La commande à caster n'est pas un Hello");
        }
    }

    public void execute() {

        logging.info("Hello");

        printExecute();
    }

    public void execute(int id) {

        logging.info("Hello in as" + id);

        printExecute();
    }

    private void printExecute(){
        try {
            String answer = input.readLine();
            if (answer.equals("no greeting")) {
                logging.warning(answer);
            } else {
                logging.info((answer));
            }
        }

        catch (IOException e) {
            logging.debug(e.getMessage());
        }
    }

}
