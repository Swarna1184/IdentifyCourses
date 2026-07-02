package org.identifycourses.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;
import org.identifycourses.pages.SearchPage;

public class TC_07_EnglishFilter extends BaseTest {

    @Test
    public void verifyEnglishFilterCourses() {

        SearchPage searchPage = new SearchPage(driver);

        // ✅ Step 1: Perform search from homepage
        searchPage.searchCourse("Web Development");

        // ✅ Step 2: Validate results loaded
        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "❌ Search results not displayed"
        );

        // ✅ Step 3: Apply English Language filter
        searchPage.applyEnglishFilter();


        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "❌ English filtered results not displayed"
        );

        System.out.println("✅ TC_07 PASSED");
    }
}