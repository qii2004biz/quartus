package pages;

import org.openqa.selenium.WebDriver;
import ui.FindBy;
import ui.SubItem;
import ui.controls.TableView;

public class CommonHasTablePage extends SearchPage{
    public CommonHasTablePage(WebDriver driver) {
        super ( driver );
    }

    @FindBy(locator = "//table", itemLocator = "/tbody/tr")
    @SubItem (name = "Logical Name1", locator = "/td[1]")
    @SubItem (name = "Logical Name2", locator = "/td[2]")
    @SubItem (name = "Logical Name3", locator = "/td[3]")
    public TableView tableCommonHasTable;
}
