package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_05_SearchSpecial extends BaseTest {

    @Test
    public void searchSpecialCharacters() {
        HomePage home = new HomePage(driver);
        home.clickSearchBox();
        home.enterSearchKeyword("@#$%^&*");
        home.clickSearchIcon();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after special-char search: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("coursera.org"),
                "System should remain on Coursera without crashing after special-character search.");
    }
}