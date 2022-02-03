package com.scripts;

import com.pages.*;
import com.utils.*;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.*;

import org.testng.annotations.*;

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
        Base.selectSideBarPage(driver, APPLICATION);
    }

    @Test
    public void testApplicationHeader() {
        String actualHeader = Application.getPageHeading(driver).toUpperCase();
        assertEquals(actualHeader, APPLICATION);
    }

    @Test(dependsOnMethods = {"testApplicationHeader"})
    public void testApplicationsPerPage() {
        Base.selectSideBarPage(driver, APPLICATION);
        do {
            int actualApplicationsPerPage = Application.getNumberOfApplicationsInCurrentPage(driver);
            assertTrue(actualApplicationsPerPage <= 10);
        } while (Application.moveToNextPage(driver));
    }

    @AfterSuite
    public void endDriverSession() {
        driver.close();
        driver.quit();
    }
}
