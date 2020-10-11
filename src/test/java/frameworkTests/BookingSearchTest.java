package frameworkTests;

import core.Configuration;
import core.Driver;
import facades.webDriverManager.BrowserWebDriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.SearchPage;
import pages.SearchPageResults;
import ui.Page;
import ui.PageFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.Keys.ENTER;

@RunWith(Parameterized.class)
public class BookingSearchTest {
    WebDriver driver;
    private String destination;
    private boolean isForWork;
    private String numberOfAdults;
    private SearchPage searchPage;
    private SearchPageResults searchPageResults;

    public BookingSearchTest(String destination, boolean isForWork, String numberOfAdults) {
        this.destination = destination;
        this.isForWork = isForWork;
        this.numberOfAdults = numberOfAdults;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(
                new Object[][] {
                        {"London", true, "2 adults"}
                        ,{"Manchester", false, "1 adult"},
                }
        );
    }

    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() throws Exception {
        Configuration.load ();
        Configuration.print ();

        String browser = System.getProperty ( "browser" );
        BrowserWebDriverSetup.setupBrowserWebDriver ();

        DesiredCapabilities cap = new DesiredCapabilities (  );
        Driver.add (browser, cap);


        driver = Driver.current ();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object> ();

        js.executeScript("window.scrollTo(0,0)");

        searchPage = PageFactory.init ( SearchPage.class);
        searchPage.navigate ();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void testValidSearch() throws Exception {
        //Actions
        searchPage.editDestination.setText ( this.destination );
        searchPage.editDestination.element ().sendKeys ( ENTER );
        searchPage.checkIn ();
        searchPage.selectAdultNumber.selectByText ( String.valueOf ( this.numberOfAdults ) );
        searchPage.selectForWork ( this.isForWork );
        searchPage.searchButton.click ();

        searchPageResults = PageFactory.init ( SearchPageResults.class );
        searchPageResults.editDestination.click ();
        searchPageResults.isTextPresent ( destination );
    }
}
/*
TEST-NG APPROACH
@BeforeTest,,@AfterTest
@dataProvider(name = "source")
(SAME BODY AS ABOVE)

@Test(dataProvider="source") corresponds to dataProvider annotation above
 */
