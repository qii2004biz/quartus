package cucumber.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.NfoSmartHomePage;
import ui.PageFactory;

public class HomePage {
    private NfoSmartHomePage nfoSmartHomePage;
    @Given("Consumer is on nfoSmart url {string}")
    public void homeIsUp(String url) throws Exception {
        System.setProperty ( "url", "http://" + url );

        nfoSmartHomePage = PageFactory.init ( NfoSmartHomePage.class );
        nfoSmartHomePage.navigate ();
    }

    @When("a customer see the home page")
    public void aCustomerSeeTheHomePage() {
    }

    @Then("he should be able to register with nfoSmart")
    public void heShouldBeAbleToRegisterWithNfoSmart() {
    }
}
