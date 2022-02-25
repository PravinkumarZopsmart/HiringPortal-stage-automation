package com.scripts;

import com.pages.*;
import com.utils.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;

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
    public void testUsersHeader() {
        String expectedHeader = USERS.toUpperCase();
        String actualHeader = Users.getPageHeader(driver).toUpperCase();
        assertEquals(actualHeader, expectedHeader);
    }

    @Test
    public void testAddAndCloseUserForm() {
        assertFalse(Users.isAddUserFormOpen(driver));
        Users.openAddUserForm(driver);
        assertTrue(Users.isAddUserFormOpen(driver));
        Users.closeAddUserForm(driver);
    }

    @Test
    public void testUsersPerPage() {
        do {
            int actualUserPerPage = Users.getNumberOfRowsInCurrentPage(driver);
            int expectedUserPerPage = Users.getExpectedNumberOfRowsInCurrentPage(driver);
            assertEquals(actualUserPerPage, expectedUserPerPage);
        } while (Users.moveToNextPage(driver));
    }

    @Test
    public void testUsersStatus() {
        do {
            List<WebElement> usersRow = Base.getAllRows(driver);
            assert usersRow != null;
            for (WebElement row : usersRow) {
                Map<String, Object> userDetails = Users.getUserDetails(row);
                assert userDetails != null;
                boolean status = (boolean) userDetails.get("status");
                assertTrue(status);
            }
        } while (Base.moveToNextPage(driver));
    }

    @Test
    public void testStatusFilter() throws InterruptedException {
        Base.selectFilter(driver,"status","inactive");
        Thread.sleep(5000);
        do {
            List<WebElement> usersRow = Base.getAllRows(driver);
            assert usersRow != null;
            for (WebElement row : usersRow) {
                Map<String, Object> userDetails = Users.getUserDetails(row);
                assert userDetails != null;
                boolean status = (boolean) userDetails.get("status");
                assertFalse(status);
            }
        } while (Base.moveToNextPage(driver));
    }

    @AfterSuite
    public void endSession() {
        driver.close();
        driver.quit();
    }
}
