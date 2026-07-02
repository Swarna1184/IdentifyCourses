package org.identifycourses.tests;
import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.identifycourses.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_07_EnglishFilter extends BaseTest {
    @Test
    public void verifyEnglishFilterCourses() {

        HomePage home = new HomePage(driver);

        // Search for Web Development
        home.clickSearchBox();
        home.enterSearchKeyword("Web Development");

        SearchPage searchPage = home.clickSearchIcon();

        // Validate search results
        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "Search results not displayed"
        );

        // Apply English filter
        searchPage.applyEnglishFilter();

        // Validate filtered results
        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "English filtered results not displayed"
        );

        System.out.println("TC_07 PASSED");
    }
}