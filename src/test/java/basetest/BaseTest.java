package basetest;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;

public class BaseTest {

    protected WebDriver driver;
    @BeforeMethod
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
    }

    @AfterMethod
    public void tearDown() {

//        if(driver != null) {
//            //driver.quit();
//        }
    }
}