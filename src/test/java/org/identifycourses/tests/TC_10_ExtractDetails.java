package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.identifycourses.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_10_ExtractDetails extends BaseTest {

    @Test
    public void extractCourseDetailsAfterFiltering() {

        HomePage home = new HomePage(driver);

        // Search for Web Development
        home.clickSearchBox();
        home.enterSearchKeyword("Web Development");

        SearchPage searchPage = home.clickSearchIcon();

        // Apply Beginner + English filters
        searchPage.applyBothFilters();

        // Validate minimum 2 courses are displayed
        Assert.assertTrue(
                searchPage.getCourseCount() >= 2,
                "Less than 2 courses found"
        );

        // Extract course details
        searchPage.extractCourseDetails();

        System.out.println("TC_10 PASSED");
    }
}
