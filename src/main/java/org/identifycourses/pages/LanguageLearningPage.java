package org.identifycourses.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class LanguageLearningPage {

    WebDriver driver;

    public LanguageLearningPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Language Learning')]")
    WebElement languageLearningOption;

    public void openLanguageLearning() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", languageLearningOption
        );
        js.executeScript("arguments[0].click();", languageLearningOption);
    }
}