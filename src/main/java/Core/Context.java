package Core;

import java.util.Hashtable;

public class Context {

    private Context() {}

    private static Hashtable<String, Hashtable<String, Object>> contextVariables
            = new Hashtable<String, Hashtable<String, Object>> (  );

    public static void Put(String name, Object value) {
        Hashtable<String, Object> dataMap = new Hashtable<String, Object> (  );
        String threadName = Driver.getThreadName();

        if (contextVariables.contains ( threadName )) {
            dataMap = contextVariables.get ( threadName );
            contextVariables.remove ( threadName );
        }

        if (contextVariables.contains ( name )) {dataMap.remove ( name );}

        dataMap.put ( name, value );
        contextVariables.put ( threadName, dataMap );
    }

    public static Object get(String name) {
        String threadName = Driver.getThreadName ();

        if (contextVariables.containsKey ( threadName )) return contextVariables.get ( threadName ).get ( name );

        return null;
    }


    public static void clearCurrent() {
        String threadName = Driver.getThreadName ();

        if (contextVariables.containsKey ( threadName )) {contextVariables.remove ( threadName );}

        contextVariables.put ( threadName, new Hashtable<String, Object> (  ) );
    }

    public static Hashtable<String, Object> variables()
    {
        String threadName = Driver.getThreadName ();
        return (Hashtable<String, Object>) contextVariables.get(threadName).keys ();
    }
}
