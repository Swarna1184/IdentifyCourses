package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.ContactUsPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.io.IOException;

public class TC_25_SubmitButton extends BaseTest {
    @Test
    public void validateSubmitButton() throws IOException {

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
        System.out.println("Submit Button Displayed : " + page.isSubmitButtonDisplayed());
        System.out.println("Submit Button Enabled : " + page.isSubmitButtonEnabled());
        BaseTest.takeScreenShot(driver, "SubmitButton");
        Assert.assertTrue(
                page.isSubmitButtonDisplayed(),
                "Submit button is not displayed");

        Assert.assertTrue(
                page.isSubmitButtonEnabled(),
                "Submit button is disabled");
        System.out.println("Submit Button Clicked Successfully");
    }
}
