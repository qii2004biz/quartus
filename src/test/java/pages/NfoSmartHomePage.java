package pages;

import core.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import ui.Page;

public class NfoSmartHomePage extends Page {
    public NfoSmartHomePage(WebDriver driver) {
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
