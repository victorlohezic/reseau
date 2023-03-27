import java.io.*;

public class Logging{

    private ConfigParser configParser;
    private boolean isInfo=true;
    private boolean isDebug=true;
    private boolean isWarning=true;
    private boolean isSave=true;
    private PrintWriter writer;
    private String logPath;


    public Logging(String path) {
        logPath = path;

        try {
            writer = new PrintWriter(logPath, "UTF-8");


        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier: " + e.getMessage());
        }

    }

    public void closeLogging() {
        writer.close();
    }


    // logs info messages
    public void info(String message) {

        if (isInfo) {
            String infoMessage = "Info: " + message;
            if (isSave) {

                writer.println(infoMessage);


            } else {
                System.err.println(infoMessage);
            }
        }
    }


    // logs debug messages
    public void debug(String message) {
        if (isDebug) {
            String debugMessage = "Debug: " + message;
            if (isSave) {
                writer.println(debugMessage);
            } else {
                System.err.println(debugMessage);
            }
        }
    }    
    

    // logs warning messages
    public void warning(String message) {
        if (isWarning) {
            String warningMessage = "Warning: " + message;
            if (isSave) {
                writer.println(warningMessage);
            } else {
                System.err.println(warningMessage);
            }
        }
    }    


    // enables/disables the different types of logs
    public void enable(boolean info, boolean debug, boolean warning) {
        isInfo = info;
        isDebug = debug; 
        isWarning = warning;
    }


    // enables/disables logs on a file
    public void enableSaving(boolean save) {
        isSave = save;
    }   



    public static void main(String[] args) {

        Logging myLog = new Logging("mli.log");

        for (int k=0; k <3; k++) {
            myLog.info("un message d'info");
            myLog.debug("voici un message de debug");
            myLog.warning("et maintenant un message de warning");
        }

        myLog.enable(true, true, false);
        for (int k=0; k <3; k++) {
            myLog.info("un message d'info");
            myLog.debug("voici un message de debug");
            myLog.warning("et maintenant un message de warning");
        }

        myLog.enable(true, true, true);
        myLog.enableSaving(false);
        for (int k=0; k <3; k++) {
            myLog.info("un message d'info");
            myLog.debug("voici un message de debug");
            myLog.warning("et maintenant un message de warning");
        }
        myLog.closeLogging();

        
    }

}
