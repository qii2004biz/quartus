package cucumber.stepDefinitions;

import core.Driver;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import ui.Page;
import ui.controls.Control;
import ui.controls.Edit;
import ui.controls.TableView;

import java.util.List;
import java.util.Map;

public class CommonStepDefinitions {
    @Given("I am on the {string} (page|screen)")
    @When("(I |)go to the {string} (page|screen)")
    public void navigateToPage(String name) throws Exception {
        Page target = Page.screen ( name );
        Assert.assertNotNull ( "Unable to find the '" + name + "' page", target );
        target.navigate ();
        verifyCurrentPage ( name );
    }

    @When("I |(click|tap) on the {string} (button|element|control)")
    public void clickOnTheButton ( String name ) throws Exception {
        Control control = Page.getCurrent ().onPage ( name );
        Assert.assertNotNull ( "Unable to find '" + name + "' element on current page", control );
        control.click ();
    }

    @When("(I |)enter {string} into the {string} field")
    public void enterValue ( String text, String fieldName ) throws Exception {
        Edit control = (Edit) verifyElementExists ( fieldName );
        control.setText ( text );
    }

    @When("(I |)accept the alert message")
    public void acceptAlert() {
        Driver.current ().switchTo ().alert ().accept ();
    }

    @When("(I |)populate current page with the following data:")
    public void populatePageWithData(DataTable data) throws Exception {
        List<Map<String, String>> content = data.asMaps ( String.class, String.class );
        for (Map<String, String> row : content ) {
            enterValue ( row.get("Value"), row.get("Field") );
        }
    }

    @When("(I should see |)the page contains the following data:")
    public void pageContainsData(DataTable data) throws Exception {
        List<Map<String, String>> content = data.asMaps ( String.class, String.class );
        for (Map<String, String> row : content ) {
            verifyFieldText ( row.get("Field"), row.get("Value") );
        }
    }

    @Then("(I should see)the {string} field is available")
    public Control verifyElementExists ( String fieldName ) throws Exception {
        Control control = Page.getCurrent ().onPage ( fieldName );
        Assert.assertNotNull ( "Unable to find '" + fieldName + "' element on current page", control );
        Assert.assertTrue ( "Element '" + fieldName + "' is not available", control.exists () );
        return control;
    }

    @Then("(I should see |)the {string} field contains the {string} text")
    public Control verifyFieldText ( String fieldName, String text ) throws Exception {
        Control control = verifyElementExists( fieldName );
        String actualText = control.getText ();
        Assert.assertTrue ( String.format("The '%s' field has unexpected text. Expected: '%s', Actual: '%s'"
                        , fieldName
                        , text
                        , actualText)
                        , text.equals(actualText) || actualText.contains ( text ));
        return control;
    }

    @Then("I should see {string} (page|screen)")
    public void verifyCurrentPage(String name) throws Exception {
        Page target = Page.screen ( name );
        Assert.assertTrue ("The '" + name + "' screen is not current", target.isCurrent ());
        Page.setCurrent ( target );
    }
    @Then("(I should see |)the {string} text is shown")
    public void verifyTextPresent(String text ) {
        Assert.assertTrue ( "Unable to find text: '" + text + "'"
        , Page.getCurrent ().isTextPresent ( text ) );
    }

    @Then("(I should see |)the following fields are shown:")
    public void verifyMultipleFieldsAvailability(List<String> elements ) throws Exception {
        for (String element : elements ) {
            verifyElementExists ( element );
        }
    }

    @Then("(I should see |)the following labels are shown:")
    public void verifyMultipleLabelsAvailability(List<String> elements ) throws Exception {
        for (String element : elements ) {
            verifyElementExists ( element );
        }
    }

    @Then("(I should see |)the {string} list is (|not ) empty")
    public void verifyListEmptyState(String list, String emptyState )  throws Exception {
        boolean empty = emptyState.trim().equals ( "" );
        TableView control = ( TableView ) verifyElementExists ( list );
        if (empty) {
            Assert.assertTrue ( "The '" + list + "' is not empty", control.isEmpty () );
        } else {
            Assert.assertTrue ( "The '" + list + "' is empty", control.isNotEmpty () );

        }
    }
    @Then("(I should see |)the (first|last) (row|item) of the {string} (list|table)" +
            "contains the following data:")
    public void verifyListRowData (String firstLast, String list, DataTable data) throws Exception{
        int index = 0;
        TableView control = ( TableView ) verifyElementExists ( list );
        if (firstLast.equals ( "last" )) {
            index = control.getItemsCount () - 1;
        }
        List<Map<String, String>> content = data.asMaps ( String.class, String.class );
        for (Map<String, String> row : content) {
            for (Map.Entry<String, String> entry : row.entrySet ()) {
                String key = entry.getKey ();
                String value = entry.getValue (  );
                Assert.assertEquals (
                        String.format ( "The %s row element '%s' has unexpected value", firstLast, key),
                                value, control.getSubItem ( key, index ).getText ());
            }
        }
    }
    @When("(I |)(click|tap) on the (first|last) {string} element of the {string} (list|table)")
    public void clickOnSubItem(String firstLast, String item, String list) throws Exception {
        int index = 0;
        TableView control = (TableView) verifyElementExists ( list );
        if (firstLast.equals ( "last" )) {
            index = control.getItemsCount () - 1;
        }
        control.getSubItem ( item, index ).click ();
    }

}
