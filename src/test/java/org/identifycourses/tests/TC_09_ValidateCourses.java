package org.identifycourses.tests;
import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.identifycourses.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class TC_09_ValidateCourses extends BaseTest {

    @Test
    public void validateMinimumTwoCoursesDisplayed() throws IOException {

        HomePage home = new HomePage(driver);
        home.clickSearchBox();
        home.enterSearchKeyword("Web Development");
        SearchPage searchPage = home.clickSearchIcon();
        searchPage.applyBothFilters();
        int courseCount = searchPage.getCourseCount();
        Assert.assertTrue(
                courseCount >= 2,
                "Less than 2 courses found. Actual count: " + courseCount
        );
        BaseTest.takeScreenShot(driver, "ValidateCourses");
        System.out.println(
                "TC_09 PASSED - Found " + courseCount + " courses"
        );
    }
}