package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.FindBy;
import ui.Page;
import ui.controls.Edit;

public class SearchPageResults extends Page {
    @FindBy ( locator = "ss" )
    public Edit editDestination;
    public SearchPageResults(WebDriver driver) {
        super ( driver );
    }
}
