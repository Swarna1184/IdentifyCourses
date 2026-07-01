package basetest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.DriverSetup;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverSetup.getDriver();
        driver.get("https://www.coursera.org/");
    }

    @AfterMethod
    public void teardown() {
        DriverSetup.quitDriver();
    }
}
