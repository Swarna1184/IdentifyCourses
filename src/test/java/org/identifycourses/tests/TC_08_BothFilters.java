package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.identifycourses.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class TC_08_BothFilters extends BaseTest {

    @Test
    public void verifyBeginnerAndEnglishCourses() throws IOException {
        HomePage home = new HomePage(driver);
        home.clickSearchBox();
        home.enterSearchKeyword("Web Development");
        SearchPage searchPage = home.clickSearchIcon();
        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "Search results not displayed"
        );
        searchPage.applyBothFilters();
        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "Beginner + English filtered results not displayed"
        );
        Assert.assertTrue(
                searchPage.getCourseCount() >= 2,
                "Less than 2 courses found after applying filters"
        );
        BaseTest.takeScreenShot(driver, "BothFilters");
        System.out.println("TC_08 PASSED");
    }
}
