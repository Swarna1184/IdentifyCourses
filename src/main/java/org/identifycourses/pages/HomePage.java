package org.identifycourses.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends CommonCode {

    @FindBy(css = "input[aria-label*='Search']")
    private WebElement searchBox;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickSearchBox() {
        waitForClickable(searchBox);
        searchBox.click();
    }

    public void enterSearchKeyword(String keyword) {
        waitForVisibility(searchBox);
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