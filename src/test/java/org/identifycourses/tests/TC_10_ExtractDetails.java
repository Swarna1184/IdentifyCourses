package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.identifycourses.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
public class TC_10_ExtractDetails extends BaseTest {

    @Test
    public void extractCourseDetailsAfterFiltering() throws IOException {

        HomePage home = new HomePage(driver);
        home.clickSearchBox();
        home.enterSearchKeyword("Web Development");
        SearchPage searchPage = home.clickSearchIcon();
        searchPage.applyBothFilters();
        Assert.assertTrue(
                searchPage.getCourseCount() >= 2,
                "Less than 2 courses found"
        );
        searchPage.extractCourseDetails();
        BaseTest.takeScreenShot(driver, "ExtractDetails");
        System.out.println("TC_10 PASSED");
    }
}
