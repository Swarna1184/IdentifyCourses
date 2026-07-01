package org.identifycourses.tests;

import org.testng.annotations.Test;
import basetest.BaseTest;
import org.identifycourses.pages.LanguageLearningPage;

public class TC_11_NavigateLanguages extends BaseTest {

    @Test
    public void navigateToLanguageLearning() {
        LanguageLearningPage langPage = new LanguageLearningPage(driver);
        langPage.openLanguageLearning();
        System.out.println("Navigation to Language Learning page successful");
    }
}
