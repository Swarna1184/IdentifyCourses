package org.identifycourses.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;
import org.identifycourses.pages.SearchPage;

public class TC_08_BothFilters extends BaseTest {

    @Test
    public void verifyBeginnerAndEnglishCourses() {

        SearchPage searchPage = new SearchPage(driver);


        searchPage.searchCourse("Web Development");


        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "Search results not displayed"
        );


        searchPage.applyBothFilters();


        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "Beginner + English filtered results not displayed"
        );

        System.out.println("TC_08 PASSED");
    }
}
