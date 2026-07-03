package org.identifycourses.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LanguageLearningPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public LanguageLearningPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/browse/language-learning']")
    WebElement languageLearningOption;

    @FindBy(xpath = "//span[contains(@class,'cds-checkboxAndRadio-label')]")
    List<WebElement> languageElements;

    @FindBy(xpath = "(//button[@aria-label='Close Message'])[1]")
    WebElement closePopup;

    @FindBy(xpath = "//*[text()='Language']")
    WebElement languageHeader;

    @FindBy(xpath = "//*[text()='Level']")
    WebElement levelHeader;

    @FindBy(xpath = "//div[contains(@data-testid,'productDifficultyLevel')]//label")
    List<WebElement> levelElements;

    @FindBy(xpath = "//button[@data-testid='expand-filter-items-button']")
    WebElement showMoreLanguages;

    public void navigateToLanguageLearning() {
        try {
            wait.until(
                    ExpectedConditions.visibilityOf(languageLearningOption));
            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    languageLearningOption);
            Thread.sleep(1000);
            js.executeScript(
                    "arguments[0].click();",
                    languageLearningOption);
            wait.until(
                    ExpectedConditions.urlContains("language-learning"));
            System.out.println("Language Learning page opened");
            closePopupIfPresent();
        }
        catch (Exception e) {
            System.out.println("Navigation failed");
            e.printStackTrace();
        }
    }

    public void closePopupIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                    By.xpath("//iframe[@title='Modal Message']")));
            wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Close Message']")))
                    .click();
            driver.switchTo().defaultContent();
            System.out.println("Popup closed successfully");
        }
        catch (Exception e) {
            System.out.println("Popup not displayed");
        }
    }
    public List<String> getLanguages() {
        List<String> languages = new ArrayList<>();
        try {
            wait.until(
                    ExpectedConditions.visibilityOf(languageHeader));
            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    languageHeader);
            System.out.println("Scrolled to Language section");
        } catch (Exception e) {
            System.out.println("Language header not found");
        }
        try {
            wait.until(
                    ExpectedConditions.elementToBeClickable(showMoreLanguages));
            js.executeScript(
                    "arguments[0].click();",
                    showMoreLanguages);
            System.out.println("Clicked Show More");
        } catch (Exception e) {
            System.out.println("Show More not found");
        }
        for (WebElement ele : languageElements) {
            String text = ele.getText().trim();
            if (!text.isEmpty()) {
                languages.add(text);
                System.out.println(text);
            }
        }
        System.out.println("Languages : " + languages);
        return languages;
    }

    public List<String> getLevels() {
        List<String> levels = new ArrayList<>();
        try {
            wait.until(
                    ExpectedConditions.visibilityOf(levelHeader));
            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    levelHeader);
            System.out.println("Scrolled to Level section");
        } catch (Exception e) {
            System.out.println("Level section not found");
        }
        try {
            Thread.sleep(2000);
            for (WebElement ele : levelElements) {
                String text = ele.getText().trim();
                if (!text.isEmpty()) {
                    levels.add(text);
                    System.out.println(text);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return levels;
    }

    public int getLanguageCount() {
        List<String> languages = getLanguages();
        return languages.size();
    }

    public int getLevelCount() {
        List<String> levels = getLevels();
        return levels.size();
    }

}