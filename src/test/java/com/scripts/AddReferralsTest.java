package com.scripts;
import com.pages.*;
import com.utils.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AddReferralsTest {
    private static WebDriver driver;
    private static final String URL = "https://stage.hiringmotion.com/";
    private static final String APPLICATION = "APPLICATIONS";

    @BeforeSuite
    public void setUpDriver() {
        driver = WebDriverUtil.startWebDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @BeforeClass
    public void loginToHiringPortal() {
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
    }

    @Test
    public void addReferralTest() throws InterruptedException {
        int expectedNumberOfPositions = Dashboard.getNumberOf(driver,"Referrals") + 1;
        int actualNumberOfPositions = Integer.parseInt(AddReferrals.addReferralss(driver));
        Assert.assertEquals(expectedNumberOfPositions, actualNumberOfPositions);
    }

    @AfterSuite
    public void endDriverSession() {
        driver.close();
        driver.quit();
    }
}
