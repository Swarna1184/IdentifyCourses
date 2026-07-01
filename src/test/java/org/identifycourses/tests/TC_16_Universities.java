package org.identifycourses.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.identifycourses.pages.UniversitiesPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_16_Universities {

    WebDriver driver;
    UniversitiesPage universitiesPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }

    /**
     * TC_16 : To validate that user can navigate to "For Universities" section
     * Steps:
     *   1. Visit the URL of Coursera
     *   2. Navigate to "For Universities" section
     * Expected:
     *   URL should navigate correctly to the "For Universities" section
     */
    @Test(priority = 16, description = "TC_16 - Validate navigation to For Universities section")
    public void validateNavigationToForUniversities() {

        universitiesPage = new UniversitiesPage(driver);

        // Step 1: Open Coursera home page
        universitiesPage.openCourseraHome();
        System.out.println("✅ Coursera home page opened: " + universitiesPage.getCurrentUrl());

        // Step 2: Scroll to footer and click "For Universities" link
        universitiesPage.clickForUniversities();

        // Handle new tab if opened
        universitiesPage.switchToNewWindow();

        // Step 3: Capture URL & title after navigation
        String currentUrl = universitiesPage.getCurrentUrl();
        String pageTitle  = universitiesPage.getPageTitle();

        System.out.println("Navigated URL  : " + currentUrl);
        System.out.println("Page Title     : " + pageTitle);

        // Step 4: Validate URL contains 'campus' (For Universities = Coursera for Campus)
        Assert.assertTrue(
                currentUrl.toLowerCase().contains("campus") || currentUrl.toLowerCase().contains("universities"),
                "❌ Navigation to 'For Universities' failed. Current URL: " + currentUrl
        );

        // Step 5: Validate page header is displayed
        Assert.assertTrue(
                universitiesPage.isUniversitiesPageDisplayed(),
                "❌ 'For Universities' page header is not displayed."
        );

        System.out.println("✅ TC_16 PASSED - Successfully navigated to 'For Universities' section.");
    }

    /*@AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }*/
}