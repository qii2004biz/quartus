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
import ui.SubItem;

import java.util.HashMap;

public class Control {
    private Page parent;
    private By locator;
    private String locatorText;
    private String itemLocator;
    private HashMap<String, SubItem> subItemHashMap;
    public static final long TIMEOUT = Configuration.timeout ();

    public Page getParent() {return parent;}

    public String getLocatorText() { return locatorText; }

    public String getItemLocator() { return itemLocator; }

    public void setItemLocator(String itemLocator) { this.itemLocator = itemLocator; }

    public HashMap<String, SubItem> getSubItemHashMap() { return subItemHashMap; }

    public void addSubItems(SubItem[] items) {
        for (SubItem item : items)
            this.subItemHashMap.put ( item.name (), item );
    }

    public Control(Page parentValue, By locatorValue) {
        this.parent = parentValue;
        this.locator = locatorValue;
        this.locatorText = locatorValue.toString ().replaceAll ( "^By\\.(\\S+): ", "" );
        this.subItemHashMap = new HashMap<String, SubItem>();
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
    public boolean visible(long timeout) {
        WebDriverWait wait = new WebDriverWait ( getDriver (), timeout );
        try {
            wait.until ( ExpectedConditions.visibilityOfElementLocated ( locator ) );
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
    public boolean visible() {
        Assert.assertTrue ( "Unable to find element: " + this.locator.toString ()
        ,exists ());
        return visible (TIMEOUT);
    }


    public boolean isClickable(long timeout) {
        WebDriverWait wait = new WebDriverWait ( getDriver (), timeout );
        try {
            wait.until ( ExpectedConditions.elementToBeClickable ( locator ) );
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean exists() {
        return exists ( TIMEOUT );
    }

    public void click() {
        Assert.assertTrue ( "Unable to find element: " + this.locator.toString ()
                , visible ());
        this.element ().click ();
    }
    public String getText() { return this.element ().getText (); }

    public By getLocator() {
        {
            return locator;
        }
    }
}
