package com.scripts;

import com.pages.*;
import com.utils.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.Objects;

import static org.testng.Assert.assertEquals;

public class AgenciesPageTest {
    private static WebDriver driver;
    private static final String URL = "https://stage.hiringmotion.com/";
    private static final String AGENCIES = "AGENCIES";

    @BeforeSuite
    public void setUpDriver() {
        Base.deleteScreenshots();
        driver = WebDriverUtil.startWebDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @BeforeClass
    public void loginToHiringPortal() {
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
    }

    @BeforeMethod
    public void selectApplicationBar() {
        Base.selectSideBarPage(driver, AGENCIES);
    }

    @Test
    public void testAgenciesHeader() {
        String actualHeader = Objects.requireNonNull(Agencies.getPageHeader(driver)).toUpperCase();
        assertEquals(actualHeader, AGENCIES);
    }

    @Test
    public void testDeleteButton() {

    }

    @AfterSuite
    public void endSession() {
        driver.close();
        driver.quit();
    }
}
