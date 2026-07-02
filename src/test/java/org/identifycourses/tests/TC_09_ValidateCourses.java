package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.identifycourses.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_09_ValidateCourses extends BaseTest {

    @Test
    public void validateMinimumTwoCoursesDisplayed() {

        HomePage home = new HomePage(driver);

        // Search for Web Development
        home.clickSearchBox();
        home.enterSearchKeyword("Web Development");

        SearchPage searchPage = home.clickSearchIcon();

        // Apply Beginner + English filters
        searchPage.applyBothFilters();

        // Get count of filtered courses
        int courseCount = searchPage.getCourseCount();

        // Validate minimum 2 courses
        Assert.assertTrue(
                courseCount >= 2,
                "Less than 2 courses found. Actual count: " + courseCount
        );

        System.out.println(
                "TC_09 PASSED - Found " + courseCount + " courses"
        );
    }
}