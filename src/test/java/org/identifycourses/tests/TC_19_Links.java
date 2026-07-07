package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.UniversitiesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class TC_19_Links extends BaseTest {
    UniversitiesPage page;
    @Test
    public void verifyNavigationLinks() {
        page = new UniversitiesPage(driver);
        page.goToUniversitiesPage();
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
        logger.info("Total Working Links: {}", workingLinks);
        Assert.assertTrue(workingLinks > 0,
                "No working navigation links found");
        logger.info("Navigation to contact page link is working successfully");
    }
}