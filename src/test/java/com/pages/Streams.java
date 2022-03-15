package com.pages;

import com.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Streams extends Base {
    private static final By header = By.cssSelector(".head-text-streams-list");
    private static final int nameIndex = 0;
    private static final int departmentIndex = 1;
    private static final int createdAtIndex = 2;
    private static final int statusIndex = 3;
    private static final int actionIndex = 4;

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
            return getStreamsDetails(row);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass());
            WebDriverUtil.takeScreenShot(driver, "getRowByDetailsByName");
        }
        return null;
    }

    public static Map<String, Object> getStreamsDetails(WebElement row) {
        try {
            Map<String, Object> userDetails = new HashMap<>();
            List<WebElement> userDetailsElements = row.findElements(By.tagName("td"));
            userDetails.put("name", userDetailsElements.get(nameIndex).getText());
            userDetails.put("department", userDetailsElements.get(departmentIndex).getText());
            userDetails.put("createdAt", userDetailsElements.get(createdAtIndex).getText());
            userDetails.put("status", userDetailsElements.get(statusIndex).getText());
            return userDetails;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass());
            return null;
        }
    }
}
