package core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Context {

    private Context() {
    }

    private static ConcurrentHashMap<String, Map<String, Object>> contextVariables = new ConcurrentHashMap<String, Map<String, Object>> ();

    private static String getThreadName() {
        return Thread.currentThread ().getName () + "-" + Thread.currentThread ().getId ();
    }

    public static void put(String name, Object value) {
        Map<String, Object> dataMap = new HashMap<> ();
        String threadName = getThreadName ();

//        if (contextVariables == null)
//            contextVariables = new ConcurrentHashMap<String, Map<String, Object>> ();

        if (contextVariables.containsKey ( threadName )) {
            dataMap = contextVariables.get ( threadName );
        }
        dataMap.put ( name, value );
        contextVariables.put ( threadName, dataMap );
    }

    public static Object get(String name) {
        String threadName = getThreadName ();
        if (contextVariables.containsKey ( threadName )) {
            return contextVariables.get ( threadName ).get ( name );
        }
        return null;
    }

    public static void clearCurrent() {
        if (contextVariables != null)
            contextVariables.put ( getThreadName (), new HashMap<String, Object> () );
    }

    public static Set<String> variables() {
        return contextVariables.get ( getThreadName () ).keySet ();
    }
}
