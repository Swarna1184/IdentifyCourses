package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.UniversitiesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class TC_20_LoadTime extends BaseTest {

    UniversitiesPage page;
    @Test
    public void verifyPageLoadAndContactUsClick() {
        page = new UniversitiesPage(driver);
        page.goToUniversitiesPage();
        long startTime = System.currentTimeMillis();
        long loadTimeSec = (System.currentTimeMillis() - startTime) / 1000;
        logger.info("Campus URL : {}", page.getCurrentUrl());
        logger.info("Title      : {}", page.getPageTitle());
        logger.info("Load Time  : {} sec", loadTimeSec);
        Assert.assertTrue(page.getCurrentUrl().toLowerCase().contains("campus"),
                "Campus page not loaded");
        Assert.assertTrue(loadTimeSec <= 15,
                "Page load exceeded 15 sec");
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String parentWindow = driver.getWindowHandle();

        WebElement contactBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='Contact us'] | //button[normalize-space()='Contact us']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", contactBtn);
        logger.info(" Contact Us clicked");

        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}
        Set<String> allWindows = driver.getWindowHandles();

        for (String w : allWindows) {
            if (!w.equals(parentWindow)) {
                driver.switchTo().window(w);
                break;
            }
        }
        logger.info("Contact Us URL   : {}", driver.getCurrentUrl());
        logger.info("Contact Us Title : {}", driver.getTitle());

        Assert.assertFalse(driver.getCurrentUrl().isEmpty(),
                "Contact Us page not opened");
        logger.info("Load time fetched successfully");
    }
}