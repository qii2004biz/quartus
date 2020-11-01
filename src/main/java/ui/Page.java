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
import java.lang.reflect.Field;

import static ui.controls.Control.TIMEOUT;

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
        String locator = String.format ( "//*[text()='%s' or contains(text(), '%s' )]", text, text);
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
    public boolean isCurrent(long timeout) throws Exception {
        Field[] fields = this.getClass ().getFields ();
        for (Field field : fields) {
            if (Control.class.isAssignableFrom ( field.getType () )) {
                Control control = (Control) field.get(this);
                if (!control.isExcludeFromSearch () && !control.exists (timeout)) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isCurrent() throws Exception {return isCurrent (TIMEOUT); }

    public boolean allElementsAre(Control [] elements, String state) throws Exception {
        for (Control element : elements) {
            if (!(boolean)element.getClass ().getMethod(state).invoke ( element )) {
                return false;
            }
        }
        return true;
    }    public boolean anyElementsIs(Control [] elements, String state) throws Exception {
        for (Control element : elements) {
            if (!(boolean)element.getClass ().getMethod(state).invoke ( element )) {
                return true;
            }
        }
        return false;
    }
    public boolean allElementsAreExists(Control [] elements) throws Exception {
        return allElementsAre ( elements, "exists" );
    }
    public boolean allElementsAreVisible(Control [] elements) throws Exception {
        return allElementsAre ( elements, "visible" );
    }
    public boolean allElementsAreInvisible(Control [] elements) throws Exception {
        return allElementsAre ( elements, "invisible" );
    }
    public boolean allElementsAreEnabled(Control [] elements) throws Exception {
        return allElementsAre ( elements, "enabled" );
    }
    public boolean allElementsAreDisabled(Control [] elements) throws Exception {
        return allElementsAre ( elements, "disabled" );
    }

    public boolean anyElementsAreExists(Control [] elements) throws Exception {
        return anyElementsIs ( elements, "exists" );
    }
    public boolean anyElementsDoesNotExist(Control [] elements) throws Exception {
        return anyElementsIs ( elements, "disappears" );
    }
    public boolean anyElementsIsVisible(Control [] elements) throws Exception {
        return anyElementsIs ( elements, "visible" );
    }
    public boolean anyElementsIsInvisible(Control [] elements) throws Exception {
        return anyElementsIs ( elements, "invisible" );
    }

}
