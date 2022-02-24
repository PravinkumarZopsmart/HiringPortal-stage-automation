package com.pages;

import com.utils.ElementHelpers;
import com.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Users extends Base {
    private static final By header = By.cssSelector(".head-text-users-list");
    private static final By addUserButton = By.cssSelector(".add-user-button button");
    private static final By addUserForm = By.cssSelector(".MuiDialog-container.MuiDialog-scrollPaper > div");
    private static final By nameInput = By.xpath("//input[@name='name']");
    private static final By emailInput = By.xpath("//input[@name='email']");
    private static final By roleInput = By.xpath("//input[@name='role']");
    private static final By submitButton = By.cssSelector(".MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-12 button");
    private static final By deleteButton = By.cssSelector(".user-actions-container > svg");
    private static final By closeButton = By.cssSelector("header.MuiPaper-root button:nth-child(2)");
    private static final int nameIndex = 0;
    private static final int emailIndex = 1;
    private static final int roleIndex = 2;
    private static final int createdAtIndex = 3;
    private static final int statusIndex = 4;

    public static String getPageHeader(WebDriver driver) {
        try {
            return driver.findElement(header).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isAddUserFormOpen(WebDriver driver) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver, addUserForm);
            return driver.findElement(addUserForm).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static void openAddUserForm(WebDriver driver) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver, addUserButton);
            driver.findElement(addUserButton).click();
        } catch (Exception e) {
            System.out.println(e.getClass());
            WebDriverUtil.takeScreenShot(driver, "openAddUser");
        }
    }

    public static void closeAddUserForm(WebDriver driver) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver, closeButton);
            driver.findElement(closeButton).click();
        } catch (Exception e) {
            System.out.println(e.getClass());
            WebDriverUtil.takeScreenShot(driver, "closeAddUser");
        }
    }

    public static Map<String, String> getUserDetails(WebElement row) {
        Map<String, String> applicantDetails = new HashMap<>();
        try {
            List<WebElement> columns = row.findElements(By.cssSelector("td"));
            String name = columns.get(nameIndex).getText();
            String email = columns.get(emailIndex).getText();
            String role = columns.get(roleIndex).getText();
            String status = columns.get(statusIndex).getText();
            String createdAt = columns.get(createdAtIndex).getText();
            applicantDetails.put("name", name);
            applicantDetails.put("email", email);
            applicantDetails.put("status", status);
            applicantDetails.put("createdAt", createdAt);
            return applicantDetails;
        } catch (Exception e) {
            return applicantDetails;
        }
    }

    public static void addUser(WebDriver driver, String name, String email, String role) {
        try {
            openAddUserForm(driver);
            ElementHelpers.waitForElementToBeVisible(driver, addUserForm);
            driver.findElement(nameInput).sendKeys(name);
            driver.findElement(emailInput).sendKeys(email);
            driver.findElement(roleInput).click();
        } catch (Exception e) {
            System.out.println(e.getClass());
            WebDriverUtil.takeScreenShot(driver, "adduser");
        }
    }

    public static void deleteUser(WebDriver driver, String name) {
        try {

        } catch (Exception e) {
            System.out.println(e.getClass());
            WebDriverUtil.takeScreenShot(driver,"deleteUser");
        }
    }
}
