package controls;

import core.Configuration;
import org.openqa.selenium.By;
import pages.android.LocationSearchPage;
import ui.Page;
import ui.PageFactory;
import ui.controls.Control;
import ui.controls.Edit;

public class FieldLookEditSample extends Edit {
    public FieldLookEditSample(Page parentValue, By locatorValue) {
        super ( parentValue, locatorValue );
    }

    @Override
    public void setText(String value) throws Exception {
        this.click ();
        if (Configuration.platform ().isWeb ()) {
            this.element ().clear ();
            this.element ().sendKeys ( value );
            Control lookupItem = new Control (this.getParent (),
                    By.xpath ( "//li[contains(@class, 'autocomplete__item')])[1]" ));
            lookupItem.click ();
        } else {
            LocationSearchPage search = PageFactory.init ( LocationSearchPage.class );
            search.editSearch.setText(value);
            search.itemTopMostResult.click ();
        }
    }
}
