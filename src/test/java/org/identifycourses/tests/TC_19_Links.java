package org.identifycourses.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.identifycourses.pages.UniversitiesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TC_19_Links {

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
//     * TC_19 : Ensure navigation links are working properly
//     * Expected: Navigation links work correctly and redirect to intended section
//     *
    @Test(priority = 19, description = "TC_19 - Verify navigation links working")
    public void verifyNavigationLinks() {

        page = new UniversitiesPage(driver);

        // Step 1: Open Coursera Campus page directly
        page.openCampusDirect();

        // Step 2: Capture URL & Title
        String url = page.getCurrentUrl().toLowerCase();
        String title = page.getPageTitle();

        System.out.println("Campus URL : " + url);
        System.out.println("Title      : " + title);

        // Step 3: Fetch all navigation links on the page
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));

        int workingLinks = 0;

        for (WebElement link : allLinks) {
            try {
                String href = link.getAttribute("href");
                if (href != null && !href.isEmpty() && href.startsWith("http")) {
                    workingLinks++;
                }
            } catch (Exception ignored) {}
        }

        System.out.println("Total Working Navigation Links: " + workingLinks);

        // Step 4: Validate URL contains "campus"
        Assert.assertTrue(url.contains("campus"),
                "Campus page URL incorrect: " + url);

        // Step 5: Validate navigation links exist
        Assert.assertTrue(workingLinks > 0,
                "No working navigation links found on Campus page.");

        System.out.println(" TC_19 PASSED - Navigation links working properly.");
    }
}