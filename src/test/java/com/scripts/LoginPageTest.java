package com.scripts;

import com.pages.Base;
import com.pages.LoginPage;
import com.utils.*;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.*;

import org.testng.annotations.*;

public class LoginPageTest {
    private static WebDriver driver;
    private static final String URL = "https://stage.hiringmotion.com/";

    @BeforeMethod
    public void setUpDriver() {
        Base.deleteScreenshots();
        driver = WebDriverUtil.startWebDriver();
    }

    @Test
    public void testDashboardHeader() {
        driver.get(URL);
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
        String expectedTitle = "Dashboard | Hiring Motion";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testApplicationUrl() {
        driver.get(URL+"applications");
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
        String expectedTitle = "Applications | Hiring Motion";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle,expectedTitle);
    }

    @Test
    public void testPositionsURL() {
        driver.get(URL+"positions");
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
        String expectedTitle = "Positions | Hiring Motion";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle,expectedTitle);
    }

    @Test
    public void testReferralsURL() {
        driver.get(URL+"referrals");
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
        String expectedTitle = "Referrals | Hiring Motion";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle,expectedTitle);
    }

    @Test
    public void testSkillsURL() {
        driver.get(URL+"skill");
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
        String expectedTitle = "Skills | Hiring Motion";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle,expectedTitle);
    }

    @Test
    public void testLevelsURL() {
        driver.get(URL+"level");
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
        String expectedTitle = "Levels | Hiring Motion";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle,expectedTitle);
    }

    @Test
    public void testInterviewsURL() {
        driver.get(URL+"interviews");
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
        String expectedTitle = "Interviews | Hiring Motion";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle,expectedTitle);
    }

    @Test
    public void testResumeScreeningURL() {
        driver.get(URL+"resume-screening");
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
        String expectedTitle = "Resume Screening | Hiring Motion";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle,expectedTitle);
    }

    @Test
    public void testPreScreeningURL() {
        driver.get(URL+"pre-screening");
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
        String expectedTitle = "Pre Screening | Hiring Motion";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle,expectedTitle);
    }

    @Test
    public void testUsersURL() {
        driver.get(URL+"interviewers");
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
        String expectedTitle = "Users | Hiring Motion";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle,expectedTitle);
    }

    @Test
    public void testTeamsURL() {
        driver.get(URL+"team");
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
        String expectedTitle = "Teams | Hiring Motion";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle,expectedTitle);
    }

    @Test
    public void testStreamsURL() {
        driver.get(URL+"stream");
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
        String expectedTitle = "Streams | Hiring Motion";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle,expectedTitle);
    }

    @Test
    public void testAgenciesURL() {
        driver.get(URL+"sources");
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
        String expectedTitle = "Agencies | Hiring Motion";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle,expectedTitle);
    }

    @AfterMethod
    public void logout() {
        LoginPage.logout(driver);
        driver.close();
        driver.quit();
    }
}
