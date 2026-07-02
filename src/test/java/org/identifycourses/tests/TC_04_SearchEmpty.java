package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_04_SearchEmpty extends BaseTest {

    @Test(description = "TC_004: Verify search with empty input field")
    public void searchEmptyKeyword() {

        HomePage home = new HomePage(driver);
        String originalUrl = driver.getCurrentUrl();

        home.clickSearchBox();
        home.enterSearchKeyword("");     // no keyword
        home.clickSearchIcon();          // ENTER on empty box

        String newUrl = driver.getCurrentUrl();
        System.out.println("Original URL: " + originalUrl);
        System.out.println("New URL     : " + newUrl);

        boolean stayedOnHome = newUrl.equalsIgnoreCase(originalUrl)
                || !newUrl.contains("search?query=");

        Assert.assertTrue(stayedOnHome,
                "Empty search should not navigate to a valid search results page.");
    }
}