package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.identifycourses.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class TC_07_EnglishFilter extends BaseTest {

    @Test
    public void verifyEnglishFilterCourses() throws IOException {
        HomePage home = new HomePage(driver);
        home.clickSearchBox();
        home.enterSearchKeyword("Web Development");
        SearchPage searchPage = home.clickSearchIcon();
        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "Search results not displayed"
        );
        searchPage.applyEnglishFilter();
        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "English filtered results not displayed"
        );
        BaseTest.takeScreenShot(driver, "EnglishFilter");
        logger.info("Successfully filtered English Level");
    }
}