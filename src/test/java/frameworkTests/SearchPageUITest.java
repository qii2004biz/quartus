package frameworkTests;

import org.junit.Assert;
import org.junit.Test;

public class SearchPageUITest extends TestCommon {
    public SearchPageUITest () {}
     @Test
    public void testVerifyUIOnSearchPage() {
         Assert.assertTrue (searchPage.editDestination.exists ());
         Assert.assertTrue (searchPage.searchButton.exists ());
         Assert.assertTrue (searchPage.chooseCheckOutDate.exists ());
         Assert.assertTrue (searchPage.searchButton.exists ());
         Assert.assertTrue (searchPage.forWork.exists ());
         Assert.assertTrue (searchPage.checkOut.exists ());
         Assert.assertTrue (searchPage.chooseCheckInDate.exists ());
         Assert.assertTrue (searchPage.selectAdultNumber.exists ());
     }
}
