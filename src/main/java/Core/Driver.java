package Core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.grid.session.remote.RemoteSession;
import ui.TargetPlatformMethods;

import java.lang.reflect.Type;
import java.util.Hashtable;


public class Driver {
    private static Hashtable<String, WebDriver> driverThreadMap = new Hashtable<String, WebDriver> (  );

    private static Hashtable<TargetPlatformMethods.TargetPlatform, Type> driverMap
            = new Hashtable<TargetPlatformMethods.TargetPlatform, Type> ()
    {
        {TargetPlatformMethods.TargetPlatform.CHROME,  Type(ChromeDriver())}
    };

    private static RemoteSession caps;

    public Driver() {}

    public static String getThreadName() {
        return Thread.currentThread ().getName () + Thread.currentThread ().getId ();
    }
}
