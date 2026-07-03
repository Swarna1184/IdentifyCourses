package org.identifycourses.tests;

import org.identifycourses.pages.LanguageLearningPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;

public class TC_14_CountLevels extends BaseTest {

    @Test
    public void countLevels() {
        LanguageLearningPage lp = new LanguageLearningPage(driver);
        lp.navigateToLanguageLearning();
        lp.closePopupIfPresent();
        int count = lp.getLevelCount();
        System.out.println("Total Levels Available : " + count);
        Assert.assertTrue(
                count > 0,
                "No levels were extracted");
    }
}