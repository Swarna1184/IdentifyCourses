package org.identifycourses.tests;

import basetest.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.identifycourses.pages.UniversitiesPage;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_16_Universities extends BaseTest {

    @Test
    public void validateNavigationToForUniversities() {
        UniversitiesPage universitiesPage = new UniversitiesPage(driver);
        universitiesPage.clickForUniversities();
        universitiesPage.switchToNewWindow();
        String currentUrl = universitiesPage.getCurrentUrl();
        String pageTitle  = universitiesPage.getPageTitle();
        System.out.println("Navigated URL  : " + currentUrl);
        System.out.println("Page Title     : " + pageTitle);
        Assert.assertTrue(
                currentUrl.toLowerCase().contains("campus") || currentUrl.toLowerCase().contains("universities"),
                "Navigation to 'For Universities' failed. Current URL: " + currentUrl
        );
        Assert.assertTrue(
                universitiesPage.isUniversitiesPageDisplayed(),
                "'For Universities' page header is not displayed."
        );
        System.out.println("TC_16 PASSED - Successfully navigated to 'For Universities' section.");

    }
}