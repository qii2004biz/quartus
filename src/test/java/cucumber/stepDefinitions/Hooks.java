package cucumber.stepDefinitions;

import core.Configuration;
import core.Driver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import facades.webDriverManager.BrowserWebDriverSetup;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.SearchPage;
import pages.SearchPageResults;
import ui.Page;
import ui.PageFactory;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class Hooks {
    private Map<String, Object> vars;
    JavascriptExecutor js;

    public Hooks() {
    }

    protected SearchPage searchPage;
    protected SearchPageResults searchPageResults;

    @Before
    public void beforeScenario(Scenario scenario) throws Exception {
        js = (JavascriptExecutor) Driver.current ();

        Configuration.load ();
        Configuration.print ();

        String browser = System.getProperty ( "browser" );
        BrowserWebDriverSetup.setupBrowserWebDriver ();

        DesiredCapabilities cap = new DesiredCapabilities ();


        if (Configuration.platform ().isWeb ()) {
            Driver.add ( browser, cap );
        } else {
            cap.setCapability ( "platformName", "Android" );
            cap.setCapability ( "deviceName", "Android Emulator" );
            cap.setCapability ( "browserName", "Chrome" );
            cap.setCapability ( "commandTimeout", "60" );
            Driver.add ( Configuration.get ( "driver_url" ), browser, cap );
        }
        searchPage = PageFactory.init ( SearchPage.class );
        searchPage.navigate ();
    }

    @After
    public void afterScenario(Scenario scenario) throws IOException {
        if (scenario.isFailed ()) {
            Page currentPage = new Page ( Driver.current () );
            String fileName = "./target/screenshots/" + searchPage.getClass ().getSimpleName () + "-" +new Date ().getTime () + ".png";
            currentPage.captureScreenShot ( fileName );
        }
        Driver.current ().quit ();
    }
}
