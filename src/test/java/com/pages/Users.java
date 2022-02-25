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
    private static final By closeButton = By.cssSelector("header.MuiPaper-root button:nth-child(2)");
    private static final By deleteButton = By.className("delete-user-icon");
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

    public static Map<String, Object> getUserDetails(WebElement row) {
        try {
            Map<String, Object> userDetails = new HashMap<>();
            List<WebElement> userDetailsElements = row.findElements(By.tagName("td"));
            userDetails.put("name",userDetailsElements.get(nameIndex).getText());
            userDetails.put("email",userDetailsElements.get(emailIndex).getText());
            userDetails.put("role",userDetailsElements.get(roleIndex).getText());
            userDetails.put("createdAt",userDetailsElements.get(createdAtIndex).getText());
            userDetails.put("status",userDetailsElements.get(statusIndex).findElement(By.tagName("input")).isSelected());
            return userDetails;
        } catch (Exception e) {
            return null;
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

    public static Map<String,Object> getUserDetailsByName(WebDriver driver,String name) {
        try {
        WebElement row = getRowByName(driver,name);
        return getUserDetails(row);
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
        return null;
    }

    public static void deleteUser(WebDriver driver, String name) {
        try {
            WebElement row = getRowByName(driver,name);
            assert row != null;
            row.findElement(deleteButton).click();
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }
}
