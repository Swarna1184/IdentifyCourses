package org.identifycourses.tests;

import org.identifycourses.pages.LanguageLearningPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;

public class TC_12_CountLanguages extends BaseTest {

    @Test
    public void countLanguages() {
        LanguageLearningPage lp = new LanguageLearningPage(driver);
        lp.navigateToLanguageLearning();
        lp.closePopupIfPresent();
        int count = lp.getLanguageCount();
        System.out.println("Total Languages Available : " + count);
        Assert.assertTrue(count > 0,
                "No languages were extracted.");
    }
}