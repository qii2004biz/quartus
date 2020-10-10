package ui.controls;

import core.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Control {
    private By locator;
    private WebDriver driver;
    public static final longh  TIMEOUT = Configuration.timeout ();

    public By getLocator() {
        {
            return locator;
        }
    }

    public Control(WebDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
    }

    public WebElement getElement() {
        return driver.findElement ( locator );
    }

    public boolean exists(long timeout) {
        WebDriverWait wait = new WebDriverWait ( driver, timeout );
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
        exists ();
        this.getElement ().click ();
    }
}
