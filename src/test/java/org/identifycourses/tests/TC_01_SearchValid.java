package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.identifycourses.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_01_SearchValid extends BaseTest {

    @Test
    public void searchValidKeyword() {
        HomePage home = new HomePage(driver);
        // Step 1: URL already opened in BaseTest
        // Step 2: Click search box
        home.clickSearchBox();
        home.enterSearchKeyword("Web Development");
        SearchPage search = home.clickSearchIcon();
        boolean resultsDisplayed = search.areResultsDisplayed();
        int count = search.getCourseCount();
        System.out.println("Total courses for 'Web Development': " + count);
        Assert.assertTrue(resultsDisplayed,
                "Expected search results but none were displayed.");
        Assert.assertTrue(count > 0,
                "Course count should be > 0 for a valid keyword.");
    }
}