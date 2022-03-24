package com.scripts;

import com.pages.*;
import com.utils.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import java.util.*;

import static org.testng.Assert.*;

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
        List<WebElement> allRows = Base.getAllRows(driver);
        assert allRows != null;
        for (WebElement allRow : allRows) {
            Map<String, Object> rowDetails = Agencies.getAgenciesDetails(allRow);
            assert rowDetails != null;
            if ((boolean) rowDetails.get("status")) {
                assertTrue((boolean) rowDetails.get("isDelete"));
            } else {
                assertFalse((boolean) rowDetails.get("isDelete"));
            }
        }
    }

    @Test
    public void testNameSearchFilter() {
        String toSearch = "zo";
        Base.searchInPage(driver, toSearch);
        List<WebElement> allRows = Base.getAllRows(driver);
        assert allRows != null;
        for (WebElement allRow : allRows) {
            Map<String, Object> rowDetails = Agencies.getAgenciesDetails(allRow);
            assert rowDetails != null;
            String name = (String) rowDetails.get("name");
            name = name.toLowerCase();
            assertTrue(name.contains(toSearch));
        }
        Base.selectSideBarPage(driver, AGENCIES);
        toSearch = "in";
        Base.searchInPage(driver, toSearch);
        allRows = Base.getAllRows(driver);
        assert allRows != null;
        for (WebElement allRow : allRows) {
            Map<String, Object> rowDetails = Agencies.getAgenciesDetails(allRow);
            assert rowDetails != null;
            String name = (String) rowDetails.get("name");
            name = name.toLowerCase();
            assertTrue(name.contains(toSearch));
        }
    }

    @Test
    public void testEmailSearchFilter() {
        String toSearch = "zopsmart@gmail.com";
        Base.searchInPage(driver, toSearch);
        List<WebElement> allRows = Base.getAllRows(driver);
        assert allRows != null;
        for (WebElement allRow : allRows) {
            Map<String, Object> rowDetails = Agencies.getAgenciesDetails(allRow);
            assert rowDetails != null;
            String actualEmail = (String) rowDetails.get("email");
            assertEquals(actualEmail, toSearch);
        }
        Base.selectSideBarPage(driver, AGENCIES);
        toSearch = "test@gmail.com";
        Base.searchInPage(driver, toSearch);
        allRows = Base.getAllRows(driver);
        assert allRows != null;
        for (WebElement allRow : allRows) {
            Map<String, Object> rowDetails = Agencies.getAgenciesDetails(allRow);
            assert rowDetails != null;
            String actualEmail = (String) rowDetails.get("email");
            assertEquals(actualEmail, toSearch);
        }
    }

    @Test
    public void testPhoneSearchFilter() {
        String toSearch = "9999999999";
        Base.searchInPage(driver, toSearch);
        List<WebElement> allRows = Base.getAllRows(driver);
        assert allRows != null;
        for (WebElement allRow : allRows) {
            Map<String, Object> rowDetails = Agencies.getAgenciesDetails(allRow);
            assert rowDetails != null;
            String actualPhoneNumber = (String) rowDetails.get("phone");
            assertTrue(actualPhoneNumber.contains(toSearch));
        }
    }

    @Test
    public void testInvalidSearchFilter() {
        Base.searchInPage(driver, "invalid@34");
        List<WebElement> allRows = Base.getAllRows(driver);
        assert allRows != null;
        assertEquals(allRows.size(), 0);
        int actualNumberOfRows = Base.getNumberOfRowsInCurrentPage(driver);
        int totalActualNumberOfRows = Base.getTotalNumberOfRowsInSection(driver);
        assertEquals(actualNumberOfRows, 0);
        assertEquals(totalActualNumberOfRows, 0);
        String actualEmptyRecordsText = Base.getEmptyRecordsText(driver);
        String expectedEmptyRecordsText = "No Record Found!";
        assertEquals(actualEmptyRecordsText, expectedEmptyRecordsText);
    }

    @Test
    public void testTypeFilter() {
        String filterName = "type";
        String filterToApply = "careers";
        Base.selectFilter(driver, filterName, filterToApply);
        List<WebElement> allRows = Base.getAllRows(driver);
        assert allRows != null;
        if (allRows.size() > 0) {
            for (WebElement row : allRows) {
                Map<String, Object> rowDetails = Agencies.getAgenciesDetails(row);
                assert rowDetails != null;
                String type = (String) rowDetails.get("type");
                assertEquals(type.toLowerCase(), filterToApply);
            }
        }
    }

    @Test
    public void testStatusFilter() {
        String filterName= "status";
        String filterToApply = "ACTIVE";
        Base.selectFilter(driver, filterName, filterToApply);
        List<WebElement> allRows = Base.getAllRows(driver);
        assert allRows != null;
        if (allRows.size() > 0) {
            for (WebElement row : allRows) {
                Map<String, Object> rowDetails = Agencies.getAgenciesDetails(row);
                assert rowDetails != null;
                assertTrue((boolean) rowDetails.get("status"));
            }
        }
        Base.selectSideBarPage(driver,AGENCIES);
        filterToApply = "INACTIVE";
        Base.selectFilter(driver, filterName, filterToApply);
        List<WebElement> allRowsInSecondFilter = Base.getAllRows(driver);
        assert allRowsInSecondFilter != null;
        if (allRowsInSecondFilter.size() > 0) {
            for (WebElement row : allRowsInSecondFilter) {
                Map<String, Object> rowDetails = Agencies.getAgenciesDetails(row);
                assert rowDetails != null;
                assertFalse((boolean) rowDetails.get("status"));
            }
        }
    }

    @Test
    public void testDeleteAgency() {
        String name = "Fiberwindow";
        Agencies.deleteAgencies(driver,name);
        Map<String, Object> agencyDetails = Agencies.getAgencyByName(driver,name);
        assert agencyDetails != null;
        boolean isDelete =(boolean) agencyDetails.get("isDelete");
        assertFalse(isDelete);
        Agencies.changeStatus(driver,name);
        Map<String, Object> agencyDetailsAfterChangeStatus = Agencies.getAgencyByName(driver,name);
        assert agencyDetailsAfterChangeStatus != null;
        boolean isDeleteAfterChangeStatus =(boolean) agencyDetailsAfterChangeStatus.get("isDelete");
        assertTrue(isDeleteAfterChangeStatus);
    }

    @AfterSuite
    public void endSession() {
        driver.close();
        driver.quit();
    }
}
