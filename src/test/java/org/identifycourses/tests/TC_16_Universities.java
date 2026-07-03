package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.UniversitiesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_16_Universities extends BaseTest {
    UniversitiesPage page;

    @Test(priority = 16, description = "TC_16 - Navigate to For Universities")
    public void validateNavigationToForUniversities() {
        page = new UniversitiesPage(driver);
        page.clickForUniversities();
        page.switchToNewWindow();
        System.out.println("URL   : " + page.getCurrentUrl());
        System.out.println("Title : " + page.getPageTitle());
        Assert.assertTrue(page.isUniversitiesPageDisplayed(),
                "For Universities page not displayed");
        System.out.println("TC_16 PASSED");
    }
}