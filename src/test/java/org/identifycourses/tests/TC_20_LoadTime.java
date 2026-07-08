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
        System.out.println("Campus URL : " + page.getCurrentUrl());
        System.out.println("Title      : " + page.getPageTitle());
        System.out.println("Load Time  : " + loadTimeSec + " sec");
        Assert.assertTrue(page.getCurrentUrl().toLowerCase().contains("campus"),
                "Campus page not loaded");
        Assert.assertTrue(
                loadTimeSec <= 15,
                "Page load exceeded 15 sec");
        page.clickContactUs();
        page.switchToContactUsWindow();
        System.out.println("Contact Us URL : " + page.getCurrentUrl());
        System.out.println("Contact Us Title : " + page.getPageTitle());
        Assert.assertFalse(page.getCurrentUrl().isEmpty(),
                "Contact Us page not opened");
        System.out.println("TC_20 PASSED");
    }
}