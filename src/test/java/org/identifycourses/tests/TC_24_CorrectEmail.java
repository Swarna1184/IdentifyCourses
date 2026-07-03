package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.ContactUsPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.io.IOException;
import java.time.Duration;

public class TC_24_CorrectEmail extends TC_20_LoadTime {
    @Test
    public void validateCorrectdEmail() throws IOException {

        ContactUsPage page = new ContactUsPage(driver);

        page.enterFirstName(ConfigReader.getProperty("firstName"));
        page.enterLastName(ConfigReader.getProperty("lastName"));
        page.enterEmail(ConfigReader.getProperty("crtemail"));
        page.enterPhone(ConfigReader.getProperty("phoneNumber"));
        page.enterInstitutionName(ConfigReader.getProperty("institutionName"));
        page.selectCountry(ConfigReader.getProperty("country"));
        page.selectInstutionType(ConfigReader.getProperty("institutionType"));
        page.selectState(ConfigReader.getProperty("state"));
        page.selectDepartment(ConfigReader.getProperty("department"));
        page.selectJobRole(ConfigReader.getProperty("job_role"));
        page.selectNeeds(ConfigReader.getProperty("needs"));
        page.clickSubmit();
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("thank-you"));
        BaseTest.takeScreenShot(driver, "CorrectEmail");
        System.out.println("The form is submitted succesfully and the current URL"+ driver.getCurrentUrl());
        Assert.assertTrue(
                driver.getCurrentUrl().contains("thank-you"),
                "Thank You page is not displayed");


    }
}
