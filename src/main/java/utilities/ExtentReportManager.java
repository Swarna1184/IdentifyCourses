package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter(
                    System.getProperty("user.dir") + "/reports/ExtentReport.html");
            spark.config().setReportName("Identify Courses - Automation Report");
            spark.config().setDocumentTitle("Coursera Hackathon");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static ExtentTest createTest(String name) {
        test = getInstance().createTest(name);
        return test;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void flush() {
        if (extent != null) extent.flush();
    }
}