package com.sample.tests;

import core.Configuration;
import core.Driver;
import facades.webDriverManager.BrowserWebDriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ui.controls.Edit;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
                        ,{"Manchest", false, 1},
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
        Edit editDestination = new Edit ( driver, By.id("ss") );

        driver.get(System.getProperty ( "url" ));
        driver.manage().window().setSize(new Dimension (819, 695));
        js.executeScript("window.scrollTo(0,0)");

        editDestination.setText ( this.destination );
    }
}
/*
TEST-NG APPROACH
@BeforeTest,,@AfterTest
@dataProvider(name = "source")
(SAME BODY AS ABOVE)

@Test(dataProvider="source") corresponds to dataProvider annotation above
 */
