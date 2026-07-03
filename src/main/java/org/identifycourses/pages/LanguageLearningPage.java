package org.identifycourses.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LanguageLearningPage extends CommonCode {

    public LanguageLearningPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@href='/browse/language-learning']")
    WebElement languageLearningOption;

    @FindBy(xpath = "//div[starts-with(@data-testid,'language:')]//label")
    List<WebElement> languageElements;

    @FindBy(xpath = "//iframe[@title='Modal Message']")
    WebElement popupFrame;

    @FindBy(xpath = "//button[@aria-label='Close Message']")
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
            waitForVisibility(languageLearningOption);
            scrollIntoView(languageLearningOption);
            Thread.sleep(1000);
            clickByJS(languageLearningOption);
            wait.until(ExpectedConditions.urlContains("language-learning"));
            System.out.println("Language Learning page opened");
            closePopupIfPresent();
        } catch (Exception e) {
            System.out.println("Navigation failed");
            e.printStackTrace();
        }
    }

    public void closePopupIfPresent() {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(popupFrame));
            waitForClickable(closePopup);
            clickByJS(closePopup);
            driver.switchTo().defaultContent();
            System.out.println("Popup closed successfully");
        } catch (Exception e) {
            driver.switchTo().defaultContent();
            System.out.println("Popup not displayed");
        }
    }

    public List<String> getLanguages() {
        List<String> languages = new ArrayList<>();
        try {
            waitForVisibility(languageHeader);
            scrollIntoView(languageHeader);
            System.out.println("Scrolled to Language section");
        } catch (Exception e) {
            System.out.println("Language header not found");
        }
        try {
            waitForClickable(showMoreLanguages);
            clickByJS(showMoreLanguages);
            System.out.println("Clicked Show More");
        } catch (Exception e) {
            System.out.println("Show More not found");
        }
        waitForAllElementsVisible(languageElements);
        for (WebElement ele : languageElements) {
            String text = ele.getText().trim();
            if (!text.isEmpty()) {
                languages.add(text);
                System.out.println(text);
            }
        }
        return languages;
    }

    public List<String> getLevels() {
        List<String> levels = new ArrayList<>();
        try {
            waitForVisibility(levelHeader);
            scrollIntoView(levelHeader);
            System.out.println("Scrolled to Level section");
        } catch (Exception e) {
            System.out.println("Level section not found");
        }
        for (WebElement ele : levelElements) {
            String text = ele.getText().trim();
            if (!text.isEmpty()) {
                levels.add(text);
                System.out.println(text);
            }
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