package pages.android;

import core.Platform;
import org.openqa.selenium.WebDriver;
import ui.FindBy;
import ui.Page;
import ui.controls.Control;
import ui.controls.Edit;

public class LocationSearchPage extends Page {
    public LocationSearchPage(WebDriver driverValue) {
        super ( driverValue );
    }
    @FindBy ( locator = "disam_search", platform = Platform.ANDROID_NATIVE)
    public Edit editSearch;

    @FindBy ( locator = "xpath=(//android.widget.LinearLayout[contains(@resource-id, 'disam_list_root')])[1]" )
    public Control itemTopMostResult;
}
