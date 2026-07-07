package basetest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.ConfigReader;

public class BaseTest {
    protected WebDriver driver;

    protected Logger logger = LogManager.getLogger(this.getClass());

    @BeforeClass
    public void setup() {
        String browser = ConfigReader.getProperty("browser");
        if(browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }else{
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigReader.getProperty("url"));
        logger.info("Application launched successfully");

    }

    @AfterClass
    public void tearDown() {
          driver.quit();
    }

    public static void takeScreenShot(WebDriver driver, String fileName) throws IOException {
        File screenshotsDir = new File(System.getProperty("user.dir") + "/Screenshots");
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(screenshotsDir, fileName + ".png");
        if (destination.exists()) {
            destination.delete();
        }
        FileHandler.copy(src, destination);
    }
}