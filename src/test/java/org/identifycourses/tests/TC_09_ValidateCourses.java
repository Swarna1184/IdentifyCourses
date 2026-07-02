package org.identifycourses.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;
import org.identifycourses.pages.SearchPage;

import java.io.IOException;

public class TC_09_ValidateCourses extends BaseTest {

    @Test
    public void validateMinimumTwoCoursesDisplayed() throws IOException {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchCourse("Web Development");
        searchPage.applyBothFilters();
        int courseCount = searchPage.getCourseCount();
        Assert.assertTrue(
                courseCount >= 2,
                "Less than 2 courses found. Actual count: " + courseCount
        );
        BaseTest.takeScreenShot(driver, "ValidateCourse");
        System.out.println(
                "TC_09 PASSED - Found " + courseCount + " courses"
        );
    }
}
