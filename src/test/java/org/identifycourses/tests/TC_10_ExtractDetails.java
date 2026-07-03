package org.identifycourses.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;
import org.identifycourses.pages.SearchPage;

import java.io.IOException;

public class TC_10_ExtractDetails extends BaseTest {

    @Test
    public void extractCourseDetailsAfterFiltering() throws IOException {

        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchCourse("Web Development");
        searchPage.applyBothFilters();
        Assert.assertTrue(
                searchPage.getCourseCount() >= 2,
                "Less than 2 courses found"
        );
        BaseTest.takeScreenShot(driver, "ExtractDetails");
        searchPage.extractCourseDetails();
        System.out.println("TC_10 PASSED");
    }
}
