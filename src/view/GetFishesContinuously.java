import java.io.PrintWriter;

public class GetFishesContinuously implements Commande {

    private static final GetFishesContinuously GET_FISHES_CONTINUOUSLY = new GetFishesContinuously();
    private static Logging logging;
    private static PrintWriter output;

    private GetFishesContinuously(){
    }

    public static GetFishesContinuously initGetFishesContinuously(PrintWriter out, Logging log) {
        output = out;
        logging = log;
        return GET_FISHES_CONTINUOUSLY;
    }

     /**
     * Cast a commande to GetFishesContinuously commande
     * @param commande : Commande to cast
     * @return  GetFishesContinuously
     * @throws FishException 
     * 
     */
    public static GetFishesContinuously castCommandToFish(Commande commande) throws CommandeException {
        if (commande == GET_FISHES_CONTINUOUSLY) {
            return GET_FISHES_CONTINUOUSLY;
        } else {
            throw new CommandeException("La commande à caster n'est pas un GetFishesContinuously");
        }
    }

    public void execute() {
        logging.info("getFishesContinuously");
        try {
            output.println("getFishesContinuously");
        }
        catch (Exception e) {
            logging.debug(e.getMessage());
        }
    }
}