package com.scripts;

import com.pages.Base;
import com.pages.LoginPage;
import com.pages.Referrals;
import com.utils.ElementHelpers;
import com.utils.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import java.util.Objects;

public class ReferralsPageTest {
    private static WebDriver driver;
    private static final String URL = "https://stage.hiringmotion.com/";
    private static final String REFERRALS = "REFERRALS";

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
        Base.selectSideBarPage(driver, REFERRALS);
    }

    @Test
    public void testHeader() {
        String actualHeader = Objects.requireNonNull(Referrals.getPageHeader(driver)).toUpperCase();
        assertEquals(actualHeader, REFERRALS);
    }

    @Test(dependsOnMethods = "testHeader")
    public static void testAddReferrals() {
        Referrals.addReferrals(driver);
        assertFalse(Referrals.isSubmitButton(driver));
        Referrals.closeAddReferralPage(driver);
    }

    @Test
    public static void testNumberOfReferrals() {
        int actualNumberOfReferrals = Referrals.getNumberOfRowsInCurrentPage(driver);
        int expectedNumberOfReferrals = Referrals.getNumberOfApplications(driver);
        assertEquals(actualNumberOfReferrals,expectedNumberOfReferrals);
    }

    @AfterSuite
    public void endSession(){
        driver.close();
        driver.quit();
    }
}
