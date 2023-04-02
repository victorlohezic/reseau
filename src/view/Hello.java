import java.io.BufferedReader;
import java.io.IOException;

public class Hello {

    private static final Hello HELLO = new Hello();
    private static Logging logging;
    private static BufferedReader input;

    private Hello() {}

    public static Hello initHello(BufferedReader in, Logging log) {
        input = in;
        logging = log;
        return HELLO;
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
