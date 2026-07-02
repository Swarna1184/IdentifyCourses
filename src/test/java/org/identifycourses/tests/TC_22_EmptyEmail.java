package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.ContactUsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;

public class TC_22_EmptyEmail extends BaseTest {

        @Test
        public void validateEmptyEmail() {
            ContactUsPage page = new ContactUsPage(driver);
            page.enterFirstName(ConfigReader.getProperty("firstName"));
            page.enterLastName(ConfigReader.getProperty("lastName"));
            page.enterEmail("");
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
            System.out.println("Validation Error: " + actualError);
            Assert.assertTrue(
                    actualError.length() > 0,
                    "Validation message was not displayed");
        }

}
