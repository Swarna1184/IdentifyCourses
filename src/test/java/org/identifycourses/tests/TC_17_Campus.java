package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.UniversitiesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_17_Campus extends BaseTest {
    UniversitiesPage page;
    @Test
    public void verifyCampusSectionVisible() {
        page = new UniversitiesPage(driver);
        page.goToUniversitiesPage();
        String url = page.getCurrentUrl().toLowerCase();
        String title = page.getPageTitle();
        logger.info("Campus URL : {}", url);
        logger.info("Title      : {}", title);
        Assert.assertTrue(url.contains("campus") || url.contains("universities"),
                "Courses for Campus section not displayed");
        Assert.assertFalse(title.isEmpty(),
                "Campus page title is empty");
        logger.info("Courses for campus section is displayed successfully");
    }
}