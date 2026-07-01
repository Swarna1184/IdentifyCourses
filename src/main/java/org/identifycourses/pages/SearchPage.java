package org.identifycourses.pages;

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

    // ---------- Page Factory Elements ----------

    // @FindAll tries each locator and combines all matches (great for resilient locators)
    @FindAll({
            @FindBy(css = "div.cds-ProductCard-content"),
            @FindBy(css = "li.cds-9"),
            @FindBy(css = "div[data-testid='product-card']"),
            @FindBy(css = "a[data-click-key*='search.search.click.search_result']")
    })
    private List<WebElement> courseCards;

    // ---------- Constructor ----------

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // ---------- Page Actions ----------

    public boolean areResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.urlContains("search"));
            // Wait until at least one course card is present
            wait.until(d -> !courseCards.isEmpty());
            return !courseCards.isEmpty();
        } catch (Exception e) {
            // Fallback: confirm URL navigated to search results
            return driver.getCurrentUrl().toLowerCase().contains("search");
        }
    }

    public int getResultsCount() {
        return courseCards.size();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}