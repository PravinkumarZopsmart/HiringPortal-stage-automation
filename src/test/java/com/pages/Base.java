package com.pages;

import com.utils.ElementHelpers;
import com.utils.WebDriverUtil;
import org.apache.commons.text.CaseUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

public class Base {
    private static final By sideBarButtonLocator = By.cssSelector(".side-bar-buttons a");
    private static final By tableContentsLocator = By.cssSelector(".MuiTableBody-root tr");
    private static final By nextPageButtonLocator = By.cssSelector(".MuiTablePagination-actions button:nth-child(2)");
    private static final By previousPageButtonLocator = By.cssSelector("MuiTablePagination-actions button:nth-child(1)");
    private static final By numberOfApplications = By.cssSelector(".MuiToolbar-root" +
            ".MuiToolbar-regular.MuiTablePagination-toolbar.MuiToolbar-gutters p");
    public static final By allRows = By.cssSelector(".MuiTableRow-root.table-row.MuiTableRow-hover");
    private static final By filters = By.cssSelector(".MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-12");
    private static final By applyFilterButton = By.xpath("//button[@data-testid='testApply']");
    private static final By clearFilterButton = By.cssSelector(".MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-12 button");
    private static final By filterBlock = By.cssSelector(".MuiPaper-root.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded");
    private static final By searchInput = By.xpath("//input[@type='text']");
    private static final By searchButton = By.xpath("//button[@data-testid='testSearchLens']");
    private static final By emptyRecords = By.className("empty-table-container__message");

    public static void selectSideBarPage(WebDriver driver, String button) {
        driver.get("https://stage.hiringmotion.com/");
        ElementHelpers.waitForElementToBeVisible(driver, sideBarButtonLocator);
        List<WebElement> sideButtons = driver.findElements(sideBarButtonLocator);
        for (WebElement sideButton : sideButtons) {
            if (sideButton.getText().equalsIgnoreCase(button)) {
                sideButton.click();
                break;
            }
        }
    }

    public static int getExpectedNumberOfRowsInCurrentPage(WebDriver driver) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver, tableContentsLocator);
            String applicationsCount = driver.findElement(numberOfApplications).getText();
            String[] countArray = applicationsCount.split(" ");
            String[] innerCountArray = countArray[0].split("-");
            return (Integer.parseInt(innerCountArray[1]) - Integer.parseInt(innerCountArray[0])) + 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getTotalNumberOfRowsInSection(WebDriver driver) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver, tableContentsLocator);
            String applicationsCount = driver.findElement(numberOfApplications).getText();
            String[] countArray = applicationsCount.split(" ");
            return Integer.parseInt(countArray[2]);
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getNumberOfRowsInCurrentPage(WebDriver driver) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver, tableContentsLocator);
            return driver.findElements(tableContentsLocator).size();
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
            return 0;
        }
    }

    public static boolean moveToNextPage(WebDriver driver) {
        ElementHelpers.waitForElementToBeVisible(driver, nextPageButtonLocator);
        WebElement nextPageButton = driver.findElement(nextPageButtonLocator);
        if (nextPageButton.isEnabled()) {
            nextPageButton.click();
            return true;
        }
        return false;
    }

    public static boolean moveToPreviousPage(WebDriver driver) {
        ElementHelpers.waitForElementToBeVisible(driver, previousPageButtonLocator);
        WebElement previousPageButton = driver.findElement(previousPageButtonLocator);
        if (previousPageButton.isEnabled()) {
            previousPageButton.click();
            return true;
        }
        return false;
    }

    public static void deleteScreenshots() {
        File folder = new File("./screenshot/");
        File[] files = folder.listFiles();
        assert files != null;
        for (File file : files) {
            file.delete();
        }
    }

    public static WebElement getRowByName(WebDriver driver, String name) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver, allRows);
            do {
                List<WebElement> rowsInCurrentPage = driver.findElements(allRows);
                for (WebElement webElement : rowsInCurrentPage) {
                    WebElement element = webElement.findElement(By.tagName("td"));
                    if (element.getText().equalsIgnoreCase(name)) {
                        return webElement;
                    }
                }
            } while (Base.moveToNextPage(driver));
        } catch (Exception e) {
            System.out.println(e.getClass());
            WebDriverUtil.takeScreenShot(driver, "getRowByName");
        }
        return null;
    }

    public static List<WebElement> getAllRows(WebDriver driver) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver, allRows);
            return driver.findElements(allRows);
        } catch (Exception e) {
            return null;
        }
    }

    public static void selectFilter(WebDriver driver, String filterName, String filterToApply) {
        try {
            filterName = CaseUtils.toCamelCase(filterName,false,' ');
            String filter = "//button[@data-testid='testFilter-" + filterName + "']";
            ElementHelpers.waitForElementToBeEnabled(driver, By.xpath(filter));
            ElementHelpers.waitForElementToBeVisible(driver, driver.findElement(By.xpath(filter)));
            ElementHelpers.waitForElementToBeClickable(driver, By.xpath(filter));
            Thread.sleep(2000);
            WebElement element = driver.findElement(By.xpath(filter));
            element.click();
            ElementHelpers.waitForElementToBeVisible(driver, filters);
            Thread.sleep(3000);
            List<WebElement> filtersRow = driver.findElements(filters);
            for (WebElement row : filtersRow) {
                System.out.println(row.findElement(By.tagName("p")).getText());
                if (row.findElement(By.tagName("p")).getText().equalsIgnoreCase(filterToApply)) {
                    row.findElement(By.tagName("input")).click();
                    driver.findElement(applyFilterButton).click();
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            WebDriverUtil.takeScreenShot(driver, "selectFilter");
        }
    }

    public static void clearFilterField(WebDriver driver, String filterName) {
        try {
            filterName = filterName.toLowerCase();
            String filter = "//button[@data-testid='testFilter-" + filterName + "']";
            ElementHelpers.waitForElementToBeVisible(driver, driver.findElement(By.xpath(filter)));
            WebElement element = driver.findElement(By.xpath(filter));
            element.click();
            ElementHelpers.waitForElementToBeVisible(driver, filters);
            driver.findElement(clearFilterButton).click();
            ElementHelpers.waitForElementToHide(driver, filterBlock);
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            WebDriverUtil.takeScreenShot(driver, "selectFilter");
        }
    }

    public static void searchInPage(WebDriver driver,String toSearch) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver,searchInput);
            driver.findElement(searchInput).sendKeys(toSearch);
            driver.findElement(searchButton).click();
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            WebDriverUtil.takeScreenShot(driver,"searchInPage");
        }
    }

    public static String getEmptyRecordsText(WebDriver driver) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver,emptyRecords);
            return driver.findElement(emptyRecords).getText();
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            WebDriverUtil.takeScreenShot(driver,"getEmptyRecords");
        }
        return null;
    }
}
