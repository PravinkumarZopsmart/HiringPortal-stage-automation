package com.pages;

import org.openqa.selenium.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Agencies {
    private static final By header = By.cssSelector(".head-text-sources-list");
    private static final int nameIndex = 0;
    private static final int emailIndex = 1;
    private static final int phoneIndex = 2;
    private static final int typeIndex = 3;
    private static final int createdAt = 4;
    private static final int statusIndex = 4;
    private static final int actionIndex = 4;


    public static String getPageHeader(WebDriver driver) {
        try {
            return driver.findElement(header).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public static Map<String, Object> getStreamsDetails(WebElement row) {
        try {
            Map<String, Object> agencyDetails = new HashMap<>();
            List<WebElement> agencyDetailsElements = row.findElements(By.tagName("td"));
            agencyDetails.put("name", agencyDetailsElements.get(nameIndex).getText());
            agencyDetails.put("email", agencyDetailsElements.get(emailIndex).getText());
            agencyDetails.put("phone", agencyDetailsElements.get(phoneIndex).getText());
            agencyDetails.put("type", agencyDetailsElements.get(typeIndex).getText());
            agencyDetails.put("createdAt", agencyDetailsElements.get(typeIndex).getText());
            agencyDetails.put("status", agencyDetailsElements.get(statusIndex).findElement(By.tagName("input")).isSelected());
            agencyDetails.put("action", agencyDetailsElements.get(typeIndex).getText());
            agencyDetails.put("actionButtons", agencyDetailsElements.get(actionIndex).findElements(By.tagName("button")).size());
            boolean deleteButton = ((int) agencyDetails.get("actionButtons")) == 2;
            agencyDetails.put("isDelete", deleteButton);
            return agencyDetails;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass());
            return null;
        }
    }
}
