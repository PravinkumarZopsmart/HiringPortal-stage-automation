package com.pages;

import com.utils.ElementHelpers;
import com.utils.WebDriverUtil;
import org.openqa.selenium.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Agencies extends Base{
    private static final By header = By.cssSelector(".head-text-sources-list");
    private static final By deleteButton = By.className("delete-source-icon");
    private static final By confirmButton = By.xpath("//button[@data-testid='testConfirmSave']");
    private static final int nameIndex = 0;
    private static final int emailIndex = 1;
    private static final int phoneIndex = 2;
    private static final int typeIndex = 3;
    private static final int createdAt = 4;
    private static final int statusIndex = 5;
    private static final int actionIndex = 6;


    public static String getPageHeader(WebDriver driver) {
        try {
            return driver.findElement(header).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public static Map<String, Object> getAgenciesDetails(WebElement row) {
        try {
            Map<String, Object> agencyDetails = new HashMap<>();
            List<WebElement> agencyDetailsElements = row.findElements(By.tagName("td"));
            agencyDetails.put("name", agencyDetailsElements.get(nameIndex).getText());
            agencyDetails.put("email", agencyDetailsElements.get(emailIndex).getText());
            agencyDetails.put("phone", agencyDetailsElements.get(phoneIndex).getText());
            agencyDetails.put("type", agencyDetailsElements.get(typeIndex).getText());
            agencyDetails.put("createdAt", agencyDetailsElements.get(createdAt).getText());
            agencyDetails.put("status", agencyDetailsElements.get(statusIndex).findElement(By.tagName("input")).isSelected());
            agencyDetails.put("actionButtons", agencyDetailsElements.get(actionIndex).findElements(By.tagName("svg")).size());
            boolean deleteButton = ((int) agencyDetails.get("actionButtons")) == 2;
            agencyDetails.put("isDelete", deleteButton);
            return agencyDetails;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass());
            return null;
        }
    }

    public static void deleteAgencies(WebDriver driver,String name) {
        try {
            WebElement row = getRowByName(driver, name);
            assert row != null;
            row.findElement(deleteButton).click();
            ElementHelpers.waitForElementToBeVisible(driver,confirmButton);
            Thread.sleep(3000);
            driver.findElement(confirmButton).click();
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            WebDriverUtil.takeScreenShot(driver, "changeStatus");
        }
    }

    public static void changeStatus(WebDriver driver,String name) {
        try {
            WebElement row = getRowByName(driver, name);
            assert row != null;
            List<WebElement> agencyDetailsElements = row.findElements(By.tagName("td"));
            WebElement changeStatusButton = agencyDetailsElements.get(statusIndex).findElement(By.tagName("input"));
            changeStatusButton.click();
            ElementHelpers.waitForElementToBeVisible(driver,confirmButton);
            Thread.sleep(3000);
            driver.findElement(confirmButton).click();
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            WebDriverUtil.takeScreenShot(driver, "changeStatus");
        }
    }

    public static Map<String, Object> getAgencyByName (WebDriver driver,String name){
        try {
            return getAgenciesDetails(getRowByName(driver,name));
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
        }
        return null;
    }
}
