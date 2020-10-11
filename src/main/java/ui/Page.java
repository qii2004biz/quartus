package ui;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import ui.controls.Control;

import java.io.File;
import java.io.IOException;

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
    public byte[] captureScreenShot() {
        WebDriver augmentedDriver = new Augmenter ().augment(getDriver ());
        byte [] data = ((TakesScreenshot) augmentedDriver).getScreenshotAs ( OutputType.BYTES );
        return data;
    }

    public File captureScreenShot(String destination) throws IOException {
        WebDriver augmentedDriver = new Augmenter ().augment ( getDriver () );
        File srcFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs ( OutputType.FILE );
        File output = new File(destination);
        FileUtils.copyFile (srcFile, output);
        return output;
    }


//    private static HashMap<String, Page> currentPages = new HashMap<String, Page> (  );


//    public static Page getCurrent() {
//        return currentPages.get ( Driver.getThreadName () );
//    }
//    public static void setCurrent(String value) {
//        return currentPages.put (value, Driver.  );
//    }
}
