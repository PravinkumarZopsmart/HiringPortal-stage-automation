package com.pages;

import com.utils.ElementHelpers;
import com.utils.WebDriverUtil;
import org.openqa.selenium.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Levels extends Base {
    private static final By header = By.cssSelector(".head-text-levels-list");
    private static final By inputName = By.name("name");
    private static final By submitButton = By.xpath("//button[@data-testid='testSubmit']");
    private static final int nameIndex = 0;
    private static final int createdAtIndex = 1;
    private static final int statusIndex = 2;
    private static final int actionIndex = 3;

    public static String getPageHeader(WebDriver driver) {
        try {
            return driver.findElement(header).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public static Map<String, Object> getRowDetailsByName(WebDriver driver, String name) {
        try {
            WebElement row = getRowByName(driver, name);
            return getLevelsDetails(row);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass());
            WebDriverUtil.takeScreenShot(driver, "getRowByDetailsByName");
        }
        return null;
    }

    public static Map<String, Object> getLevelsDetails(WebElement row) {
        try {
            Map<String, Object> userDetails = new HashMap<>();
            List<WebElement> userDetailsElements = row.findElements(By.tagName("td"));
            userDetails.put("name", userDetailsElements.get(nameIndex).getText());
            userDetails.put("createdAt", userDetailsElements.get(createdAtIndex).getText());
            userDetails.put("status", userDetailsElements.get(statusIndex).getText());
            userDetails.put("actionButtons", userDetailsElements.get(actionIndex).findElements(By.tagName("button")).size());
            boolean deleteButton = ((int) userDetails.get("actionButtons")) == 2;
            userDetails.put("isDelete", deleteButton);
            return userDetails;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass());
            return null;
        }
    }

    public static void editName(WebDriver driver, String nameToChange, String name) {
        try {
            WebElement row = getRowByName(driver, nameToChange);
            assert row != null;
            List<WebElement> userDetailsElements = row.findElements(By.tagName("td"));
            userDetailsElements.get(actionIndex).findElement(By.cssSelector(".level-actions-container div button")).click();
            ElementHelpers.waitForElementToBeVisible(driver, inputName);
            WebElement inputField = driver.findElement(inputName);
            inputField.sendKeys(Keys.chord(Keys.COMMAND, "a"));
            inputField.sendKeys(Keys.DELETE);
            inputField.sendKeys(name);
            driver.findElement(submitButton).click();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass());
            WebDriverUtil.takeScreenShot(driver, "editName");
        }
    }
}
