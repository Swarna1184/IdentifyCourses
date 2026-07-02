package org.identifycourses.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "input[data-testid='HeaderSearchInput'], input[name='query'], input[placeholder*='Search'], input[aria-label*='Search']")
    private WebElement searchBox;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void clickSearchBox() {
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.click();
    }

    public void enterSearchKeyword(String keyword) {
        searchBox.clear();
        searchBox.sendKeys(keyword);
    }

    public SearchPage clickSearchIcon() {

        searchBox.sendKeys(Keys.ENTER);
        return new SearchPage(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }
}