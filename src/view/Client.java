import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] argv) {
        try {
            int port = Integer.parseInt(argv[1]);
            Socket s = new Socket(argv[0], port);
            System.out.println("SOCKET = " + s);

            BufferedReader plec = new BufferedReader(new InputStreamReader(s.getInputStream()));

            PrintWriter pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);

            Status status = Status.initStatus(plec, pred);
            status.execute();

            System.out.println("END");
            pred.println("END");
            plec.close();
            pred.close();
            s.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
