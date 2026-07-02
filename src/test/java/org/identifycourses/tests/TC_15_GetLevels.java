package org.identifycourses.tests;

import java.util.List;

import org.identifycourses.pages.LanguageLearningPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseTest;
import utilities.ExcelUtils;

public class TC_15_GetLevels extends BaseTest {

    @Test
    public void extractLevels() {

        LanguageLearningPage lp =
                new LanguageLearningPage(driver);

        lp.navigateToLanguageLearning();

        lp.closePopupIfPresent();

        List<String> levels =
                lp.getLevels();

        System.out.println("\n===== LEVELS =====");

        for(String level : levels) {
            System.out.println(level);
        }

        ExcelUtils.writeLevelsToExcel(levels);

        Assert.assertFalse(
                levels.isEmpty(),
                "No levels found");
    }
}