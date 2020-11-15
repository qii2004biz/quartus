package pages;

import core.Alias;
import core.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import ui.FindBy;
import ui.Page;
import ui.controls.Control;

@Alias ( "NfoSmart Landing Page" )
public class NfoSmartLandingPage extends Page {

    @Alias ( "Page Title" )
    @FindBy ( locator = "id=welcome-header", excludeFromSearch = false )
    public Control pageTitle;

    @Alias ( "MarketingDiscover" )
    @FindBy ( locator = "css=div:nth-child(1) > h5", excludeFromSearch = false )
    public Control pageMarketingDiscover;

    @Alias ( "MarketingImagination" )
    @FindBy ( locator = "id=marketing-msg", excludeFromSearch = false )
    public Control pageMarketingImagination;

    @Alias ( "Contact Us" )
    @FindBy ( locator = "id=contact-email", excludeFromSearch = false )
    public Control pageContactUs;

    @Alias ( "Marketing Adventure" )
    @FindBy ( locator = "css=div:nth-child(1) > h4", excludeFromSearch = false )
    public Control pageMarketingAdventure;

    @Alias ( "Sign-in" )
    @FindBy ( locator = "css=a > span.MuiButton-label", excludeFromSearch = false )
    public Control signIn;

    @Alias ( "What is NForSmart Icon" )
    @FindBy ( locator = "css=a > div:nth-child(1) > svg > path", excludeFromSearch = false )
    public Control whatIsNforSmartIcon;

    @Alias ( "What is NForSmart" )
    @FindBy ( locator = "css=a > div:nth-child(2) > span", excludeFromSearch = false )
    public Control whatIsNforSmart;

    public NfoSmartLandingPage(WebDriver driver) {
        super ( driver );
    }

    @Override
    public Page navigate() {
        String baseUrl = System.getProperty ( "url" );
        this.getDriver ().get ( baseUrl );

        getDriver ().findElement ( By.id("details-button") ).click ();
        getDriver ().findElement ( By.id("proceed-link") ).click ();

        if (Configuration.platform ().isWeb ()) {
            this.getDriver ().manage ().window ().setSize ( new Dimension ( 950, 1250 ) );
        }
        return this;
    }
}
