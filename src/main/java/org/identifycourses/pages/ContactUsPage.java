package org.identifycourses.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage extends CommonCode {

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

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

    @FindBy(id = "ValidMsgEmail")
    WebElement txtErrorMessage;

    @FindBy(xpath = "//select[@id='Country']")
    WebElement drpCountry;

    @FindBy(id = "State")
    WebElement drpState;

    @FindBy(id = "Institution_Type__c")
    WebElement drpInstitutionType;

    @FindBy(id = "Self_Reported_Needs__c")
    WebElement drpNeeds;

    @FindBy(id = "Title")
    WebElement drpJobRole;

    @FindBy(id = "Department")
    WebElement drpDepartment;

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

    public void selectInstutionType(String institutionType) {
        Select select = new Select(drpInstitutionType);
        select.selectByVisibleText(institutionType);
    }

    public void selectJobRole(String jobRole) {
        Select select = new Select(drpJobRole);
        select.selectByVisibleText(jobRole);
    }

    public void selectDepartment(String department) {
        Select select = new Select(drpDepartment);
        select.selectByVisibleText(department);
    }

    public void selectNeeds(String needs) {
        Select select = new Select(drpNeeds);
        select.selectByVisibleText(needs.trim());
    }

    public void selectCountry(String country) {
        clickElement(drpCountry);
        WebElement countryOption =
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[text()='" + country + "']")));
        clickElement(countryOption);
    }

    public void selectState(String state) {
        Select select = new Select(drpState);
        select.selectByVisibleText(state);
    }

    public boolean isSubmitButtonDisplayed() {
        return btnSubmit.isDisplayed();
    }

    public boolean isSubmitButtonEnabled() {
        return btnSubmit.isEnabled();
    }

    public void clickSubmit() {
        clickElement(btnSubmit);
    }

    public String getErrorMessage() {
        waitForVisibility(txtErrorMessage);
        scrollIntoView(txtErrorMessage);
        return txtErrorMessage.getText();
    }
}
