package org.identifycourses.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.util.List;

public class SearchPage extends CommonCode {

    public SearchPage(WebDriver driver) {
        super(driver);
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
            clickElement(element);
        } catch (Exception e) {
            clickByJS(element);
        }
    }

    public void searchCourse(String course) {
        WebElement box = waitForVisibility(searchBox);
        box.clear();
        box.sendKeys(course);
        box.sendKeys(Keys.ENTER);
        waitForAllElementsVisible(courseCards);
        System.out.println("Search completed");
    }

    public void applyBeginnerFilter() {
        scrollIntoView(filterButton);
        safeClick(filterButton);
        safeClick(levelDropdown);
        if (!beginnerCheckbox.isSelected()) {
            safeClick(beginnerCheckbox);
        }
        safeClick(viewButton);
        waitForAllElementsVisible(courseCards);
        System.out.println("Beginner filter applied");
    }

    public boolean areResultsDisplayed() {
        return courseCards.size() > 0;
    }

    public void applyEnglishFilter() {
        scrollIntoView(filterButton);
        safeClick(filterButton);
        safeClick(languageDropdown);
        if (!englishCheckbox.isSelected()) {
            safeClick(englishCheckbox);
        }
        safeClick(viewButton);
        waitForAllElementsVisible(courseCards);
        System.out.println("English language filter applied");
    }

    public void applyBothFilters() {
        scrollIntoView(filterButton);
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
        waitForAllElementsVisible(courseCards);
        System.out.println("Beginner + English filters applied");
    }

    public int getCourseCount() {
        waitForAllElementsVisible(courseCards);
        int count = courseCards.size();
        System.out.println("Total courses found: " + count);
        return count;
    }

    public void extractCourseDetails() {
        waitForAllElementsVisible(courseCards);
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