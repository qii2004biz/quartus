package pages;

import core.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import ui.FindBy;
import ui.Page;
import ui.controls.Control;
import ui.controls.Edit;
import ui.controls.SelectList;

import static utils.DateUtilities.adjustDate;

public class SearchPage extends Page {
    String checkInDay = adjustDate ( "yyyy-MM-dd", 1 );
    String checkOutDay = adjustDate ( "yyyy-MM-dd", 4 );
    @FindBy(locator = "css=#b2indexPage h1")
    public Control title;


    @FindBy(locator = "ss")
    public Edit editDestination;
    @FindBy(locator = "css=button.sb-searchbox__button")
    public Control searchButton;
    @FindBy(locator = "css=table td.bui-calendar__date[data-date='" + "2020-10-16" + "']", excludeFromSearch = true)
    public Control chooseCheckInDate;
    @FindBy(locator = "css=label.sb-searchbox__label[for='checkout_monthday']", excludeFromSearch = true)
    public Control checkOut;
    @FindBy(locator = "css=table td.bui-calendar__date[data-date='2020-10-18']", excludeFromSearch = true)
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

        if (Configuration.platform ().isWeb ()) {
            this.getDriver ().manage ().window ().setSize ( new Dimension ( 950, 1250 ) );
        }
        return this;
    }

    public void selectCheckinCheckOutDate() {
        //Example of handling creating a dynamic control on a page
        //containing non-static content.


        Control checkInDate = buildLocatorControl ( "table td.bui-calendar__date[data-date='" + checkInDay + "']" );
        checkInDate.exists ();
        if (checkForFeedback ()) {
            getDriver ().findElement ( By.xpath ( "//*[text()='Check-in Date']" ) ).click ();
        }
        if (checkInDate.isClickable ( 15 ))
            checkInDate.click ();

        checkOut.click ();
        checkForFeedback ();
        Control chooseCheckOutDate = buildLocatorControl ( "table td.bui-calendar__date[data-date='" + checkOutDay + "']" );
        chooseCheckOutDate.exists ();
        chooseCheckOutDate.click ();
    }

    public void selectForWork(boolean isForWork) {
        if (isForWork) {
            if (forWork.element ().isDisplayed ())
                forWork.click ();
        }
    }

    private Control buildLocatorControl(String locatorText) {
        return new Control ( this, By.cssSelector ( locatorText ) );
    }

    private boolean checkForFeedback() {
        try {
            if (getDriver ().findElement ( By.cssSelector ( "#ethnio-booking-theme" ) ).isDisplayed ())
                getDriver ().findElement ( By.xpath ( "//a[text()='Close']" ) ).click ();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
