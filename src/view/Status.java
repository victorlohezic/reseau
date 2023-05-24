import java.util.ArrayList;

/**
 * Give information about the state of the aquarium
 **/
public class Status implements ParametersCommande {
    private static final Status STATUS = new Status();
    private static Logging logging;
    private static Ping ping;
    private static GetFishes getFishes;
    private static Prompt prompt;

    private Status() {
    }

    /*
     * Initialise Status command and return this command
     */
    public static Status initStatus(Ping PingCommand, GetFishes getFishesCommand, Logging log, Prompt p) {
        logging = log;
        ping = PingCommand;
        getFishes = getFishesCommand;
        prompt = p;
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
            return;
        }
        getFishes.execute();
        String stateView = getFishes.getResult();
        //System.out.println(stateView.split(" ").length);
        stateView = stateView.replaceAll("\\[", "\n Fish").replaceAll("]", "").replaceAll("list \n", "").replaceAll(",\\d+ *\n", "\n").replaceAll(",\\d+$", "");
        int fishCount = stateView.length() > 7  ? stateView.split("\n", -1).length : 0;
        String plurial = fishCount < 2 ? "poisson trouvé" : "poissons trouvés";
        result += String.format(", %d %s \n", fishCount, plurial);
        result += stateView;
        logging.info(result);
        prompt.print(result);
    }

    public void setParameters(ArrayList<String> p) {
        return;
    }

}