package com.scripts;

import com.pages.Application;
import com.pages.Base;
import com.pages.LoginPage;
import com.pages.NavBar;
import com.utils.ElementHelpers;
import com.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class NavBarTest {
    private static WebDriver driver;
    private static final String URL = "https://stage.hiringmotion.com/";
    private static final String APPLICATIONS = "APPLICATIONS";
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
    public void selectSideBarPage() throws InterruptedException {
        Base.selectSideBarPage(driver, APPLICATIONS);
        Thread.sleep(2000);
    }



    @Test(priority = 1)
    public void searchApplicationsByName() {
        String expectedName = "Jaspreet";
        List<String> applicantsDetails = NavBar.searchApplication(driver, expectedName);
        String actualName = applicantsDetails.get(0);
        Assert.assertEquals(expectedName, actualName);
    }

    @Test(priority = 2)
    public void searchApplicationsByEmail() throws InterruptedException {
        Thread.sleep(2000);
        String expectedEmail = "jaspreet@gmail.com";
        List<String> applicantsDetails = NavBar.searchApplication(driver, expectedEmail);
        String actualName = applicantsDetails.get(1);
        Assert.assertEquals(expectedEmail, actualName);
    }

    @Test(priority = 3)
    public void searchApplicationsByPhone() throws InterruptedException {
        Thread.sleep(2000);
        String expectedPhone = "+919378567487";
        String phoneNumber = "9378567487";
        List<String> applicantsDetails = NavBar.searchApplication(driver, phoneNumber);
        String actualName = applicantsDetails.get(2);
        Assert.assertEquals(expectedPhone, actualName);
    }

    @Test(priority = 4)
    public void testBellNotification() {
        NavBar.validateBellNotification(driver);
    }

    @Test(priority = 5)
    public void testLogout() {
        String expectedText = "Sign in with Google";
        Assert.assertEquals(NavBar.validateLogout(driver), expectedText);
    }


    @AfterSuite
    public void endWebDriverSession() throws InterruptedException {
        driver.close();
        driver.quit();
    }
}
