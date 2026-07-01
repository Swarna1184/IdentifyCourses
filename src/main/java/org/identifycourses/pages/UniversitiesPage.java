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



    public void openCourseraHome() {
        driver.get("https://www.coursera.org/");
        wait.until(ExpectedConditions.visibilityOf(courseraLogo));
    }


    public void clickForUniversities() {

        wait.until(ExpectedConditions.elementToBeClickable(forUniversitiesLink));
        try {
            forUniversitiesLink.click();
        } catch (Exception e) {
            // Fallback JS click if normal click is intercepted
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", forUniversitiesLink);
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
            // Wait for page to fully load
            wait.until(d -> ((JavascriptExecutor) d)
                    .executeScript("return document.readyState").equals("complete"));

            String url   = driver.getCurrentUrl().toLowerCase();
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
            System.out.println(" Verification failed: " + e.getMessage());
            return false;
        }
    }
}