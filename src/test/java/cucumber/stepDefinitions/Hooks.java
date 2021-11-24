package cucumber.stepDefinitions;

import core.Configuration;
import core.Context;
import core.Driver;
import facades.webDriverManager.BrowserWebDriverSetup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.core.api.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.SearchPage;
import pages.SearchPageResults;
import ui.Page;

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
//        Context.clearCurrent ();
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
    }

    @After
    public void afterScenario(Scenario scenario) throws IOException {
        if (scenario.isFailed ()) {
            Page currentPage = new Page ( Driver.current () );
            String fileName = "./target/screenshots/" + currentPage.getClass ().getSimpleName () + "-" +new Date ().getTime () + ".png";
            currentPage.captureScreenShot ( fileName );
        }
        Driver.current ().quit ();
    }
}
