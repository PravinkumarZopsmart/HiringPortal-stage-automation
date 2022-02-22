package com.pages;

import com.utils.ElementHelpers;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class Base {
    private static final By sideBarButtonLocator = By.cssSelector(".side-bar-buttons a");
    private static final By tableContentsLocator = By.cssSelector(".MuiTableBody-root tr");
    private static final By nextPageButtonLocator = By.cssSelector(".MuiTablePagination-actions button:nth-child(2)");
    private static final By previousPageButtonLocator = By.cssSelector("MuiTablePagination-actions button:nth-child(1)");
    private static final By numberOfApplications = By.cssSelector(".MuiToolbar-root" +
            ".MuiToolbar-regular.MuiTablePagination-toolbar.MuiToolbar-gutters p");

    public static void selectSideBarPage(WebDriver driver, String button) {
        driver.get("https://stage.hiringmotion.com/");
        ElementHelpers.waitForElementToBeVisible(driver,sideBarButtonLocator);
        List<WebElement> sideButtons = driver.findElements(sideBarButtonLocator);
        for (WebElement sideButton : sideButtons) {
            if (sideButton.getText().equalsIgnoreCase(button)) {
                sideButton.click();
                break;
            }
        }
    }

    public static int getNumberOfApplications(WebDriver driver) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver,tableContentsLocator);
            String applicationsCount = driver.findElement(numberOfApplications).getText();
            String[] countArray = applicationsCount.split(" ");
            return Integer.parseInt(countArray[2]);
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getNumberOfRowsInCurrentPage(WebDriver driver) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver,tableContentsLocator);
            return driver.findElements(tableContentsLocator).size();
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
            return 0;
        }
    }

    public static boolean moveToNextPage(WebDriver driver) {
        ElementHelpers.waitForElementToBeVisible(driver,nextPageButtonLocator);
        WebElement nextPageButton = driver.findElement(nextPageButtonLocator);
        if (nextPageButton.isEnabled()) {
            nextPageButton.click();
            return true;
        }
        return false;
    }

    public static boolean moveToPreviousPage(WebDriver driver) {
        ElementHelpers.waitForElementToBeVisible(driver,previousPageButtonLocator);
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
}
