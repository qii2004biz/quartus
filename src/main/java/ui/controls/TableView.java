package ui.controls;

import org.openqa.selenium.By;
import ui.Page;
import ui.SubItem;

public class TableView extends Control{
    public TableView(Page parentValue, By locatorValue) {
        super ( parentValue, locatorValue );
    }
    protected String getFullItemLocator() {
        return String.format ( "%s%s", this.getLocatorText (), this.getItemLocator () );
    }
    public int getItemsCount() {
        return this.getDriver ().findElements ( By.xpath ( this.getFullItemLocator () ) ).size ();
    }
    public Control getItem(int index) {
        String locator = String.format ( "(%s)[%d]", this.getFullItemLocator (), index + 1 );
        return new Control(this.getParent (), By.xpath ( locator ));
    }
    public boolean isNotEmpty(long timeout) {
        return this.getItem ( 0 ).exists (timeout);
    }
    public boolean isNotEmpty() {
        return isNotEmpty (TIMEOUT);
    }
    public boolean isEmpty(long timeout) {
        return this.getItem ( 0 ).disappears (timeout);
    }
    public boolean isEmpty() {
        return isEmpty (TIMEOUT);
    }
    public By getSubItemLocator(String name, int index) {
        SubItem item = this.getSubItemHashMap ().get ( name );
        String locator = String.format ( "(%s)[%d]%s", this.getFullItemLocator (), index + 1,
        item.locator ());
        return By.xpath ( locator );
    }

    public <T extends Control> T getSubItem(String name, int index, Class<T> itemType) throws Exception {
        T element = itemType.getConstructor ( Page.class, By.class)
                .newInstance(this.getParent (), getSubItemLocator ( name, index ));
        return element;
    }
    public Control getSubItem(String name, int index) throws Exception {
        SubItem item = this.getSubItemHashMap ().get ( name );
        return this.getSubItem ( name, index, item.controlType () );
    }
}
