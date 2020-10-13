package frameworkTests;

import core.Configuration;
import core.Driver;
import facades.webDriverManager.BrowserWebDriverSetup;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.SearchPage;
import pages.SearchPageResults;
import ui.PageFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TestCommon {
    private Map<String, Object> vars;
    JavascriptExecutor js;

    public TestCommon () {}

    protected SearchPage searchPage;
    protected SearchPageResults searchPageResults;

    @Before
    public void setUp() throws Exception {
        js = (JavascriptExecutor) Driver.current ();

        Configuration.load ();
        Configuration.print ();

        String browser = System.getProperty ( "browser" );
        BrowserWebDriverSetup.setupBrowserWebDriver ();

        DesiredCapabilities cap = new DesiredCapabilities (  );
//        cap.setCapability ( "platformName", "Android" );
//        cap.setCapability ( "app", new File (Configuration.get ( "app_path" )).getAbsolutePath () );
//        cap.setCapability ( "deviceName", "Any" );
//        cap.setCapability ( "commandTimeout", "60" );


        if (Configuration.platform ().isWeb ()) {
            Driver.add (browser, cap);
        } else {
            Driver.add (Configuration.get ( "driver_url" ), browser, cap);
        }

//        js.executeScript("window.scrollTo(0,0)");

        searchPage = PageFactory.init ( SearchPage.class);
        searchPage.navigate ();
    }
    @After
    public void tearDown() {
        Driver.current ().quit();
    }
}
