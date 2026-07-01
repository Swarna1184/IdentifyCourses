package org.identifycourses.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
    }

    // ✅ LOCATORS (FIXED)

    By filterButton = By.xpath("//button[contains(.,'Filter')]");
    By levelDropdown = By.xpath("//span[contains(text(),'Level')]");
    By beginnerCheckbox = By.xpath("//input[@type='checkbox']/ancestor::label[contains(.,'Beginner')]");
    By viewButton = By.xpath("//button[contains(.,'View')]");

    // ✅ APPLY BEGINNER FILTER
    public void applyBeginnerFilter() {

        // ✅ Click Filter
        WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(filterButton));
        js.executeScript("arguments[0].click();", filter);

        // ✅ Open Level dropdown
        WebElement level = wait.until(ExpectedConditions.elementToBeClickable(levelDropdown));
        js.executeScript("arguments[0].scrollIntoView(true);", level);
        js.executeScript("arguments[0].click();", level);


        WebElement beginner = wait.until(ExpectedConditions.elementToBeClickable(beginnerCheckbox));
        js.executeScript("arguments[0].click();", beginner);


        WebElement view = wait.until(ExpectedConditions.elementToBeClickable(viewButton));
        js.executeScript("arguments[0].click();", view);

        System.out.println("Beginner filter applied successfully");
    }

    public void searchCourse(String webDevelopment) {
    }
}

