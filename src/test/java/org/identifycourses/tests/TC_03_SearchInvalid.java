package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_03_SearchInvalid extends BaseTest {

    @Test(description = "TC_003: Verify behavior when no results are found (text + special char)")
    public void searchInvalidKeyword() {
        HomePage home = new HomePage(driver);
        home.clickSearchBox();
        home.enterSearchKeyword("Web@#Development$$");
        home.clickSearchIcon();
        boolean noResults = driver.findElements(
                By.xpath("//*[contains(text(),'No results') " +
                        "or contains(text(),'no results') " +
                        "or contains(text(),'0 results')]")).size() > 0;
        int cardCount = driver.findElements(
                By.xpath("//div[contains(@data-testid,'product-card')]")).size();
        System.out.println("Cards displayed: " + cardCount + " | No-results banner: " + noResults);
        Assert.assertTrue(noResults || cardCount == 0,
                "System should either show no results or a proper message for invalid input.");
    }
}