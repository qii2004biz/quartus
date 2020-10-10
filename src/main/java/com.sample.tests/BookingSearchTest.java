package com.sample.tests;

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
import ui.Page;
import ui.controls.Control;
import ui.controls.Edit;
import ui.controls.SelectList;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.Keys.ENTER;

@RunWith(Parameterized.class)
public class BookingSearchTest {
    WebDriver driver;
    private String destination;
    private boolean isLeisure;
    private int numberOfAdults;

    public BookingSearchTest(String destination, boolean isLeisure, int numberOfAdults) {
        this.destination = destination;
        this.isLeisure = isLeisure;
        this.numberOfAdults = numberOfAdults;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(
                new Object[][] {
                        {"London", true, 2}
                        ,{"Manchester", false, 1},
                }
        );
    }

    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() throws Exception {
        Configuration.load ();
        Configuration.print ();

//        String baseurl = System.getProperty ( "url" );
        String browser = System.getProperty ( "browser" );
        BrowserWebDriverSetup.setupBrowserWebDriver ();

        DesiredCapabilities cap = new DesiredCapabilities (  );
        Driver.add (browser, cap);


        driver = Driver.current ();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object> ();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void testValidSearch() {
        //Declarations
        Page pageValue = new Page ( driver );
        Edit editDestination = new Edit ( pageValue, By.id("ss") );

        Control SearchButton   = new Control(pageValue, By.cssSelector("button.sb-searchbox__button"));
        Control chooseCheckInDate = new Control(pageValue, By.cssSelector("table td.bui-calendar__date[data-date='2020-10-16']") );

        Control checkOut = new Control(pageValue, By.cssSelector("label.sb-searchbox__label[for='checkout_monthday']") );
        Control chooseCheckOutDate = new Control(pageValue, By.cssSelector("table td.bui-calendar__date[data-date='2020-10-18']") );

        SelectList selectAdultNumber = new SelectList ( pageValue, By.id("group_adults") );

        driver.get(System.getProperty ( "url" ));
        driver.manage().window().setSize(new Dimension (950, 1050));
        js.executeScript("window.scrollTo(0,0)");
        //Actions
        editDestination.setText ( this.destination );
        editDestination.element ().sendKeys ( ENTER );

        chooseCheckInDate.click();
        checkOut.click ();
        chooseCheckOutDate.click();
        selectAdultNumber.selectByText ( String.valueOf ( "1 adult" ) );
        SearchButton.click ();
    }
}
/*
TEST-NG APPROACH
@BeforeTest,,@AfterTest
@dataProvider(name = "source")
(SAME BODY AS ABOVE)

@Test(dataProvider="source") corresponds to dataProvider annotation above
 */
