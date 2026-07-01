package org.identifycourses.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactUsPage {
    WebDriver driver;
    WebDriverWait wait;
        public ContactUsPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        // Locators

        @FindBy(id = "FirstName")
         WebElement txtFirstName;

        @FindBy(id = "LastName")
         WebElement txtLastName;

        @FindBy(id = "Email")
         WebElement txtEmail;

        @FindBy(id = "Phone")
         WebElement txtPhone;

        @FindBy(id = "Company")
      WebElement txtInstitutionName;

        @FindBy(xpath = "//button[@type='submit']")
         WebElement btnSubmit;

        @FindBy(xpath = "//*[contains(text(),'Must be valid email')]")
         WebElement txtErrorMessage;

        public void enterFirstName(String firstName) {
            txtFirstName.sendKeys(firstName);
        }

        public void enterLastName(String lastName) {
            txtLastName.sendKeys(lastName);
        }

        public void enterEmail(String email) {
            txtEmail.sendKeys(email);
        }

        public void enterPhone(String phone) {
            txtPhone.sendKeys(phone);
        }

        public void enterInstitutionName(String institutionName) {
            txtInstitutionName.sendKeys(institutionName);
        }

        public void clickSubmit() {
            btnSubmit.click();
        }





        public String getErrorMessage() {

            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOf(txtErrorMessage));

            return txtErrorMessage.getText();

        }
}
