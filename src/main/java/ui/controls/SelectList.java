package ui.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import ui.Page;

public class SelectList extends Control {
    public SelectList(Page pageValue, By locator) {
        super ( pageValue, locator );
    }

    public Select getSelect() {
        return new Select ( this.element () );
    }

    public void selectByText(String value) {
        this.exists ();
        this.getSelect ().selectByVisibleText ( value );
    }
}
