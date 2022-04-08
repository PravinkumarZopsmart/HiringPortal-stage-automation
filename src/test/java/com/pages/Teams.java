package com.pages;

import com.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teams extends Base {
    private static final By addTeamsButton = By.cssSelector(".add-team-button button");
    private static final By addTeamBlock = By.cssSelector(".MuiDialog-container.MuiDialog-scrollPaper");
    private static final By inputTeamName = By.name("name");
    private static final By nameFilter = By.xpath("//*[@id='root']/div/main/div[2]/div/div[2]/div/div[1]/table/thead/tr/th[1]/div/span");
    private static final int nameIndex = 0;
    private static final int createdAtIndex = 1;
    private static final int statusIndex = 2;
    private static final int actionIndex = 3;

    public static String addTeam(WebDriver driver) {
        try {
            int randomNumber = (int) (Math.random() * 100);
            String randomString = String.valueOf(randomNumber);
            String teamName = "Test" + randomString;
            driver.findElement(addTeamsButton).click();
            ElementHelpers.waitForElementToBeVisible(driver, addTeamBlock);
            driver.findElement(inputTeamName).sendKeys(teamName);
            ElementHelpers.waitForElementToBeClickable(driver,submitButton);
            driver.findElement(submitButton).click();
            return teamName;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass().getName());
            WebDriverUtil.takeScreenShot(driver,"addTeam");
        }
        return null;
    }

    public static Map<String, Object> getRowDetailsByName(WebDriver driver, String name) {
        try {
            WebElement row = getRowByName(driver, name);
            return getTeamsDetails(row);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass());
            WebDriverUtil.takeScreenShot(driver, "getRowDetailsByName");
        }
        return null;
    }

    public static Map<String, Object> getTeamsDetails(WebElement row) {
        try {
            Map<String, Object> teamDetails = new HashMap<>();
            List<WebElement> teamDetailsElements = row.findElements(By.tagName("td"));
            teamDetails.put("name", teamDetailsElements.get(nameIndex).getText());
            teamDetails.put("createdAt", teamDetailsElements.get(createdAtIndex).getText());
            teamDetails.put("status", teamDetailsElements.get(statusIndex).getText());
            teamDetails.put("actionButtons", teamDetailsElements.get(actionIndex).findElements(By.tagName("button")).size());
            boolean deleteButton = ((int) teamDetails.get("actionButtons")) == 2;
            teamDetails.put("isDelete", deleteButton);
            return teamDetails;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass());
            return null;
        }
    }

    public static List<String> validateNameFilter(WebDriver driver) {
        List<String> teamNames = new ArrayList<>();
        driver.findElement(nameFilter).click();
        List<WebElement> rowsFilteredByName = driver.findElements(allRows);
        for (WebElement items : rowsFilteredByName) {
            teamNames.add(items.getText().split(" ")[0]);
        }
        return teamNames;
    }

    public static String validateCreatedAtFilter(WebDriver driver) {
        Base.selectFilter(driver, "Created At", "22-Oct-2021");
        String expectedTeamName = "Krogo";
        return expectedTeamName;
    }

    public static List<String> validateInactiveStatusFilter(WebDriver driver) {
        List<String> expectedTeamNames = new ArrayList<>();
        Base.clearFilterField(driver, "Created At");
        Base.selectFilter(driver, "Status", "INACTIVE");
        List<WebElement> allTeams = driver.findElements(allRows);
        for (WebElement items : allTeams) {
            expectedTeamNames.add(items.getText().split(" ")[0]);
        }
        return expectedTeamNames;
    }

    public static List<String> validateActiveStatusFilter(WebDriver driver) {
        List<String> expectedTeamNames = new ArrayList<>();
        Base.clearFilterField(driver, "Created At");
        Base.selectFilter(driver, "Status", "ACTIVE");
        List<WebElement> allTeams = driver.findElements(allRows);
        for (WebElement items : allTeams) {
            expectedTeamNames.add(items.getText().split(" ")[0]);
        }
        return expectedTeamNames;
    }

//    public static String deleteTeam(WebDriver driver) {
//        String expectedTeamName = "Test";
//        List<WebElement> allTeams = driver.findElements(allRows);
//        for (WebElement items : allTeams) {
//            if ((items.getText().split(" ")[0]).equals(expectedTeamName)) {
//                driver.findElement(deleteButton).click();
//                ElementHelpers.waitForElementToBeVisible(driver, okButton);
////                driver.findElement(okButton).click();
//            }
//        }
//        return expectedTeamName;
//    }
//
//    public static String editTeamName(WebDriver driver) {
//        String expectedTeamName = "Test2";
//        List<WebElement> allTeams = driver.findElements(allRows);
//        for (WebElement items : allTeams) {
//            if ((items.getText().split(" ")[0]).equals("Test")) {
//                driver.findElement(editButton).click();
//                driver.findElement(inputTeamName).sendKeys(expectedTeamName);
////                driver.findElement(submitButton).click();
//            }
//        }
//        return expectedTeamName;
//    }
}
