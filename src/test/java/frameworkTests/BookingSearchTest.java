package frameworkTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import pages.SearchPage;
import pages.SearchPageResults;
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
                        {"London", true, "2"}
                        ,{"Manchester", false, "1"},
                }
        );
    }

    @Test
    public void testValidSearch() throws Exception {
        //Actions
        TestCommon.EnterCustomerDetailsAndSearchForResults(this.destination, this.numberOfAdults, this.isForWork);
        TestCommon.CheckForDestinationInResults(this.destination, this.numberOfAdults, this.isForWork);

    }
}
/*
TEST-NG APPROACH
@BeforeTest,,@AfterTest
@dataProvider(name = "source")
(SAME BODY AS ABOVE)

@Test(dataProvider="source") corresponds to dataProvider annotation above
 */
