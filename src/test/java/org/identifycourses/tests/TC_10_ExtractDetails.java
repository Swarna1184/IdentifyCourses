package org.identifycourses.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;
import org.identifycourses.pages.SearchPage;

public class TC_10_ExtractDetails extends BaseTest {

    @Test
    public void extractCourseDetailsAfterFiltering() {

        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchCourse("Web Development");
        searchPage.applyBothFilters();
        Assert.assertTrue(
                searchPage.getCourseCount() >= 2,
                "Less than 2 courses found"
        );
        searchPage.extractCourseDetails();
        System.out.println("TC_10 PASSED");
    }
}
