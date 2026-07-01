package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.identifycourses.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_06_BeginnerFilter extends BaseTest {

    @Test(description = "TC_006 - Verify filtering section by Beginner level")
    public void verifyBeginnerFilter() {

        // ---------- Search using Pranathi's HomePage methods ----------
        HomePage homePage = new HomePage(driver);
        homePage.clickSearchBox();
        homePage.enterSearchKeyword("Web Development");
        SearchPage searchPage = homePage.clickSearchIcon();

        // ---------- Verify search results loaded ----------
        Assert.assertTrue(searchPage.areResultsDisplayed(),
                "Search results were not displayed before applying filter.");

        // ---------- Apply Beginner filter (teammate's method) ----------
        searchPage.applyBeginnerFilter();

        // ---------- Verify filtered results are visible ----------
        Assert.assertTrue(searchPage.areResultsDisplayed(),
                "No results shown after applying Beginner filter.");
        System.out.println("✅ TC_06 Passed - Beginner filter applied successfully.");
    }
}
