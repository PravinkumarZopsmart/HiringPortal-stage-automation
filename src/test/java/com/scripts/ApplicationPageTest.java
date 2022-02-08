package com.scripts;

import com.pages.*;
import com.utils.*;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.*;

import org.testng.annotations.*;

import java.util.Map;

public class ApplicationPageTest {
    private static WebDriver driver;
    private static final String URL = "https://stage.hiringmotion.com/";
    private static final String APPLICATION = "APPLICATIONS";

    @BeforeSuite
    public void setUpDriver() {
        driver = WebDriverUtil.startWebDriver();
        driver.get(URL);
    }

    @BeforeClass
    public void loginToHiringPortal() {
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
    }

    @BeforeMethod
    public void selectApplicationBar() {
        Base.selectSideBarPage(driver, APPLICATION);
    }

    @Test
    public void testApplicationHeader() {
        String actualHeader = Application.getPageHeading(driver).toUpperCase();
        assertEquals(actualHeader, APPLICATION);
    }

    @Test(dependsOnMethods = {"testApplicationHeader"})
    public void testApplicationsPerPage() {
        do {
            int actualApplicationsPerPage = Application.getNumberOfApplicationsInCurrentPage(driver);
            assertTrue(actualApplicationsPerPage <= 10);
        } while (Application.moveToNextPage(driver));
    }

    @Test(dependsOnMethods = {"testApplicationsPerPage"})
    public void testApplicationDetails() {
        Map<String,String> applicantDetails = Application.getApplicantDetails(driver,1);
        System.out.println(applicantDetails);
        Application.enterApplicantDetails(driver,1);
        Map<String, String> actualApplicantDetails = Application.getApplicantNameAndStatus(driver);
        String expectedName = applicantDetails.get("name");
        String expectedStatus = applicantDetails.get("status");
        String actualName = actualApplicantDetails.get("name");
        String actualStatus = actualApplicantDetails.get("status");
        assertEquals(actualName,expectedName);
        assertEquals(actualStatus,expectedStatus);
    }
    @AfterSuite
    public void endDriverSession() {
        driver.close();
        driver.quit();
    }
}
