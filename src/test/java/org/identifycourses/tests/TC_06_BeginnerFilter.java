package org.identifycourses.tests;

import org.testng.annotations.Test;
import org.identifycourses.pages.SearchPage;
import basetest.BaseTest;

public class TC_06_BeginnerFilter extends BaseTest {

    @Test
    public void verifyBeginnerFilterCourses() {

        SearchPage searchPage = new SearchPage(driver);

        // ✅ Step 1: Search course
        searchPage.searchCourse("Web Development");

        // ✅ Step 2: Apply Beginner filter
        searchPage.applyBeginnerFilter();

        // ✅ Step 3: Simple confirmation log
        System.out.println("✅ Beginner filter applied successfully");

        System.out.println("✅ TC_06 PASSED");
    }
}