package org.identifycourses.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;
import org.identifycourses.pages.SearchPage;

import java.io.IOException;

public class TC_06_BeginnerFilter extends BaseTest {

    @Test
    public void verifyBeginnerFilterCourses() throws IOException {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchCourse("Web Development");
        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "Search results not displayed"
        );
        searchPage.applyBeginnerFilter();
        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "Filtered results not displayed"
        );
        BaseTest.takeScreenShot(driver, "BeginnerFilter");
        System.out.println("TC_06 PASSED");
    }
}