import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.HashMap;

public class ConfigParser {

    private String filenameConfig;
    private Map<String, String> config;

    ConfigParser(String filenameConfig) {
        this.filenameConfig = filenameConfig;
        this.config = new HashMap<>();
        this.parse();
    }

    void parse(){
        try(BufferedReader br = new BufferedReader(new FileReader(this.filenameConfig))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty() || line.charAt(0) == '#') {
                    continue;
                }
                String[] split = line.split("=");
                String setting = split[0].trim();
                String value = split[1].trim();
                config.put(setting, value);
            }
            br.close();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage()); // handle exception
        }
    }

    String getId(){
        return config.get("id");
    }

    String getControllerAddress() {
        return config.get("controller-address");
    }

    int getControllerPort() {
        return Integer.parseInt(config.get("controller-port"));
    }

    String getResources() {
        return (config.get("resources"));
    }

    int getDisplayTimeoutValue() {
        return Integer.parseInt(config.get("display-timeout-value"));
    }
}   
