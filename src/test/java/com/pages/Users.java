package com.pages;

import com.utils.ElementHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Users {
    private static final By header = By.cssSelector(".head-text-users-list");
    private static final By addUserButton = By.cssSelector(".add-user-button button");
    private static final By addUserForm = By.cssSelector(".MuiDialog-container.MuiDialog-scrollPaper > div");
    private static final By nameInput = By.xpath("//input[@name='name']");
    private static final By emailInput = By.xpath("//input[@name='email']");
    private static final By roleInput = By.xpath("//input[@name='role']");
    private static final By submitButton = By.cssSelector(".MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-12 button");
    private static final By deleteButton = By.cssSelector(".user-actions-container > svg");

    public static String getPageHeader(WebDriver driver){
        try {
            return driver.findElement(header).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isAddUserFormOpen(WebDriver driver) {
        try {
            ElementHelpers.waitForElementToBeVisible(driver,addUserForm);
            return driver.findElement(addUserButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
