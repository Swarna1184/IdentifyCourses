package org.identifycourses.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UniversitiesPage extends CommonCode {

    public UniversitiesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@aria-label='Coursera']")
    WebElement courseraLogo;

    @FindBy(xpath = "//a[normalize-space()='For Universities']")
    WebElement forUniversitiesLink;

    public void clickForUniversities() {
        try {
            clickElement(forUniversitiesLink);
        } catch (Exception e) {
            clickByJS(forUniversitiesLink);
        }
    }

    public void goToUniversitiesPage() {
        clickForUniversities();
        switchToNewWindow();
    }

    public void switchToNewWindow() {
        String parentWindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isUniversitiesPageDisplayed() {
        String url = driver.getCurrentUrl().toLowerCase();
        return url.contains("campus") || url.contains("universities");
    }
}