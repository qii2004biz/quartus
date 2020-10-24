package cucumber.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import frameworkTests.TestCommon;
import pages.SearchPage;
import pages.SearchPageResults;
import ui.PageFactory;


public class BookSearchStepDef {
    TestCommon testCommon = new TestCommon ();
    public BookSearchStepDef() throws Exception {
    }

    @Given("A Consumer is on the Booking.com landing page")
    public void aConsumerIsOnTheBookingComLandingPage() throws Exception {
//        testCommon.setUp ();
    }

    @When("The Consumer enter checkin dates for {string} with {string} for work {string}")

    public void theConsumerEnterCheckinDates(String destination, String numberOfAdults, String isForWork) throws Exception {
        boolean forWork = (isForWork.matches ( "Yes" ) ? true : false);
        TestCommon.EnterCustomerDetailsAndSearchForResults ( destination, numberOfAdults, forWork  );
    }

    @Then("Consumer should see results for the destination of {string}")
    public void consumerShouldSeeResultsForTheDestination(String destination) throws Exception {
        SearchPageResults searchPageResults =  PageFactory.init ( SearchPageResults.class);
        searchPageResults.isTextPresent ( destination );

    }
}
