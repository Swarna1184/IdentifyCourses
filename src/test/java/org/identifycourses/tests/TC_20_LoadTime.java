package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.UniversitiesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_20_LoadTime extends BaseTest {

    UniversitiesPage page;
    @Test
    public void verifyPageLoadAndContactUsClick() {
        page = new UniversitiesPage(driver);
        long startTime = System.currentTimeMillis();
        page.goToUniversitiesPage();
        long endTime = System.currentTimeMillis();
        long loadTimeSec = (endTime - startTime) / 1000;
        logger.info("Campus URL : {}", page.getCurrentUrl());
        logger.info("Title      : {}", page.getPageTitle());
        logger.info("Load Time  : {} sec", loadTimeSec);
        Assert.assertTrue(page.getCurrentUrl().toLowerCase().contains("campus"),
                "Campus page not loaded");
        Assert.assertTrue(
                loadTimeSec <= 15,
                "Page load exceeded 15 sec");
        page.clickContactUs();
        page.switchToContactUsWindow();
        logger.info("Contact Us URL : {}", page.getCurrentUrl());
        logger.info("Contact Us Title : {}", page.getPageTitle());
        Assert.assertFalse(page.getCurrentUrl().isEmpty(),
                "Contact Us page not opened");
        logger.info("Loading time is verified successfully");
    }
}