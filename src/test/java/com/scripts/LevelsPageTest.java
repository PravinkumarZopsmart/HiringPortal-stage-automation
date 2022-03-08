package com.scripts;

import com.pages.*;
import com.utils.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.Map;
import java.util.Objects;

import static org.testng.Assert.*;

public class LevelsPageTest {
    private static WebDriver driver;
    private static final String URL = "https://stage.hiringmotion.com/";
    private static final String LEVELS = "Levels";

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
        Base.selectSideBarPage(driver, LEVELS);
    }

    @Test
    public void testLevelsHeader() {
        String expectedHeader = LEVELS.toUpperCase();
        String actualHeader = Objects.requireNonNull(Levels.getPageHeader(driver)).toUpperCase();
        assertEquals(actualHeader, expectedHeader);
    }

    @Test
    public void testInactiveLevelStatus() {
        Map<String, Object> levelsDetails = Levels.getRowDetailsByName(driver, "Tester");
        assert levelsDetails != null;
        boolean isDeleteButton = (boolean) levelsDetails.get("isDelete");
        assertEquals(levelsDetails.get("status"), "INACTIVE");
        assertFalse(isDeleteButton);
    }

    @Test
    public void testActiveLevelStatus() {
        Map<String, Object> levelsDetails = Levels.getRowDetailsByName(driver, "SSDE");
        assert levelsDetails != null;
        boolean isDeleteButton = (boolean) levelsDetails.get("isDelete");
        assertEquals(levelsDetails.get("status"), "ACTIVE");
        assertTrue(isDeleteButton);
    }

    @Test
    public void testEditFunction() {
        Levels.editName(driver,"Tester","Testers");
        Base.selectSideBarPage(driver,"Levels");
        Map<String, Object> levelsDetails = Levels.getRowDetailsByName(driver, "Testers");
        assert levelsDetails != null;
        assertEquals(levelsDetails.get("name"),"Testers");
        System.out.println(levelsDetails);
        Levels.editName(driver,"Testers","Tester");
        Base.selectSideBarPage(driver,"Levels");
        levelsDetails = Levels.getRowDetailsByName(driver, "Tester");
        assert levelsDetails != null;
        assertEquals(levelsDetails.get("name"),"Tester");
    }

    @AfterSuite
    public void endSession() {
        driver.close();
        driver.quit();
    }
}
