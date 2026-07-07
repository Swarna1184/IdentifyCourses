package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.identifycourses.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;
public class TC_01_SearchValidTest extends BaseTest {

    @Test
    public void searchValidKeyword() {
        HomePage home = new HomePage(driver);
        home.clickSearchBox();
        home.enterSearchKeyword("Web Development");
        SearchPage search = home.clickSearchIcon();
        boolean resultsDisplayed = search.areResultsDisplayed();
        int count = search.getCourseCount();
        logger.info("Total courses for 'Web Development': {}", count);
        Assert.assertTrue(resultsDisplayed,
                "Expected search results but none were displayed.");
        Assert.assertTrue(count > 0,
                "Course count should be > 0 for a valid keyword.");
    }
}

