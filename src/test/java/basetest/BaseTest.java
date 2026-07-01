package basetest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.DriverSetup;
import utilities.ExtentReportManager;

public class BaseTest {

    protected WebDriver driver;
    protected static final String BASE_URL = "https://www.coursera.org/";

    @BeforeMethod
    public void setUp() {
        driver = DriverSetup.initDriver();
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        ExtentReportManager.flush();
        DriverSetup.quitDriver();
    }
}