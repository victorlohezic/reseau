import java.net.*;

public class ThreadServeur {

    public static void main(String[] argv) {
        int p = Integer.parseInt(argv[0]);
        new ThreadServeur(p);

    }

    public ThreadServeur(int p) {
        try {
            ServerSocket s = new ServerSocket(p);

            while (true) {
                Socket soc = s.accept();
                SocketThread r = new SocketThread(this, soc);
                Thread t = new Thread(r);
                t.start();
            }

            // s.close();
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}