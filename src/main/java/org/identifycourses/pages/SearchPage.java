package org.identifycourses.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    // ✅ Constructor
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;

        PageFactory.initElements(driver, this);
    }

    // ✅ LOCATORS

    @FindBy(xpath = "//input[@type='search' or @type='text']")
    WebElement searchBox;

    @FindBy(xpath = "//button[contains(.,'Filter')]")
    WebElement filterButton;

    @FindBy(xpath = "//span[contains(text(),'Level')]")
    WebElement levelDropdown;

    @FindBy(xpath = "//input[@type='checkbox']/ancestor::label[contains(.,'Beginner')]")
    WebElement beginnerCheckbox;

    @FindBy(xpath = "//button[contains(.,'View')]")
    WebElement viewButton;

    @FindBy(xpath = "//div[contains(@data-testid,'product-card')]")
    List<WebElement> courseCards;

   @FindBy(xpath = "//span[contains(text(),'Language')]")
    WebElement languageDropdown;

    @FindBy(xpath = "//input[@type='checkbox']/ancestor::label[contains(.,'English')]")
    WebElement englishCheckbox;


    // ✅ SAFE CLICK
    private void safeClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", element);
        }
    }

    // ✅ SEARCH FUNCTION (IMPORTANT ✅)
    public void searchCourse(String course) {

        // ✅ Wait until search box visible
        WebElement box = wait.until(ExpectedConditions.visibilityOf(searchBox));

        box.clear();
        box.sendKeys(course);
        box.sendKeys(Keys.ENTER);

        // ✅ Wait for results
        wait.until(ExpectedConditions.visibilityOfAllElements(courseCards));

        System.out.println("✅ Search completed");
    }

    // ✅ APPLY BEGINNER FILTER
    public void applyBeginnerFilter() {

        // ✅ scroll little
        js.executeScript("window.scrollBy(0,500)");

        // ✅ Filter button
        WebElement filter = wait.until(ExpectedConditions.visibilityOf(filterButton));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", filter);
        safeClick(filter);

        // ✅ Level dropdown
        WebElement level = wait.until(ExpectedConditions.elementToBeClickable(levelDropdown));
        safeClick(level);

        // ✅ Beginner checkbox
        WebElement beginner = wait.until(ExpectedConditions.elementToBeClickable(beginnerCheckbox));

        if (!beginner.isSelected()) {
            safeClick(beginner);
        }

        // ✅ View button
        WebElement view = wait.until(ExpectedConditions.elementToBeClickable(viewButton));
        safeClick(view);

        // ✅ Wait for results reload
        wait.until(ExpectedConditions.visibilityOfAllElements(courseCards));

        System.out.println("✅ Beginner filter applied");
    }


    public boolean areResultsDisplayed() {
        return courseCards.size() > 0;
    }

    public void applyEnglishFilter() {

        js.executeScript("window.scrollBy(0,500)");

        // Filter button
        WebElement filter = wait.until(
                ExpectedConditions.elementToBeClickable(filterButton));
        safeClick(filter);

        // Language dropdown
        WebElement language = wait.until(
                ExpectedConditions.elementToBeClickable(languageDropdown));
        safeClick(language);

        // English checkbox
        WebElement english = wait.until(
                ExpectedConditions.elementToBeClickable(englishCheckbox));

        if (!english.isSelected()) {
            safeClick(english);
        }

        // View button
        WebElement view = wait.until(
                ExpectedConditions.elementToBeClickable(viewButton));
        safeClick(view);

        wait.until(ExpectedConditions.visibilityOfAllElements(courseCards));

        System.out.println("✅ English language filter applied");
    }
}