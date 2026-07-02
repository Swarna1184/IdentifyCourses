package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.identifycourses.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_08_BothFilters extends BaseTest {

    @Test
    public void verifyBeginnerAndEnglishCourses() {

        HomePage home = new HomePage(driver);

        // Search Web Development
        home.clickSearchBox();
        home.enterSearchKeyword("Web Development");

        SearchPage searchPage = home.clickSearchIcon();

        // Validate search results
        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "Search results not displayed"
        );

        // Apply Beginner + English filters
        searchPage.applyBothFilters();

        // Validate filtered results
        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "Beginner + English filtered results not displayed"
        );

        // Optional stronger validation
        Assert.assertTrue(
                searchPage.getCourseCount() >= 2,
                "Less than 2 courses found after applying filters"
        );

        System.out.println("TC_08 PASSED");
    }
}
