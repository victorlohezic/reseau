import java.io.*;
import java.net.*;

public class SocketThread implements Runnable {
    ThreadServeur parent;
    Socket soc;

    public SocketThread(ThreadServeur parent, Socket soc) {
        this.parent = parent;
        this.soc = soc;
    }

    public void run() {
        try {
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
            soc.close();
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}