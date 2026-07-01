package basetest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    @BeforeClass
    public void setup() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();


        driver.get("https://www.coursera.org/search?query=web%20development");

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        js = (JavascriptExecutor) driver;
    }

    @Test(priority = 1)
    public void testFilterBeginnerLevel() {

        try {

            js.executeScript("window.scrollBy(0,600)");


            WebElement filterBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(.,'Filter')]")
            ));
            js.executeScript("arguments[0].click();", filterBtn);


            WebElement levelDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//span[contains(text(),'Level')]")
            ));

            js.executeScript("arguments[0].scrollIntoView(true);", levelDropdown);
            js.executeScript("arguments[0].click();", levelDropdown);


            WebElement beginner = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//input[@type='checkbox']/ancestor::label[contains(.,'Beginner')]")
            ));
            js.executeScript("arguments[0].click();", beginner);

            System.out.println("Beginner filter applied successfully");


            WebElement viewBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(.,'View')]")
            ));
            js.executeScript("arguments[0].click();", viewBtn);


            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//div[contains(@data-testid,'product-card')]")
            ));

            System.out.println(" Filter applied and results loaded");

        } catch (Exception e) {
            System.out.println(" Filter flow failed");
            e.printStackTrace();
        }
    }

    // ❌ REMOVE or COMMENT this to keep browser open
    /*
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    */
}