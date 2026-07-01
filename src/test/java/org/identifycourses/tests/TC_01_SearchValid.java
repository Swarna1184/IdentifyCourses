package org.identifycourses.tests;

import basetest.BaseTest;
import org.apache.logging.log4j.Logger;
import org.identifycourses.pages.HomePage;
import org.identifycourses.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ExtentReportManager;
import utilities.LoggerUtil;

public class TC_01_SearchValid extends BaseTest {

    private static final Logger log = LoggerUtil.getLogger(TC_01_SearchValid.class);

    @Test(description = "TC_001 - Verify search results for a valid keyword 'Web Development'")
    public void verifySearchWithValidKeyword() {

        ExtentReportManager.createTest("TC_001 - Search with valid keyword");
        log.info("Starting TC_001 - Search with valid keyword 'Web Development'");

        // Step 1: Coursera URL is already opened from BaseTest
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getTitle().toLowerCase().contains("coursera"),
                "Coursera home page did not load.");
        log.info("Coursera home page loaded successfully.");

        // Step 2: Click on the search box
        homePage.clickSearchBox();
        log.info("Clicked on the search box.");

        // Step 3: Enter the keyword 'Web Development'
        String keyword = "Web Development";
        homePage.enterSearchKeyword(keyword);
        log.info("Entered keyword: " + keyword);

        // Step 4: Click the search icon
        SearchPage searchPage = homePage.clickSearchIcon();
        log.info("Clicked the search icon.");

        // Expected Result: Relevant search results are displayed
        boolean resultsDisplayed = searchPage.areResultsDisplayed();
        int totalResults = searchPage.getResultsCount();

        log.info("Results displayed: " + resultsDisplayed + " | Total cards: " + totalResults);
        log.info("Current URL after search: " + searchPage.getCurrentUrl());

        Assert.assertTrue(resultsDisplayed,
                "Expected search results were NOT displayed for keyword: " + keyword);
        Assert.assertTrue(searchPage.getCurrentUrl().toLowerCase().contains("search"),
                "URL did not navigate to the search results page.");

        log.info("TC_001 Passed - Search results displayed successfully.");
    }
}