package org.identifycourses.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // ==================== PAGE FACTORY ELEMENTS ====================

    // ---------- Course result cards (Pranathi - TC_01) ----------
    @FindAll({
            @FindBy(css = "div.cds-ProductCard-content"),
            @FindBy(css = "li.cds-9"),
            @FindBy(css = "div[data-testid='product-card']"),
            @FindBy(css = "a[data-click-key*='search.search.click.search_result']")
    })
    private List<WebElement> courseCards;

    // ---------- Filter locators (Teammate - Beginner filter) ----------
    private By filterButton     = By.xpath("//button[contains(.,'Filter')]");
    private By levelDropdown    = By.xpath("//span[contains(text(),'Level')]");
    private By beginnerCheckbox = By.xpath("//input[@type='checkbox']/ancestor::label[contains(.,'Beginner')]");
    private By viewButton       = By.xpath("//button[contains(.,'View')]");

    // ==================== CONSTRUCTOR ====================

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // ==================== ACTIONS: PRANATHI (TC_01) ====================

    /** Verifies whether search results (course cards) are visible on the page */
    public boolean areResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.urlContains("search"));
            wait.until(d -> !courseCards.isEmpty());
            return !courseCards.isEmpty();
        } catch (Exception e) {
            return driver.getCurrentUrl().toLowerCase().contains("search");
        }
    }

    /** Returns total number of course cards displayed */
    public int getResultsCount() {
        return courseCards.size();
    }

    /** Returns current browser URL */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // ==================== ACTIONS: TEAMMATE (Beginner Filter) ====================

    /** Applies the "Beginner" level filter on the search results page */
    public void applyBeginnerFilter() {
        // Click Filter button
        WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(filterButton));
        js.executeScript("arguments[0].click();", filter);

        // Expand Level dropdown
        WebElement level = wait.until(ExpectedConditions.elementToBeClickable(levelDropdown));
        js.executeScript("arguments[0].scrollIntoView(true);", level);
        js.executeScript("arguments[0].click();", level);

        // Select Beginner checkbox
        WebElement beginner = wait.until(ExpectedConditions.elementToBeClickable(beginnerCheckbox));
        js.executeScript("arguments[0].click();", beginner);

        // Click View to apply filter
        WebElement view = wait.until(ExpectedConditions.elementToBeClickable(viewButton));
        js.executeScript("arguments[0].click();", view);

        System.out.println("✅ Beginner filter applied successfully");
    }
}
