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

import java.util.Date;
import java.util.Map;

import static org.openqa.selenium.Keys.ENTER;

public class TestCommon {
    private static Map<String, Object> vars;
    private static JavascriptExecutor js;

    public TestCommon() {
    }

    protected static SearchPage searchPage;
    protected static SearchPageResults searchPageResults;

    protected static void CheckForDestinationInResults(String destination, String numberOfAdults, boolean isForWork) throws Exception {
        searchPageResults = PageFactory.init ( SearchPageResults.class );
        searchPageResults.editDestination.click ();
        searchPageResults.isTextPresent ( destination );
        String fileName = "./target/screenshots/" + searchPage.getClass ().getSimpleName () + "-" +new Date ().getTime () + ".png";
        searchPageResults.captureScreenShot ( fileName );
    }

    @Before
    public void setUp() throws Exception {
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
            cap.setCapability ( "automationName", "UiAutomator2" );
            cap.setCapability ( "browserName", "Chrome" );
            cap.setCapability ( "commandTimeout", "60" );
            Driver.add ( Configuration.get ( "driver_url" ), browser, cap );
        }
        searchPage = PageFactory.init ( SearchPage.class );
        searchPage.navigate ();

    }

    @After
    public void tearDown() {
        Driver.current ().quit ();
    }

    public static void EnterCustomerDetailsAndSearchForResults(String destination
            , String numberOfAdults
            , boolean isForWork
    ) throws Exception {
        searchPage = PageFactory.init ( SearchPage.class );
        searchPage.title.clickAndWait ( SearchPage.class );

        searchPage.editDestination.setText ( destination );
        searchPage.editDestination.element ().sendKeys ( ENTER );
        searchPage.selectCheckinCheckOutDate ();
        searchPage.selectAdultNumber.selectByText ( String.valueOf ( numberOfAdults ) );
        searchPage.selectForWork ( isForWork );
        searchPage.searchButton.click ();

    }
}
