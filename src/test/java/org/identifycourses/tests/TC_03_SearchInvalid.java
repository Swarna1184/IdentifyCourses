package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_03_SearchInvalid extends BaseTest {

    @Test
    public void searchInvalidKeyword() {
        HomePage home = new HomePage(driver);
        home.clickSearchBox();
        home.enterSearchKeyword("Web@#Development$$");
        home.clickSearchIcon();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("search"));
        String currentUrl = driver.getCurrentUrl();
        logger.info("Search URL: {}", currentUrl);
        Assert.assertTrue(currentUrl.contains("search"),
                "URL should navigate to search results page after search execution.");
    }
}