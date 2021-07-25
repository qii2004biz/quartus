package frameworkTests;

import org.junit.Assert;
import org.junit.Test;
import ui.controls.Control;

public class SearchPageUITest extends TestCommon {
    public SearchPageUITest () {}
     @Test
    public void testVerifyUIOnSearchPage() throws Exception {
         Assert.assertTrue (searchPage.editDestination.exists ());
         Assert.assertTrue (searchPage.searchButton.exists ());
//         Assert.assertTrue (searchPage.chooseCheckOutDate.exists ());
         Assert.assertTrue (searchPage.searchButton.exists ());
         Assert.assertTrue (searchPage.forWork.exists ());
//         Assert.assertTrue (searchPage.chooseCheckInDate.exists ());
         Assert.assertTrue (searchPage.selectAdultNumber.exists ());

         Assert.assertTrue ( searchPage.allElementsAreExists ( new Control[]  {
             searchPage.editDestination
                 ,searchPage.title
                 ,searchPage.searchButton
                 ,searchPage.forWork
         }));
     }
}
