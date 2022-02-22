package com.scripts;

import com.pages.*;
import com.utils.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class UserPageTest {
    private static WebDriver driver;
    private static final String URL = "https://stage.hiringmotion.com/";
    private static final String USERS = "Users";

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
        Base.selectSideBarPage(driver, USERS);
    }

    @Test
    public void testUsersHeader(){
        String expectedHeader = USERS.toUpperCase();
        String actualHeader = Users.getPageHeader(driver).toUpperCase();
        assertEquals(actualHeader,expectedHeader);
    }
}
