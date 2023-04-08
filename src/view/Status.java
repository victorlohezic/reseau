import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;

/** 
 * Give information about the state of the aquarium  
**/
public class Status implements ParametersCommande {
    private static final Status STATUS = new Status();
    private static Logging logging;
    private static Ping ping;

    private Status(){
    }

    /*
     * Initialise Status command and return this command
     */
    public static Status initStatus(Ping PingCommand, Logging log) {
        logging = log;
        ping = PingCommand;
        return STATUS;
    }

    /** 
     * Launch the work of the commande
    **/
    public void execute() {
        String result = "";
        ping.execute();
        if (ping.getResult()) {
            result += "OK: Connecté au contrôleur";
        } else {
            logging.warning("NOK: le controlleur ne répond pas");
        }
        logging.info(result);
    }

    public void setParameters(ArrayList<String> p){
        return;
    }

}