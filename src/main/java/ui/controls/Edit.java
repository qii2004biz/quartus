package ui.controls;

import org.openqa.selenium.By;
import ui.Page;

public class Edit extends Control {

    public Edit(Page pageValue, By locatorValue) {
        super ( pageValue, locatorValue );
    }
    public void setText(String value) throws Exception {
        this.click ();
        this.element ().clear ();
        this.element ().sendKeys ( value );
    }

    @Override
    public String getText() {
        return this.element ().getAttribute ( "value" );
    }
}
