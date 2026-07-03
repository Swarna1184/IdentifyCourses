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

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

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


    @FindBy(xpath = "//h3")
    List<WebElement> courseNames;

    @FindBy(xpath = "//*[contains(text(),'hours')]")
    List<WebElement> learningHours;

    @FindBy(xpath = "//*[contains(@aria-label,'rating') or contains(text(),'Rating')]")
    List<WebElement> ratings;

    private void safeClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", element);
        }
    }

    public void searchCourse(String course) {
        WebElement box = wait.until(ExpectedConditions.visibilityOf(searchBox));
        box.clear();
        box.sendKeys(course);
        box.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfAllElements(courseCards));
        System.out.println("Search completed");
    }


    public void applyBeginnerFilter() {
        js.executeScript("window.scrollBy(0,500)");
        WebElement filter = wait.until(ExpectedConditions.visibilityOf(filterButton));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", filter);
        safeClick(filter);
        WebElement level = wait.until(ExpectedConditions.elementToBeClickable(levelDropdown));
        safeClick(level);
        WebElement beginner = wait.until(ExpectedConditions.elementToBeClickable(beginnerCheckbox));
        if (!beginner.isSelected()) {
            safeClick(beginner);
        }
        WebElement view = wait.until(ExpectedConditions.elementToBeClickable(viewButton));
        safeClick(view);
        wait.until(ExpectedConditions.visibilityOfAllElements(courseCards));
        System.out.println("Beginner filter applied");
    }


    public boolean areResultsDisplayed() {
        return courseCards.size() > 0;
    }

    public void applyEnglishFilter() {
        js.executeScript("window.scrollBy(0,500)");
        WebElement filter = wait.until(
                ExpectedConditions.elementToBeClickable(filterButton));
        safeClick(filter);
        WebElement language = wait.until(
                ExpectedConditions.elementToBeClickable(languageDropdown));
        safeClick(language);
        WebElement english = wait.until(
                ExpectedConditions.elementToBeClickable(englishCheckbox));
        if (!english.isSelected()) {
            safeClick(english);
        }
        WebElement view = wait.until(
                ExpectedConditions.elementToBeClickable(viewButton));
        safeClick(view);
        wait.until(ExpectedConditions.visibilityOfAllElements(courseCards));
        System.out.println("English language filter applied");
    }
    public void applyBothFilters() {
        js.executeScript("window.scrollBy(0,500)");
        safeClick(filterButton);
        safeClick(levelDropdown);
        if (!beginnerCheckbox.isSelected()) {
            safeClick(beginnerCheckbox);
        }
        safeClick(languageDropdown);
        if (!englishCheckbox.isSelected()) {
            safeClick(englishCheckbox);
        }
        safeClick(viewButton);
        wait.until(ExpectedConditions.visibilityOfAllElements(courseCards));
        System.out.println(" Beginner + English filters applied");
    }

    public int getCourseCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(courseCards));
        int count = courseCards.size();
        System.out.println("Total courses found: " + count);
        return count;
    }

    public void extractCourseDetails() {
        wait.until(ExpectedConditions.visibilityOfAllElements(courseCards));
        int count = Math.min(2, courseCards.size());
        System.out.println("\n===== TOP COURSES =====");
        for (int i = 0; i < count; i++) {
            String courseName = courseNames.get(i).getText();
            String hours = "N/A";
            String rating = "N/A";
            try {
                hours = learningHours.get(i).getText();
            } catch (Exception e) {
                System.out.println("Hours not found");
            }
            try {
                rating = ratings.get(i).getText();
            } catch (Exception e) {
                System.out.println("Rating not found");
            }
            System.out.println("\nCourse " + (i + 1));
            System.out.println("Name   : " + courseName);
            System.out.println("Hours  : " + hours);
            System.out.println("Rating : " + rating);
        }
    }
}