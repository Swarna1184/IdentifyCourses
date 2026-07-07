package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.identifycourses.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_02_SearchPartialTest extends BaseTest {

    @Test
    public void searchPartialKeyword() {
        HomePage home = new HomePage(driver);
        home.clickSearchBox();
        home.enterSearchKeyword("Web Deve");
        SearchPage search = home.clickSearchIcon();
        boolean resultsDisplayed = search.areResultsDisplayed();
        int count = search.getCourseCount();
        logger.info("Total courses for 'Web Deve': {}", count);
        Assert.assertTrue(resultsDisplayed,
                "Partial keyword should still show matching results.");
        Assert.assertTrue(count > 0,
                "Course count should be > 0 for partial keyword.");
    }
}

