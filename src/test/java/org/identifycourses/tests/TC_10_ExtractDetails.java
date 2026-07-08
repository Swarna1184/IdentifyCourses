package org.identifycourses.tests;

import basetest.BaseTest;
import org.identifycourses.pages.HomePage;
import org.identifycourses.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import utilities.ExcelUtils;
import java.util.List;
import java.util.Map;

public class TC_10_ExtractDetails extends BaseTest {

    @Test
    public void extractCourseDetailsAfterFiltering() throws IOException {
        HomePage home = new HomePage(driver);
        home.clickSearchBox();
        home.enterSearchKeyword("Web Development");
        SearchPage searchPage = home.clickSearchIcon();
        searchPage.applyBothFilters();
        Assert.assertTrue(
                searchPage.getCourseCount() >= 2,
                "Less than 2 courses found"
        );
        List<Map<String,String>> courses =
                searchPage.getCourseDetails();
        ExcelUtils.writeCourseDetailsToExcel(courses);
        logger.info("Extracting from page: {}", driver.getCurrentUrl());
        BaseTest.takeScreenShot(driver, "ExtractDetails");
        logger.info("Courses details are extracted successfully");
    }
}
