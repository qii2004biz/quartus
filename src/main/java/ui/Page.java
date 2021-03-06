package ui;

import com.paulhammant.ngwebdriver.NgWebDriver;
import core.Alias;
import core.Configuration;
import core.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.reflections.Reflections;
import ui.controls.Control;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Page {
    protected static final long TIMEOUT = Configuration.timeout ();
    private static ConcurrentHashMap<String, Page> currentPages = new ConcurrentHashMap<String, Page>();
    private WebDriver driver;
    private NgWebDriver ngWebDriver;
    private JavascriptExecutor jsDriver;

    public Page(WebDriver driver) {
        super ();
        this.driver = driver;
    }

    public static Page screen(String name) throws  Exception {
        return screen(name, Configuration.get ( "pages_package" ) );
    }

    public static Page screen(String name, String pagePackage ) throws  Exception {
        Reflections reflections = new Reflections ( pagePackage );
        Set<Class<? extends Page>> subTypes = reflections.getSubTypesOf ( Page.class );
        for (Class<? extends Page> type : subTypes ) {
            Alias annotation = type.getAnnotation ( Alias.class );
            if (annotation != null && annotation.value ().equals ( name )) {
                return PageFactory.init ( type );
            }
        }
        return null;
    }

    public WebDriver getDriver() {
        return driver;
    }
    public static Page getCurrent() {
        return currentPages.get ( Driver.getThreadName () );
    }
    public static void setCurrent(Page newPage ) {
        currentPages.put ( Driver.getThreadName (), newPage );
    }

    public Page navigate() {
        return this;
    }

    public Control onPage ( String name ) throws Exception {
        for ( Field field : this.getClass ().getFields ()) {
            if ( Control.class.isAssignableFrom ( field.getType () )) {
                Alias alias = field.getAnnotation ( Alias.class );
                if (alias != null && name.equals ( alias.value () )) {
                    return ( Control ) field.get ( this );
                }
            }
        }
        return null;
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
    public Control buildCssControl (String locatorText) {
        return new Control ( this, By.cssSelector ( locatorText ) );
    }

    public Control buildXpathControl (String locatorText) {
        return new Control ( this, By.xpath ( locatorText ) );
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
    public void waitForAngularRequestToComplete () {
        this.jsDriver = (JavascriptExecutor) this.driver;
        ngWebDriver = new NgWebDriver(this.jsDriver );
        ngWebDriver.waitForAngularRequestsToFinish();
    }

}
