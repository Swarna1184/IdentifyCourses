package org.identifycourses.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;
import org.identifycourses.pages.LanguageLearningPage;
import utilities.ExcelUtils;

import java.util.List;

public class TC_12_GetLanguages extends BaseTest {

    @Test
    public void extractLanguages() {

        LanguageLearningPage langPage = new LanguageLearningPage(driver);
        langPage.openLanguageLearning();
        langPage.closePopupIfPresent();
        String url = driver.getCurrentUrl();
        System.out.println("Current URL: " + url);
        List<String> languages = langPage.getLanguages();
        System.out.println("Languages Found: " + languages);
        ExcelUtils.writeLanguagesToExcel(languages);
        Assert.assertTrue(
                languages.size() > 0,
                "Languages list is EMPTY! Extraction Failed "
        );

        System.out.println("Languages extracted successfully ");
    }
}
