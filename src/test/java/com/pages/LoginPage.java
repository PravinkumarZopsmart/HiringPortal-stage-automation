package com.pages;

import com.utils.ElementHelpers;
import com.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class LoginPage {

    // -----For headless mode----
    private static final boolean headless = true;
    private static final By emailInput = By.xpath("/html/body/div/div[2]/div[2]/div[1]/form/div/div/div/div/div/input[1]");
    private static final By nextButtonInGoogleLogin = By.xpath("/html/body/div/div[2]/div[2]/div[1]/form/div/div/input");
    private static final By passwordInput = By.xpath("/html/body/div[1]/div[2]/div/div[2]/form/span/div/div[2]/input");
    private static final By passwordNextButtonInGoogleLogin = By.xpath("/html/body/div[1]/div[2]/div/div[2]/form/span/div/input[2]");

    //-----For non headless mode-----
//    private static final boolean headless = false;
//    private static final By emailInput = By.id("identifierId");
//    private static final By nextButtonInGoogleLogin = By.id("identifierNext");
//    private static final By passwordInput = By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input");
//    private static final By passwordNextButtonInGoogleLogin = By.id("passwordNext");

    private static final By loginButtonSelector = By.cssSelector(".form-box > button");
    private static final By allowAccess = By.id("submit_approve_access");
    private static final String titleAfterLogin = "Dashboard | Hiring Motion";

    public static void login(WebDriver driver, String email, String password) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver, loginButtonSelector);
            driver.findElement(loginButtonSelector).click();
            WebDriverUtil.moveToNextTab(driver);
            enterEmailAndNext(driver, email);
            enterPasswordAndLogin(driver, password);
            if (headless) {
                //to perform Scroll on application using Selenium
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,350)", "");
                ElementHelpers.waitForElementToBeEnabled(driver, allowAccess);
                driver.findElement(allowAccess).click();
            }
            WebDriverUtil.moveToFirstTab(driver);
            ElementHelpers.waitForTitleToBe(driver, titleAfterLogin);
        } catch (Exception e) {
            try {
                WebDriverUtil.takeScreenShot(driver, "loginPage");
            } catch (IOException io) {
                System.out.println(io.getClass());
            }
            e.printStackTrace();
            System.out.println(e.getClass());
        }
    }

    public static void enterEmailAndNext(WebDriver driver, String email) {
        ElementHelpers.waitForElementToBeVisible(driver, emailInput);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(nextButtonInGoogleLogin).click();
    }

    public static void enterPasswordAndLogin(WebDriver driver, String password) {
        ElementHelpers.waitForElementToBeVisible(driver, passwordInput);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(passwordNextButtonInGoogleLogin).click();
    }
}