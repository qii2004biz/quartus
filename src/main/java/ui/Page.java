package ui;

import core.Driver;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class Page {
    private static HashMap<String, Page> currentPages = new HashMap<String, Page> (  );
    private WebDriver driver;

    public WebDriver driver() {
        return driver;
    }
    public Page(WebDriver driverValue) {
        driver = driverValue;
    }

    public Page navigate() {
        return this;
    }

//    public boolean isTextPresent(String text) {
//        String locatorText = String.format ( "//*[text()='{0}' or contains(text(), '{1}')]", text, text );
//        Controls element = new Controls
//    }


//    public static Page getCurrent() {
//        return currentPages.get ( Driver.getThreadName () );
//    }
//    public static void setCurrent(String value) {
//        return currentPages.put (value, Driver.  );
//    }
}
