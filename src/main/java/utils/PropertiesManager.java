package utils;


import facades.report.Reporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class PropertiesManager {
    private Properties props = null;

    public PropertiesManager(String filePath, String fileName) {
        props = new Properties();
        try {
            FileInputStream in = new FileInputStream(filePath + fileName);
            props.load(in);
            in.close();
        } catch (IOException e) {
            Reporter.logException("Property Manager Error", e);
        }
    }

    public String getValue(String _key) {
        return props.getProperty(_key);
    }

    public List<String> getAllValuesStartingWith(String _key) {
        List<String> values = new ArrayList<>();

        this.props.forEach((k, v) -> {
                    if (k.toString().startsWith(_key))
                        values.add(v.toString());

                }
        );
        return values;
    }

    public Map<String,String> getAllKeysAndValuesStartingWith(String _key) {
        Map<String, String> values = new HashMap<>();

        this.props.forEach((k, v) -> {
                    if (k.toString().startsWith(_key))
                        values.put(k.toString(), v.toString());
                }
        );
        return values;
    }
}
