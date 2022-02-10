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
    public void testDuplicateApplication() {
        Map<String, String> applicantDetails = Application.getApplicantDetails(driver, "Duplicate");
        if (Application.enterApplicantDetails(driver, "Duplicate")) {
            Map<String, String> actualApplicantDetails = Application.getApplicantNameAndStatus(driver);
            assertNotNull(applicantDetails);
            String expectedName = applicantDetails.get("name");
            String expectedStatus = applicantDetails.get("status");
            String actualName = actualApplicantDetails.get("name");
            String actualStatus = actualApplicantDetails.get("status");
            assertEquals(actualName, expectedName);
            assertEquals(actualStatus, expectedStatus);
            assertFalse(Application.isApplicationDetailsButton(driver, "Edit Application"));
            assertTrue(Application.isApplicationDetailsButton(driver, "View Information"));
        }
    }

    @Test
    public void testOfferedApplication() {
        Map<String, String> applicantDetails = Application.getApplicantDetails(driver, "Offered");
        if (Application.enterApplicantDetails(driver, "Offered")) {
            Map<String, String> actualApplicantDetails = Application.getApplicantNameAndStatus(driver);
            assertNotNull(applicantDetails);
            String expectedName = applicantDetails.get("name");
            String expectedStatus = applicantDetails.get("status");
            String actualName = actualApplicantDetails.get("name");
            String actualStatus = actualApplicantDetails.get("status");
            assertEquals(actualName, expectedName);
            assertEquals(actualStatus, expectedStatus);
            assertFalse(Application.isApplicationDetailsButton(driver, "Edit Application"));
            assertTrue(Application.isApplicationDetailsButton(driver, "View Information"));
        }
    }

    @Test
    public void testPendingInterviewApplication() {
        Map<String, String> applicantDetails = Application.getApplicantDetails(driver, "Pending Interview");
        if (Application.enterApplicantDetails(driver, "Pending Interview")) {
            Map<String, String> actualApplicantDetails = Application.getApplicantNameAndStatus(driver);
            assertNotNull(applicantDetails);
            String expectedName = applicantDetails.get("name");
            String expectedStatus = applicantDetails.get("status");
            String actualName = actualApplicantDetails.get("name");
            String actualStatus = actualApplicantDetails.get("status");
            assertEquals(actualName, expectedName);
            assertEquals(actualStatus, expectedStatus);
            assertTrue(Application.isApplicationDetailsButton(driver, "Edit Application"));
            assertTrue(Application.isApplicationDetailsButton(driver, "View Information"));
        }
    }

    @Test
    public void testRejectedApplication() {
        Map<String, String> applicantDetails = Application.getApplicantDetails(driver, "Rejected");
        if (Application.enterApplicantDetails(driver, "Rejected")) {
            Map<String, String> actualApplicantDetails = Application.getApplicantNameAndStatus(driver);
            assertNotNull(applicantDetails);
            String expectedName = applicantDetails.get("name");
            String expectedStatus = applicantDetails.get("status");
            String actualName = actualApplicantDetails.get("name");
            String actualStatus = actualApplicantDetails.get("status");
            assertEquals(actualName, expectedName);
            assertEquals(actualStatus, expectedStatus);
            assertFalse(Application.isApplicationDetailsButton(driver, "Edit Application"));
            assertTrue(Application.isApplicationDetailsButton(driver, "View Information"));
        }
    }

    @Test
    public void testScreeningPendingApplication() {
        Map<String, String> applicantDetails = Application.getApplicantDetails(driver, "Screening Pending");
        if (Application.enterApplicantDetails(driver, "Screening Pending")) {
            Map<String, String> actualApplicantDetails = Application.getApplicantNameAndStatus(driver);
            assertNotNull(applicantDetails);
            String expectedName = applicantDetails.get("name");
            String expectedStatus = applicantDetails.get("status");
            String actualName = actualApplicantDetails.get("name");
            String actualStatus = actualApplicantDetails.get("status");
            assertEquals(actualName, expectedName);
            assertEquals(actualStatus, expectedStatus);
            assertTrue(Application.isApplicationDetailsButton(driver, "Edit Application"));
            assertTrue(Application.isApplicationDetailsButton(driver, "View Information"));
        }
    }

    @Test
    public void testInterviewScheduledApplication() {
        Map<String, String> applicantDetails = Application.getApplicantDetails(driver, "Interview Scheduled");
        if (Application.enterApplicantDetails(driver, "Interview Scheduled")) {
            Map<String, String> actualApplicantDetails = Application.getApplicantNameAndStatus(driver);
            assertNotNull(applicantDetails);
            String expectedName = applicantDetails.get("name");
            String expectedStatus = applicantDetails.get("status");
            String actualName = actualApplicantDetails.get("name");
            String actualStatus = actualApplicantDetails.get("status");
            assertEquals(actualName, expectedName);
            assertEquals(actualStatus, expectedStatus);
            assertTrue(Application.isApplicationDetailsButton(driver, "Edit Application"));
            assertTrue(Application.isApplicationDetailsButton(driver, "View Information"));
        }
    }

    @Test
    public void testResumeRejectedApplication() {
        Map<String, String> applicantDetails = Application.getApplicantDetails(driver, "Resume Rejected");
        if (Application.enterApplicantDetails(driver, "Resume Rejected")) {
            Map<String, String> actualApplicantDetails = Application.getApplicantNameAndStatus(driver);
            assertNotNull(applicantDetails);
            String expectedName = applicantDetails.get("name");
            String expectedStatus = applicantDetails.get("status");
            String actualName = actualApplicantDetails.get("name");
            String actualStatus = actualApplicantDetails.get("status");
            assertEquals(actualName, expectedName);
            assertEquals(actualStatus, expectedStatus);
            assertTrue(Application.isApplicationDetailsButton(driver, "Edit Application"));
            assertTrue(Application.isApplicationDetailsButton(driver, "View Information"));
        }
    }

    @Test
    public void testInterviewFailedApplication() {
        Map<String, String> applicantDetails = Application.getApplicantDetails(driver, "Interview Failed");
        if (Application.enterApplicantDetails(driver, "Interview Failed")) {
            Map<String, String> actualApplicantDetails = Application.getApplicantNameAndStatus(driver);
            assertNotNull(applicantDetails);
            String expectedName = applicantDetails.get("name");
            String expectedStatus = applicantDetails.get("status");
            String actualName = actualApplicantDetails.get("name");
            String actualStatus = actualApplicantDetails.get("status");
            assertEquals(actualName, expectedName);
            assertEquals(actualStatus, expectedStatus);
            assertFalse(Application.isApplicationDetailsButton(driver, "Edit Application"));
            assertTrue(Application.isApplicationDetailsButton(driver, "View Information"));
        }
    }

    @Test
    public void testInterviewCancelledApplication() {
        Map<String, String> applicantDetails = Application.getApplicantDetails(driver, "Interview Cancelled");
        if (Application.enterApplicantDetails(driver, "Interview Cancelled")) {
            Map<String, String> actualApplicantDetails = Application.getApplicantNameAndStatus(driver);
            assertNotNull(applicantDetails);
            String expectedName = applicantDetails.get("name");
            String expectedStatus = applicantDetails.get("status");
            String actualName = actualApplicantDetails.get("name");
            String actualStatus = actualApplicantDetails.get("status");
            assertEquals(actualName, expectedName);
            assertEquals(actualStatus, expectedStatus);
            assertTrue(Application.isApplicationDetailsButton(driver, "Edit Application"));
            assertTrue(Application.isApplicationDetailsButton(driver, "View Information"));
        }
    }

    @AfterSuite
    public void endDriverSession() {
        driver.close();
        driver.quit();
    }
}
