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

    @Test(priority = 20, description = "TC_20 - Verify page load + Click Contact Us")
    public void verifyPageLoadAndContactUsClick() {

        page = new UniversitiesPage(driver);

        // Step 1: Track load time
        long startTime = System.currentTimeMillis();

        page.openCampusDirect();

        long loadTimeSec = (System.currentTimeMillis() - startTime) / 1000;

        System.out.println("Campus URL : " + page.getCurrentUrl());
        System.out.println("Title      : " + page.getPageTitle());
        System.out.println("Load Time  : " + loadTimeSec + " sec");

        Assert.assertTrue(page.getCurrentUrl().toLowerCase().contains("campus"),
                "Campus page not loaded");

        Assert.assertTrue(loadTimeSec <= 15,
                "Page load exceeded 15 sec");

        // Step 2: Scroll to Contact Us
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");

        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        // Step 3: Click Contact Us
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        String parentWindow = driver.getWindowHandle();

        WebElement contactBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='Contact us'] | //button[normalize-space()='Contact us']")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", contactBtn);
        System.out.println(" Contact Us clicked");

        // Step 4: Switch to new tab if opened
        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}

        Set<String> allWindows = driver.getWindowHandles();
        for (String w : allWindows) {
            if (!w.equals(parentWindow)) {
                driver.switchTo().window(w);
                break;
            }
        }

        System.out.println("Contact Us URL   : " + driver.getCurrentUrl());
        System.out.println("Contact Us Title : " + driver.getTitle());

        Assert.assertFalse(driver.getCurrentUrl().isEmpty(),
                "Contact Us page not opened");

        System.out.println(" TC_20 PASSED");
    }
}