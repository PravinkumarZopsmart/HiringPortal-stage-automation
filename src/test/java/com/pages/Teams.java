package com.pages;
import com.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Teams extends Base {
    private static final By teamsSection = By.xpath("//*[@id='root']/div/div/div/div[2]/div/div[9]/a/div/div[2]");
    private static final By addTeamsButton = By.xpath("//*[@id='root']/div/main/div[2]/div/div[1]/div/div/button/span[1]");
    private static final By addTeamTitle = By.className("MuiToolbar-root MuiToolbar-regular MuiToolbar-gutters");
    private static final By teamName = By.name("name");
    private static final By save = By.cssSelector("body > div.MuiDialog-root.form_overlay__team_form > div.MuiDialog-container.MuiDialog-scrollPaper > div > div > div > div > form > div > div:nth-child(2) > div > button > span.MuiButton-label");
    private static final By addTeamCloseButton = By.cssSelector("body > div.MuiDialog-root.form_overlay__team_form > div.MuiDialog-container.MuiDialog-scrollPaper > div > header > div > button > span.MuiIconButton-label > svg");
    private static final By searchBox = By.xpath("//*[@id='root']/div/main/div[2]/div/div[2]/div/div[1]/div/div/div/input");
    private static final By searchButton = By.xpath("//*[@id='root']/div/main/div[2]/div/div[2]/div/div[1]/div/div/div/div/button") ;
    private static final By allRows = By.cssSelector(".MuiTableRow-root.table-row.MuiTableRow-hover");
    private static final By nameFilter = By.xpath("//*[@id='root']/div/main/div[2]/div/div[2]/div/div[1]/table/thead/tr/th[1]/div/span");
    private static final By deleteButton = By.cssSelector(".MuiTableContainer-root.table-container > table > tbody > tr:nth-child(4) > td:nth-child(4) > div > button");
    private static final By okButton  = By.className("MuiButton-label");
    private static final By editButton  = By.cssSelector(".MuiTableContainer-root.table-container > table > tbody > tr:nth-child(5) > td:nth-child(4) > div > div > button");
    private static final By submitButton = By.className("MuiButtonBase-root MuiButton-root jss29 MuiButton-text");

    public static String addTeam(WebDriver driver) {
        Base.selectSideBarPage(driver, "Teams");
        String newTeamName = "Test";
        driver.findElement(addTeamsButton).click();
        ElementHelpers.waitForElementToBeVisible(driver, addTeamTitle);
        driver.findElement(teamName).sendKeys(newTeamName);
//        driver.findElement(save).click();
        driver.findElement(addTeamCloseButton).click();
        return newTeamName;
    }

    public static String searchTeam(WebDriver driver) {
        String teamName = "Test";
        Base.searchInPage(driver, teamName);
        return teamName;
    }

    public static List<String> validateNameFilter(WebDriver driver){
        List<String> teamNames = new ArrayList<>();
        driver.findElement(nameFilter).click();
        List<WebElement> rowsFilteredByName =  driver.findElements(allRows);
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

    public static String deleteTeam(WebDriver driver) {
        String expectedTeamName = "Test";
        List<WebElement> allTeams = driver.findElements(allRows);
        for (WebElement items : allTeams) {
            if ((items.getText().split(" ")[0]).equals(expectedTeamName)) {
                driver.findElement(deleteButton).click();
                ElementHelpers.waitForElementToBeVisible(driver, okButton);
//                driver.findElement(okButton).click();
            }
        }
        return expectedTeamName;
    }

    public static String editTeamName(WebDriver driver) {
        String expectedTeamName = "Test2";
        List<WebElement> allTeams = driver.findElements(allRows);
        for (WebElement items : allTeams) {
            if ((items.getText().split(" ")[0]).equals("Test")) {
                driver.findElement(editButton).click();
                driver.findElement(teamName).sendKeys(expectedTeamName);
//                driver.findElement(submitButton).click();
            }
        }
        return expectedTeamName;
    }
}
