package ui.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.Page;
import ui.controls.Control;

public class Edit extends Control {

    public Edit(Page pageValue, By locatorValue) {
        super ( pageValue, locatorValue );
    }
    public void setText(String value) {
        this.click ();
        this.element ().clear ();
        this.element ().sendKeys ( value );
    }
}
