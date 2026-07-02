package org.identifycourses.tests;

import org.identifycourses.pages.LanguageLearningPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;

public class TC_11_NavigateLanguages extends BaseTest {

    @Test
    public void navigateLanguageLearning() {
        LanguageLearningPage languagePage = new LanguageLearningPage(driver);
        languagePage.navigateToLanguageLearning();
        languagePage.closePopupIfPresent();
        System.out.println("Current URL : " + driver.getCurrentUrl());
        Assert.assertTrue(
                driver.getCurrentUrl().contains("language-learning"),
                "Navigation to Language Learning failed");
        System.out.println(
                "Successfully navigated to Language Learning page");
    }
}