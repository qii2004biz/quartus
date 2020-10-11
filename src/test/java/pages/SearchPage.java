package pages;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import ui.FindBy;
import ui.Page;
import ui.controls.Control;
import ui.controls.Edit;
import ui.controls.SelectList;

public class SearchPage extends Page {
    @FindBy(locator = "ss")
    public Edit editDestination;
    @FindBy(locator = "css=button.sb-searchbox__button")
    public Control searchButton;
    @FindBy(locator = "css=table td.bui-calendar__date[data-date='2020-10-16']")
    public Control chooseCheckInDate;
    @FindBy(locator = "css=label.sb-searchbox__label[for='checkout_monthday']")
    public Control checkOut;
    @FindBy(locator = "css=table td.bui-calendar__date[data-date='2020-10-18']")
    public Control chooseCheckOutDate;
    @FindBy(locator = "css=input[name=sb_travel_purpose]")
    public Control forWork;
    @FindBy(locator = "group_adults")
    public SelectList selectAdultNumber;

    public SearchPage(WebDriver driver) {
        super ( driver );
    }

    @Override
    public Page navigate() {
        String baseUrl = System.getProperty ( "url" );
        this.getDriver ().get ( baseUrl );
        this.getDriver ().manage ().window ().setSize ( new Dimension ( 950, 1050 ) );

        return this;
    }

    public void checkIn() {
        chooseCheckInDate.click ();
        checkOut.click ();
        chooseCheckOutDate.click ();
    }

    public void selectForWork(boolean isForWork) {
        if (isForWork) {
            if (forWork.element ().isDisplayed ())
                forWork.click ();
        }
    }
}
