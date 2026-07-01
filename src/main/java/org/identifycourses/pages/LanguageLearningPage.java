package org.identifycourses.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LanguageLearningPage {

    WebDriver driver;

    public LanguageLearningPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/browse/language-learning']")
    WebElement languageLearningOption;

    @FindBy(xpath = "//span[contains(@class,'cds-checkboxAndRadio-label')]")
    List<WebElement> languageList;

    @FindBy(xpath = "//div[@role='dialog']//button[@aria-label='Close Message']")
    WebElement closePopup;

    @FindBy(xpath = "//h3[text()='Language']/following::button[contains(text(),'Show')]")
    WebElement showMoreLanguages;
    public void openLanguageLearning() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(languageLearningOption));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                languageLearningOption
        );
        js.executeScript("arguments[0].click();", languageLearningOption);
    }

    public void closePopupIfPresent() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            System.out.println("Waiting for popup...");
            WebElement closeBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@role='dialog']//button[@aria-label='Close Message']")
                    )
            );
            System.out.println("Popup detected ");
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", closeBtn);
            wait.until(ExpectedConditions.invisibilityOf(closeBtn));
            System.out.println("Popup closed ");
        } catch (Exception e) {
            System.out.println("Popup not detected ");
        }
    }

    public List<String> getLanguages() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<String> languages = new ArrayList<>();
        try {
            WebElement languageHeader = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[text()='Language']")
                    )
            );
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", languageHeader);
            System.out.println("Scrolled to Language section ");
        } catch (Exception e) {
            System.out.println("Language header not found ");
        }
        try {
            WebElement showMore = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@data-testid='expand-filter-items-button']")
                    )
            );
            js.executeScript("arguments[0].click();", showMore);
            System.out.println("Clicked Show more ");
        } catch (Exception e) {
            System.out.println("Show more not found ");
        }
        List<WebElement> languageElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[starts-with(@data-testid,'language:')]//label")
                )
        );
        for (WebElement ele : languageElements) {
            String text = ele.getText().trim();
            if (!text.isEmpty()) {
                text = text.replaceAll("\\(.*\\)", "").trim();
                languages.add(text);
            }
        }
        System.out.println("Languages: " + languages);
        return languages;
    }
}