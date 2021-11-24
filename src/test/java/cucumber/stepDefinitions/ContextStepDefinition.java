package cucumber.stepDefinitions;

import core.Context;
import facades.database.MongoDBFacade;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.SearchPage;
import ui.Page;
import ui.PageFactory;
import ui.controls.Control;

public class ContextStepDefinition {
    @Given("I add {string} to context")
    public void iAddValueToContext(String value) throws Exception {
        Context.put ("string-"+System.currentTimeMillis (),value);
        Context.put ("mongoFacade-"+System.currentTimeMillis (),new MongoDBFacade () );
        PageFactory.init ( SearchPage.class );
        Context.put ("Page-"+System.currentTimeMillis (), Page.getCurrent ());
    }

    @Then("I should be able to get the context {string}")
    public void iShouldBeAbleToGetTheContext(String value) {

        System.out.println (Context.variables ().size ());
        Context.variables ().forEach ( System.out::println );

    }
}
