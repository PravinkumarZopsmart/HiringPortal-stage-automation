package com.scripts;

import com.pages.LoginPage;
import com.utils.ElementHelpers;
import com.utils.WebDriverUtil;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.*;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class PositionPageTest {
    private static WebDriver driver;
    private static final String URL = "https://stage.hiringmotion.com/";
    private static final String email = "pravin.kumar@zopsmart.com";
    private static final String password = "Godofwar@25";

    @BeforeSuite
    public void setUpDriver() {
        driver = WebDriverUtil.startWebDriver();
        driver.get(URL);
    }

    @BeforeClass
    public void loginToHiringPortal() {
        LoginPage.login(driver, email, password);
        ElementHelpers.waitForDOMToLoad(driver);
    }

    @Test
    public void testPositionHeader() {
        String expectedTitle = "Dashboard | Hiring Motion";
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle);
    }

    @AfterSuite
    public void endWebDriverSession() {
        driver.close();
        driver.quit();
    }
}
