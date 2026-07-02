package org.identifycourses.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;
import org.identifycourses.pages.SearchPage;

public class TC_06_BeginnerFilter extends BaseTest {

    @Test
    public void verifyBeginnerFilterCourses() {

        SearchPage searchPage = new SearchPage(driver);

        // ✅ Step 1: Perform search from homepage
        searchPage.searchCourse("Web Development");

        // ✅ Step 2: Validate results loaded
        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "❌ Search results not displayed"
        );

        // ✅ Step 3: Apply Beginner filter
        searchPage.applyBeginnerFilter();

        // ✅ Step 4: Validate results again
        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "❌ Filtered results not displayed"
        );

        System.out.println("✅ TC_06 PASSED");
    }
}