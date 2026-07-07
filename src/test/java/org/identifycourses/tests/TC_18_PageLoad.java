package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.UniversitiesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_18_PageLoad extends BaseTest {
    UniversitiesPage page;
    @Test
    public void verifyCampusPageLoad() {
        page = new UniversitiesPage(driver);
        page.goToUniversitiesPage();
        String url = page.getCurrentUrl().toLowerCase();
        String title = page.getPageTitle();
        logger.info("Campus URL : {}", url);
        logger.info("Title      : {}", title);
        Assert.assertTrue(url.contains("campus"),
                "Campus page URL incorrect");
        Assert.assertFalse(title.isEmpty(),
                "Campus page title is empty");
       logger.info("Courses for campus is loaded successfully");
    }
}