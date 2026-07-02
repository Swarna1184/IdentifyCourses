package org.identifycourses.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.identifycourses.pages.UniversitiesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_20_LoadTime {

    WebDriver driver;
    UniversitiesPage page;

    @BeforeMethod
    public void setUp() {

        ChromeOptions o = new ChromeOptions();
        o.addArguments("--remote-allow-origins=*", "--start-maximized");

        driver = new ChromeDriver(o);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
    }
}