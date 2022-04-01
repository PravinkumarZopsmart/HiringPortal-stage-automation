package com.scripts;

import com.pages.*;
import com.utils.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.testng.Assert.*;

public class StreamsPageTest {
    private static WebDriver driver;
    private static final String URL = "https://stage.hiringmotion.com/";
    private static final String STREAMS = "STREAMS";

    @BeforeSuite
    public void setUpDriver() {
//        Base.deleteScreenshots();
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
        Base.selectSideBarPage(driver, STREAMS);
    }

    @Test
    public void testStreamsHeader() {
        String actualHeader = Objects.requireNonNull(Streams.getPageHeader(driver)).toUpperCase();
        assertEquals(actualHeader, STREAMS);
    }

    @Test
    public void testSearchFilter() {
        String toSearch = "Go";
        Base.searchInPage(driver, toSearch);
        List<WebElement> allRows = Base.getAllRows(driver);
        assert allRows != null;
        for (WebElement allRow : allRows) {
            Map<String, Object> rowDetails = Streams.getStreamsDetails(allRow);
            assert rowDetails != null;
            String name = (String) rowDetails.get("name");
            assertTrue(name.contains(toSearch));
        }
        Base.selectSideBarPage(driver, STREAMS);
        toSearch = "Pr";
        Base.searchInPage(driver, toSearch);
        allRows = Base.getAllRows(driver);
        assert allRows != null;
        for (WebElement allRow : allRows) {
            Map<String, Object> rowDetails = Streams.getStreamsDetails(allRow);
            assert rowDetails != null;
            String name = (String) rowDetails.get("name");
            assertTrue(name.contains(toSearch));
        }
    }

    @Test
    public void testInvalidSearchFilter() {
        Base.searchInPage(driver, "sdfs");
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
    public void testDepartmentFilter() {
        String filterName = "Department";
        String filterToApply = "Engineering";
        Base.selectFilter(driver, filterName, filterToApply);
        List<WebElement> allRows = Base.getAllRows(driver);
        assert allRows != null;
        if (allRows.size() > 0) {
            for (WebElement allRow : allRows) {
                Map<String, Object> rowDetails = Streams.getStreamsDetails(allRow);
                assert rowDetails != null;
                String name = (String) rowDetails.get("department");
                assertEquals(name, filterToApply);
            }
        }
    }

    @Test
    public void testCreatedAtFilter() {
        String filterName = "Created At";
        String filterToApply = "01-Dec-2021";
        Base.selectFilter(driver, filterName, filterToApply);
        List<WebElement> allRows = Base.getAllRows(driver);
        assert allRows != null;
        if (allRows.size() > 0) {
            for (WebElement allRow : allRows) {
                Map<String, Object> rowDetails = Streams.getStreamsDetails(allRow);
                assert rowDetails != null;
                String date = (String) rowDetails.get("createdAt");
                assertEquals(date, filterToApply);
            }
        }
    }

    @AfterSuite
    public void endSession() {
        driver.close();
        driver.quit();
    }
}
