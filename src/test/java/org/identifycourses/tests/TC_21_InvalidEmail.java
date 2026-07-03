package org.identifycourses.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;
import org.identifycourses.pages.ContactUsPage;
import utilities.ConfigReader;

import java.io.IOException;

public class TC_21_InvalidEmail extends TC_20_LoadTime {
    @Test
    public void validateInvalidEmail() throws IOException {
        ContactUsPage page = new ContactUsPage(driver);
        page.enterFirstName(ConfigReader.getProperty("firstName"));
        page.enterLastName(ConfigReader.getProperty("lastName"));
        page.enterEmail(ConfigReader.getProperty("email"));
        page.enterPhone(ConfigReader.getProperty("phoneNumber"));
        page.enterInstitutionName(ConfigReader.getProperty("institutionName"));
        page.selectCountry(ConfigReader.getProperty("country"));
        page.selectInstutionType(ConfigReader.getProperty("institutionType"));
        page.selectState(ConfigReader.getProperty("state"));
        page.selectDepartment(ConfigReader.getProperty("department"));
        page.selectJobRole(ConfigReader.getProperty("job_role"));
        page.selectNeeds(ConfigReader.getProperty("needs"));
        page.clickSubmit();
        String actualError = page.getErrorMessage();
        System.out.println("Validation Error : " + actualError);
        BaseTest.takeScreenShot(driver, "InvalidEmail");
        Assert.assertTrue(
                actualError.contains("Please enter your work email address"),
                "Email validation message not displayed");

    }
}