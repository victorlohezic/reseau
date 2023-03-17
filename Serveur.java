import java.io.*;
import java.net.*;

public class Serveur {
    private ServerSocket s;
    private Socket soc;

    public Serveur(int port) throws IOException {
        s = new ServerSocket(port);
        soc = s.accept();
    }

    public void start() throws IOException {
        BufferedReader plec = new BufferedReader(new InputStreamReader(soc.getInputStream()));

        PrintWriter pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())), true);

        while (true) {
            String str = plec.readLine();
            if (str.equals("END")) {
                break;
            }
            System.out.println("ECHO = " + str);
            pred.println(str);
        }

        plec.close();
        pred.close();
    }

    public void close() throws IOException {
        soc.close();
        s.close();
    }

    public static void main(String[] argv) {
        try {
            int port = Integer.parseInt(argv[0]);
            Serveur server = new Serveur(port);
            server.start();
            server.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}