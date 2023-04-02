import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;

public class Hello {

    private static final Hello HELLO = new Hello();
    private static final Prompt PROMPT = new Prompt();
    private static Logging logging;
    private static BufferedReader input;
    private static PrintWriter output;
    private static Socket socket;

    private Hello() {
    }

    public static Hello initHello(BufferedReader in, PrintWriter out, Socket s, Logging log) {
        input = in;
        output = out;
        logging = log;
        socket = s;
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
