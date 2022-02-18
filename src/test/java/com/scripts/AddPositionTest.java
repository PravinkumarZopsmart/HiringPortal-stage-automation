package com.scripts;

import com.pages.AddPosition;
import com.pages.Dashboard;
import com.pages.LoginPage;
import com.utils.ElementHelpers;
import com.utils.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AddPositionTest {
    private static WebDriver driver;
    private static final String URL = "https://stage.hiringmotion.com/";

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

    @Test
    public void addPositionTest() throws InterruptedException {
        int expectedNumberOfPositions = Dashboard.getNumberOf(driver,"Positions") + 1;
        int actualNumberOfPositions = AddPosition.addPositions(driver);
        Assert.assertEquals(actualNumberOfPositions, expectedNumberOfPositions);
    }
    @AfterSuite
    public void endDriverSession() {
        driver.close();
        driver.quit();
    }
}
