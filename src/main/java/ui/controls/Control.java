package ui.controls;

import core.Configuration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.Page;

public class Control {
    private By locator;
    private Page parent;
    public static final long TIMEOUT = Configuration.timeout ();

    public Control(Page pageValue, By locatorValue) {
        this.parent = pageValue;
        this.locator = locatorValue;
    }

    public WebDriver getDriver() { return parent.getDriver ();}

    public WebElement element() {
        return getDriver ().findElement ( locator );
    }
    public boolean exists(long timeout) {
        WebDriverWait wait = new WebDriverWait ( getDriver (), timeout );
        try {
            wait.until ( ExpectedConditions.presenceOfElementLocated ( locator ) );
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean exists() {
        return exists ( TIMEOUT );
    }

    public void click() {
        Assert.assertTrue ( "Unable to find element: " + this.locator.toString (), exists ());
        this.element ().click ();
    }
    public By getLocator() {
        {
            return locator;
        }
    }
}
