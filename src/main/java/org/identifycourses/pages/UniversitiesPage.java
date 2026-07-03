package org.identifycourses.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class UniversitiesPage {
    WebDriver driver;
    WebDriverWait wait;
    public UniversitiesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[@aria-label='Coursera']")
    WebElement courseraLogo;
    @FindBy(xpath = "//a[normalize-space()='For Universities']")
    WebElement forUniversitiesLink;

    public void clickForUniversities() {
        wait.until(ExpectedConditions.elementToBeClickable(forUniversitiesLink));
        try {
            forUniversitiesLink.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", forUniversitiesLink);
        }
    }

    public void goToUniversitiesPage() {
        clickForUniversities();
        switchToNewWindow();
    }

    public void switchToNewWindow() {
        String parent = driver.getWindowHandle();
        for (String h : driver.getWindowHandles()) {
            if (!h.equals(parent)) {
                driver.switchTo().window(h);
                break;
            }
        }
    }
    public String getCurrentUrl() { return driver.getCurrentUrl(); }

    public String getPageTitle() { return driver.getTitle(); }

    public boolean isUniversitiesPageDisplayed() {
        String url = driver.getCurrentUrl().toLowerCase();
        return url.contains("campus") || url.contains("universities");
    }
}
