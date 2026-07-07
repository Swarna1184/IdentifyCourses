package org.identifycourses.tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseTest;
import org.identifycourses.pages.ContactUsPage;
import utilities.ConfigReader;

import java.io.IOException;

public class Tc_23_ErrorMessageDisplay extends  TC_20_LoadTime{

        @Test
        public void validateErrorMessageDisplay() throws IOException {
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
            logger.info("Displayed Error Message: {}", actualError);
            BaseTest.takeScreenShot(driver, "ErrorMessage");
            Assert.assertEquals(
                    actualError,
                    "Please enter your work email address",
                    "Incorrect error message displayed");
        }


}
