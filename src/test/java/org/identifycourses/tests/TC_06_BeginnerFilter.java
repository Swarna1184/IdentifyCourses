package org.identifycourses.tests;
import org.identifycourses.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;
import org.identifycourses.pages.SearchPage;

public class TC_06_BeginnerFilter extends BaseTest {

    @Test
    public void verifyBeginnerFilterCourses() {

        HomePage home = new HomePage(driver);
        home.clickSearchBox();
        home.enterSearchKeyword("Web Development");
        SearchPage searchPage = home.clickSearchIcon();

        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "Search results not displayed"
        );

        searchPage.applyBeginnerFilter();

        Assert.assertTrue(
                searchPage.areResultsDisplayed(),
                "Filtered results not displayed"
        );

        System.out.println("TC_06 PASSED");
    }
}