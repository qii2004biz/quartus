package ui;

import core.Configuration;
import core.Driver;
import core.Platform;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.controls.Control;

import java.lang.reflect.Field;

public class PageFactory {
    public PageFactory() {
    }

    private static By toLocator(String input) {
        if (input.matches ( "^(xpath=|/)(.*)" )) {
            return By.xpath ( input.replaceAll ( "^xpath=", "" ) );
        } else if (input.matches ( "^id=(.*)" )) {
            return By.id ( input.substring ( "id=".length () ) );
        } else if (input.matches ( "^name=(.*)" )) {
            return By.name ( input.substring ( "name=".length () ) );
        } else if (input.matches ( "^css=(.*)" )) {
            return By.cssSelector ( input.substring ( "css=".length () ) );
        } else if (input.matches ( "^class=(.*)" )) {
            return By.className ( input.substring ( "class=".length () ) );
        } else {
            return By.id ( input );
        }
    }

    private static FindBy getLocatorForPlatform(FindBy locators[], Platform platform) {
        for (FindBy locator : locators) {
            if (locator.platform ().equals ( platform )) {
                return locator;
            }
        }
        return null;
    }

    private static SubItem[] getSubItemsForPlatform(SubItem[] items, Platform platform) {
        SubItem[] result = new SubItem[]{};
        for (SubItem item : items) {
            if (item.platform ().equals ( platform ) || item.platform ().equals ( Platform.ANY )) {
                result = ArrayUtils.add (result, item);
            }
        }
        return result;
    }
    public static <T extends Page> T init(Class<T> pageClass) throws Exception {
        T page = pageClass.getConstructor ( WebDriver.class ).newInstance ( Driver.current () );
        for (Field field : pageClass.getFields ()) {
            FindBy locators[] = field.getAnnotationsByType ( FindBy.class );
            if (locators != null && locators.length > 0) {
                FindBy anno = getLocatorForPlatform ( locators, Configuration.platform () );
                if (anno == null) {
                    anno = getLocatorForPlatform ( locators, Platform.ANY );
                }
                if (anno != null) {
                    Control control = (Control) field
                            .getType ()
                            .getConstructor ( Page.class, By.class )
                            .newInstance ( page,
                                    toLocator ( anno.locator () ) );
                    control.setItemLocator ( anno.itemLocator() );
                    SubItem[] items = field.getAnnotationsByType ( SubItem.class );
                    control.addSubItems ( getSubItemsForPlatform ( items, Configuration.platform () ) );
                    control.setExcludeFromSearch ( anno.excludeFromSearch () );
                    field.set ( page, control );
                }
            }
        }
        return page;
    }
}
