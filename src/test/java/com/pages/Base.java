package com.pages;

import com.utils.ElementHelpers;
import com.utils.WebDriverUtil;
import org.openqa.selenium.*;

import java.io.File;
import java.util.*;

public class Base {
    private static final By sideBarButtonLocator = By.cssSelector(".side-bar-buttons a");
    private static final By tableContentsLocator = By.cssSelector(".MuiTableBody-root tr");
    private static final By nextPageButtonLocator = By.cssSelector(".MuiTablePagination-actions button:nth-child(2)");
    private static final By previousPageButtonLocator = By.cssSelector("MuiTablePagination-actions button:nth-child(1)");
    private static final By numberOfApplications = By.cssSelector(".MuiToolbar-root" +
            ".MuiToolbar-regular.MuiTablePagination-toolbar.MuiToolbar-gutters p");
    public static final By allRows = By.cssSelector(".MuiTableRow-root.table-row.MuiTableRow-hover");
//    private static final By filters = By.cssSelector(".MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-12");//to be removed
    private static final By filterButton = By.cssSelector(".MuiTableContainer-root.table-container button.MuiButton-text");
    private static final By applyFilterButton = By.xpath("//button[@data-testid='testApply']");
    private static final By clearFilterButton = By.cssSelector(".MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-12 button");
    private static final By filterBlock = By.xpath("//body/div[3]/div[3]/div");
    private static final By filters = By.xpath("//body/div[3]/div[3]/div/div[1]/div[2]");
    private static final By filterActions = By.xpath("//button[@type='submit']");
    private static final By filterOptionsBlock = By.className("MuiAutocomplete-popper");
    private static final By searchInput = By.xpath("//div[@class='MuiTableContainer-root table-container']//input");
    private static final By searchButton = By.xpath("//div[@class='MuiTableContainer-root table-container']//button[@data-testid='testSearchLens']");
    private static final By emptyRecords = By.className("empty-table-container__message");
    public static final By confirmButton = By.xpath("//button[@data-testid='testConfirmSave']");
    public static final By submitButton = By.xpath("//button[@data-testid='testSubmit']");
    public static final By closeButton = By.xpath("//button[@data-testid='testClose']");

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
            ElementHelpers.waitForElementToBeVisible(driver,filterButton);
            driver.findElement(filterButton).click(); ///html/body/div[3]/div[3]/div/div[1]/div[2]
            ElementHelpers.waitForElementToBeVisible(driver,filters);
            WebElement filterElement = driver.findElement(filters);
            List<WebElement> filterElements = filterElement.findElements(By.tagName("div"));
            for (WebElement filter : filterElements) {
                String nameOfFilter = filter.findElement(By.tagName("h6")).getText();
                if(nameOfFilter.equalsIgnoreCase(filterName)) {
//                    filter.findElement(By.tagName("input")).sendKeys(filterToApply);
                    filter.findElement(By.tagName("input")).click();
                    ElementHelpers.waitForElementToBeVisible(driver,filterOptionsBlock);
                    WebElement filterOptionsBlockElement = driver.findElement(filterOptionsBlock);
                    List<WebElement> filterOptions = filterOptionsBlockElement.findElements(By.tagName("li"));
                    for (WebElement filterOption : filterOptions) {
                        if(filterOption.getText().equalsIgnoreCase(filterToApply)){
                            filterOption.findElement(By.tagName("input")).click();
                            break;
                        }
                    }
                    Thread.sleep(5000);
                    break;
                }
            }
            driver.findElement(filterActions).click();
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            WebDriverUtil.takeScreenShot(driver, "selectFilter");
        }
    }

    public static void clearFilterField(WebDriver driver, String filterName) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver,filterButton);
            driver.findElement(filterButton).click(); ///html/body/div[3]/div[3]/div/div[1]/div[2]
            ElementHelpers.waitForElementToBeVisible(driver,filters);
            driver.findElements(filterActions).get(1).click();
            ElementHelpers.waitForElementToHide(driver, filterBlock);
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            WebDriverUtil.takeScreenShot(driver, "selectFilter");
        }
    }

    public static void searchInPage(WebDriver driver, String toSearch) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver, searchInput);
            ElementHelpers.waitForElementToBeClickable(driver,searchInput);
            driver.findElement(searchInput).sendKeys(toSearch);
            driver.findElement(searchButton).click();
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            WebDriverUtil.takeScreenShot(driver, "searchInPage");
        }
    }

    public static String getEmptyRecordsText(WebDriver driver) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver, emptyRecords);
            return driver.findElement(emptyRecords).getText();
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            WebDriverUtil.takeScreenShot(driver, "getEmptyRecords");
        }
        return null;
    }
}
