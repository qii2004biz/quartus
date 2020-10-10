package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.controls.Control;

public class Page {
    private WebDriver driver;

    public Page(WebDriver driver) {
        super ();
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }


    public Page navigate() {
        return this;
    }

    public boolean isTextPresent(String text) {
        String locator = String.format ( "//*[text()='{0}' or contains(text(), '{1}')]", text, text );
        Control element = new Control (this, By.xpath (locator));
        return element.exists ();
    }
//    private static HashMap<String, Page> currentPages = new HashMap<String, Page> (  );


//    public static Page getCurrent() {
//        return currentPages.get ( Driver.getThreadName () );
//    }
//    public static void setCurrent(String value) {
//        return currentPages.put (value, Driver.  );
//    }
}
