package org.identifycourses.pages;

import org.openqa.selenium.JavascriptExecutor;
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
            waitForClickable(forUniversitiesLink);
            forUniversitiesLink.click();
        } catch (Exception e) {
            clickByJS(forUniversitiesLink);
        }
    }

    public void switchToNewWindow() {
        String parent = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parent)) {
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
        try {
            wait.until(d ->
                    ((JavascriptExecutor) d)
                            .executeScript("return document.readyState")
                            .equals("complete"));
            String url = driver.getCurrentUrl().toLowerCase();
            String title = driver.getTitle().toLowerCase();
            if (url.contains("campus") || url.contains("universities")) {
                System.out.println("Verified via URL: " + url);
                return true;
            }
            if (title.contains("campus") || title.contains("universities")) {
                System.out.println("Verified via Title: " + title);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Verification failed: " + e.getMessage());
            return false;
        }
    }
}