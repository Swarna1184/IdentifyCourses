package org.identifycourses.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;
import org.identifycourses.pages.LanguageLearningPage;
import utilities.ExcelUtils;
import java.util.List;

public class TC_13_GetLanguages extends BaseTest {

    @Test
    public void extractLanguages() {
        LanguageLearningPage langPage = new LanguageLearningPage(driver);
        langPage.navigateToLanguageLearning();
        langPage.closePopupIfPresent();
        String url = driver.getCurrentUrl();
        System.out.println("Current URL: " + url);
        List<String> languages = langPage.getLanguages();
        System.out.println("Languages Found: " + languages);
        Assert.assertFalse(
                languages.isEmpty(),
                "Languages list is EMPTY! Extraction Failed");
        ExcelUtils.writeLanguagesToExcel(languages);
    }
}