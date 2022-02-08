package com.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class ElementHelpers {
    public static void waitForDOMToLoad(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public static void waitForDOMToLoad(WebDriver driver, long seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public static boolean waitForElementToBeVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            System.out.println("Error finding element --" + e.getClass());
            return false;
        }
    }

    public static void waitForElementToBeVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            WebDriverUtil.takeScreenShot(driver,"elementNotVisible");
            System.out.println("Error finding element --" + e.getClass());
        }
    }

    public static void waitForTitleToBe(WebDriver driver, String title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            wait.until(ExpectedConditions.titleIs(title));
        } catch (Exception e) {
            WebDriverUtil.takeScreenShot(driver,"title");
        }
    }

    public static void waitForElementToBeEnabled(WebDriver driver,WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            WebDriverUtil.takeScreenShot(driver,"title");
        }
    }

    public static void waitForElementToBeEnabled(WebDriver driver,By locator) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            WebDriverUtil.takeScreenShot(driver,"title");
        }
    }
}
