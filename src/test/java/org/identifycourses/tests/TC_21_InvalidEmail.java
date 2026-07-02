package org.identifycourses.tests;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseTest;
import org.identifycourses.pages.ContactUsPage;
import utilities.ConfigReader;

public class TC_21_InvalidEmail extends BaseTest {

    @Test
    public void validateInvalidEmail() {

        ContactUsPage page = new ContactUsPage(driver);

        page.enterFirstName(ConfigReader.getProperty("firstName"));
        page.enterLastName(ConfigReader.getProperty("lastName"));
        page.enterEmail(ConfigReader.getProperty("email"));
        page.enterPhone(ConfigReader.getProperty("phoneNumber"));
        page.enterInstitutionName(ConfigReader.getProperty("institutionName"));

        page.clickSubmit();

        String actualError = page.getErrorMessage();

        System.out.println("Validation Error : " + actualError);

        Assert.assertTrue(
                actualError.contains("Must be valid email"),
                "Email validation message not displayed");
    }
}