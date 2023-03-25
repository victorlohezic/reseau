import java.io.*;

public class LoggingStatic {

    private static ConfigParser configParser;
    private static boolean isInfo=true;
    private static boolean isDebug=true;
    private static boolean isWarning=true;
    private static boolean isSave=true;
    private static PrintWriter writer;
    private static String logPath = "myLog.log";


    public static void initLogging(String path) {

        logPath = path;

        try {
            writer = new PrintWriter(logPath, "UTF-8");

        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier: " + e.getMessage());
        }

    }


    // logs info messages
    public static void info(String message) {
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
    public static void debug(String message) {
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
    public static void warning(String message) {
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
    public static void enable(boolean info, boolean debug, boolean warning) {
        isInfo = info;
        isDebug = debug; 
        isWarning = warning;
    }


    // enables/disables logs on a file
    public static void enableSaving(boolean save) {
        isSave = save;
    }   



    public static void main(String[] args) {

            initLogging("myLog.log");
            for (int k=0; k <3; k++) {
                info("un message d'info");
                debug("voici un message de debug");
                warning("et maintenant un message de warning");
            }

            enable(true, true, false);
            for (int k=0; k <3; k++) {
                info("un message d'info");
                debug("voici un message de debug");
                warning("et maintenant un message de warning");
            }

            enable(true, true, true);
            enableSaving(false);
            for (int k=0; k <3; k++) {
                info("un message d'info");
                debug("voici un message de debug");
                warning("et maintenant un message de warning");
            }

            writer.close();

        
    }

}
