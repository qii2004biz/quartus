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

import java.util.*;

import static org.openqa.selenium.Keys.ENTER;

@RunWith(Parameterized.class)
public class BookingSearchTest extends TestCommon {
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

    @Test
    public void testValidSearch() throws Exception {
        //Actions
        searchPage = PageFactory.init ( SearchPage.class );
        searchPage.editDestination.setText ( this.destination );
        searchPage.editDestination.element ().sendKeys ( ENTER );
        searchPage.checkIn ();
        searchPage.selectAdultNumber.selectByText ( String.valueOf ( this.numberOfAdults ) );
        searchPage.selectForWork ( this.isForWork );
        searchPage.searchButton.click ();

        searchPageResults = PageFactory.init ( SearchPageResults.class );
        searchPageResults.editDestination.click ();
        searchPageResults.isTextPresent ( destination );
        String fileName = "./target/screenshots/" + searchPage.getClass ().getSimpleName () + "-" +new Date().getTime () + ".png";
        searchPageResults.captureScreenShot ( fileName );
    }
}
/*
TEST-NG APPROACH
@BeforeTest,,@AfterTest
@dataProvider(name = "source")
(SAME BODY AS ABOVE)

@Test(dataProvider="source") corresponds to dataProvider annotation above
 */
