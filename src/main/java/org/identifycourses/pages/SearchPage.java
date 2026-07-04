package org.identifycourses.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

    @FindBy(xpath = "//div[contains(@data-testid,'product-card')]//h3")
    List<WebElement> courseNames;

    @FindBy(xpath = "//div[contains(@data-testid,'product-card')]//*[contains(text(),'hours') or contains(text(),'Weeks') or contains(text(),'Months')]")
    List<WebElement> learningHours;

    @FindBy(xpath = "//div[contains(@data-testid,'product-card')]//*[contains(@aria-label,'out of 5 stars')]")
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
    public List<Map<String, String>> getCourseDetails() {
        List<Map<String, String>> courses = new ArrayList<>();
        waitForAllElementsVisible(courseCards);
        int count = Math.min(2, courseCards.size());
        for (int i = 0; i < count; i++) {
            WebElement card = courseCards.get(i);
            String courseName = "N/A";
            String hours = "N/A";
            String rating = "N/A";
            try {
                courseName = card.findElement(By.xpath(".//h3")).getText();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String cardText = card.getText();
                java.util.regex.Pattern ratingPattern =
                        java.util.regex.Pattern.compile("★\\s*([0-9]+\\.?[0-9]*)");
                java.util.regex.Matcher ratingMatcher =
                        ratingPattern.matcher(cardText);
                if (ratingMatcher.find()) {
                    rating = ratingMatcher.group(1);
                }
                java.util.regex.Pattern durationPattern =
                        java.util.regex.Pattern.compile(
                                "(\\d+\\s*-\\s*\\d+\\s*(Weeks|Months))|(\\d+(\\.\\d+)?\\s*hours)");
                java.util.regex.Matcher durationMatcher =
                        durationPattern.matcher(cardText);

                if (durationMatcher.find()) {
                    hours = durationMatcher.group();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Map<String, String> course = new HashMap<>();
            course.put("Name", courseName);
            course.put("Hours", hours);
            course.put("Rating", rating);
            System.out.println(course);
            courses.add(course);
        }

        System.out.println("Final Course List = " + courses);

        return courses;
    }
}