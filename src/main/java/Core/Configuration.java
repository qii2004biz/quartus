package Core;

import com.sun.tools.internal.ws.processor.util.DirectoryUtil;
import ui.TargetPlatformMethods;

import java.util.Properties;

public class Configuration {
    private static Configuration instance = null;
    private static Properties property = null;
    private static String propertiesFilePath = "./src/java/config";

    private Configuration() {}

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration ();
            property = new Properties ();
        }
        return instance;
    }

    public static TargetPlatformMethods.TargetPlatform Platform () {
        return TargetPlatformMethods.TargetPlatform.valueOf (get("Browser"));
    }

    public static int timeOut () {
        return Integer.parseInt ( get("timeOut") );
    }

    public static String get(String value) {
        return property.getProperty ( value );
    }
}
