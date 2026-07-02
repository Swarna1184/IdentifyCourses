package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_04_SearchEmpty extends BaseTest {

    @Test(description = "TC_004: Verify search with empty input field")
    public void searchEmptyKeyword() {

        HomePage home = new HomePage(driver);

        // Wait until home page fully loads before capturing initial URL
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));

        String originalUrl = driver.getCurrentUrl();

        home.clickSearchBox();
        home.enterSearchKeyword("");   // no input
        home.clickSearchIcon();        // press ENTER on empty search box

        // Wait briefly for potential navigation using URL-change condition
        try {
            wait.withTimeout(Duration.ofSeconds(5))
                    .until(ExpectedConditions.not(ExpectedConditions.urlToBe(originalUrl)));
        } catch (Exception e) {
            // Expected — URL should NOT change for empty search
        }

        String newUrl = driver.getCurrentUrl();
        System.out.println("Original URL: " + originalUrl);
        System.out.println("New URL     : " + newUrl);

        // Verify empty search did NOT navigate to a search results page
        Assert.assertFalse(newUrl.contains("search?query="),
                "Empty search should not navigate to a search results page.");
    }
}