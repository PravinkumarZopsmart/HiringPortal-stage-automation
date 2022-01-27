package com.pages;

import com.utils.ElementHelpers;
import com.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private static final By loginButtonSelector = By.cssSelector(".form-box > button");
    private static final By emailInput = By.id("identifierId");
    private static final By nextButtonInGoogleLogin = By.id("identifierNext");
    private static final By passwordInput = By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input");
    private static final By passwordNextButtonInGoogleLogin = By.id("passwordNext");
    private static final String titleAfterLogin = "Dashboard | Hiring Motion";

    public static void login(WebDriver driver, String email, String password) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver, loginButtonSelector);
            driver.findElement(loginButtonSelector).click();
            WebDriverUtil.moveToNextTab(driver);
            enterEmailAndNext(driver, email);
            enterPasswordAndLogin(driver, password);
            WebDriverUtil.moveToFirstTab(driver);
            ElementHelpers.waitForTitleToBe(driver, titleAfterLogin);
        } catch (Exception e) {
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