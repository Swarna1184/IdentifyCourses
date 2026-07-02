package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_05_SearchSpecial extends BaseTest {

    @Test(description = "TC_005: Verify search with only special characters")
    public void searchSpecialCharacters() {

        HomePage home = new HomePage(driver);

        home.clickSearchBox();
        home.enterSearchKeyword("@#$%^&*");
        home.clickSearchIcon();

        int cardCount = driver.findElements(
                By.xpath("//div[contains(@data-testid,'product-card')]")).size();

        boolean noResultsMsg = driver.findElements(
                By.xpath("//*[contains(text(),'No results') " +
                        "or contains(text(),'no results')]")).size() > 0;

        System.out.println("Course cards: " + cardCount + " | No-results msg: " + noResultsMsg);

        Assert.assertTrue(cardCount == 0 || noResultsMsg,
                "Search with only special characters should return no results or show an error.");
    }
}