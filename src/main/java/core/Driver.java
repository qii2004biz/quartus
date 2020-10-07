package core;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public final class Driver {

    public Driver() {
    }

    private static WebDriver driver;

    private static ConcurrentHashMap<String, WebDriver> driverThreadMap = new ConcurrentHashMap<String, WebDriver> ();

    private static final Map<String, Class<?>> driverMap = new HashMap<String, Class<?>> () {
        {
            put ( "chrome", ChromeDriver.class );
            put ( "firefox", FirefoxDriver.class );
            put ( "ie", InternetExplorerDriver.class );
            put ( "edge", EdgeDriver.class );
            put ( "safari", SafariDriver.class );
            put ( "opera", OperaDriver.class );
        }
    };

    private static String getThreadName() {
        return Thread.currentThread ().getName () + "-" + Thread.currentThread ().getId ();
    }

    public static void add(String browser, Capabilities capabilities) throws Exception {
        Class<?> driverClass = driverMap.get ( browser );
        driver = (WebDriver) driverClass.getConstructor ( Capabilities.class ).newInstance ( capabilities );
        String threadName = getThreadName ();
        driverThreadMap.put ( threadName, driver );
    }

    public static WebDriver current() {
        String threadName = getThreadName ();
        return driverThreadMap.get ( threadName );
    }
//    public static void ad(String browser, )
//    public static String getThreadName() {
//        return Thread.currentThread ().getName () + "-" + Thread.currentThread ().getId ();
//    }
//
//
//
//    public static WebDriver current() {
//        String threadName = getThreadName ();
//        return driverThreadMap.get ( threadName );
//    }

//    private static Hashtable<TargetPlatformMethods.TargetPlatform, Type> optionsMap
//            = new Hashtable<TargetPlatformMethods.TargetPlatform, Type> ();
//
//
//    static {
//        {
//            driverMap.put ( TargetPlatformMethods.TargetPlatform.CHROME, ChromeDriver.class.getComponentType () );
//            driverMap.put ( TargetPlatformMethods.TargetPlatform.FIREFOX, FirefoxDriver.class.getComponentType () );
//            driverMap.put ( TargetPlatformMethods.TargetPlatform.SAFARI, SafariDriver.class.getComponentType () );
//            driverMap.put ( TargetPlatformMethods.TargetPlatform.IE, InternetExplorerDriver.class.getComponentType () );
//            driverMap.put ( TargetPlatformMethods.TargetPlatform.EDGE, EdgeDriver.class.getComponentType () );
//            driverMap.put ( TargetPlatformMethods.TargetPlatform.OPERA, OperaDriver.class.getComponentType () );
//            driverMap.put ( TargetPlatformMethods.TargetPlatform.ANDROID_NATIVE, AndroidDriver.class.getComponentType () );
//            driverMap.put ( TargetPlatformMethods.TargetPlatform.IOS_NATIVE, IOSDriver.class.getComponentType () );
//        }
//    };

//    static {
//        {
//            optionsMap.put ( TargetPlatformMethods.TargetPlatform.CHROME, ChromeOptions.class.getComponentType () );
//            optionsMap.put ( TargetPlatformMethods.TargetPlatform.FIREFOX, FirefoxOptions.class.getComponentType () );
//            optionsMap.put ( TargetPlatformMethods.TargetPlatform.SAFARI, SafariOptions.class.getComponentType () );
//            optionsMap.put ( TargetPlatformMethods.TargetPlatform.IE, InternetExplorerOptions.class.getComponentType () );
//            optionsMap.put ( TargetPlatformMethods.TargetPlatform.EDGE, EdgeOptions.class.getComponentType () );
//            optionsMap.put ( TargetPlatformMethods.TargetPlatform.OPERA, OperaOptions.class.getComponentType () );
//        }
//    };
}
