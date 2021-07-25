package core;

import ui.TargetPlatformMethods;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

public class Configuration {
    private static Properties properties = null;


    private Configuration() {
    }

    public static void load() throws IOException {
        properties = new Properties ();
        InputStream is = new FileInputStream ( new File ( "config.properties" ) );
        BufferedReader reader = new BufferedReader ( new InputStreamReader (
                is,
                StandardCharsets.UTF_8
        ) );
        try {
            properties.load ( reader );
        } finally {
            is.close ();
            reader.close ();
        }
    }

    public static String get(String option) {
        String value = properties.getProperty ( option );

        if (value == null) {
            return "";
        }

        return value;
    }

    public static void print() {
        for (Map.Entry<Object, Object> entry : properties.entrySet ()) {
            System.out.println ( String.format ( "%s-%s", entry.getKey (), entry.getValue () ) );
        }
    }
    public static long timeout() {
        String value = get ( "timeout" ).trim ();
        return Long.parseLong ( value );
    }

    public static Platform platform () { return Platform.fromString ( System.getProperty ("browser") );}
}
