package org.identifycourses.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.identifycourses.pages.UniversitiesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_18_PageLoad {

    WebDriver driver;
    UniversitiesPage page;

    @BeforeMethod
    public void setUp() {

        ChromeOptions o = new ChromeOptions();
        o.addArguments("--remote-allow-origins=*", "--start-maximized");

        driver = new ChromeDriver(o);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
    }

    //    **
//     * TC_18 : Verify correct page load for
//     *         "Courses for Campus" section
//     * Expected: Campus page loads successfully without errors
//     *
    @Test(priority = 18, description = "TC_18 - Verify Campus page loads correctly")
    public void verifyCampusPageLoad() {

        page = new UniversitiesPage(driver);

        // Step 1: Open Coursera Campus page directly
        page.openCampusDirect();

        // Step 2: Capture URL & Title
        String url = page.getCurrentUrl().toLowerCase();
        String title = page.getPageTitle();

        System.out.println("Campus URL : " + url);
        System.out.println("Title      : " + title);

        // Step 3: Validate URL contains "campus"
        Assert.assertTrue(url.contains("campus"),
                "Campus page URL incorrect: " + url);

        // Step 4: Validate Title is not empty
        Assert.assertFalse(title.isEmpty(),
                "Campus page title is empty");

        System.out.println(" TC_18 PASSED - Campus page loaded successfully without errors.");
    }
}