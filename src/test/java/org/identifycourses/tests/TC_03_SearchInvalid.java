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

    @Test(description = "TC_003: Verify system behavior with text + special characters")
    public void searchInvalidKeyword() {

        HomePage home = new HomePage(driver);

        home.clickSearchBox();
        home.enterSearchKeyword("Web@#Development$$");
        home.clickSearchIcon();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait until URL contains the search query — proves search was executed
        wait.until(ExpectedConditions.urlContains("search"));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Search URL: " + currentUrl);

        // Verify search executed successfully (URL changed to search results page)
        Assert.assertTrue(currentUrl.contains("search"),
                "URL should navigate to search results page after search execution.");
    }
}