package com.pages;

import com.utils.ElementHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class Skills {
    private static final By addSkillsButton = By.xpath("//*[@id='root']/div/main/div[2]/div/div[1]/div/div/button");
    private static final By skillName = By.name("name");
    private static final By submit = By.cssSelector("body > div.MuiDialog-root.form_overlay__team_form > div.MuiDialog-container.MuiDialog-scrollPaper > div > div > div > div > form > div > div:nth-child(2) > div > button > span.MuiButton-label");
    private static final By close = By.cssSelector("body > div.MuiDialog-root.form_overlay__team_form > div.MuiDialog-container.MuiDialog-scrollPaper > div > header > div > button > span.MuiIconButton-label > svg");
    private static final By searchBar = By.cssSelector(".MuiTableContainer-root.table-container > div > div > div > input");
//    private static final By searchButton = By.cssSelector("#root > div > main > div.MuiBox-root.jss147.jss83.jss146 > div > div.jss142 > div > div.MuiTableContainer-root.table-container > div > div > div > div > button > span.MuiIconButton-label > svg");
    private static final By allRows = By.cssSelector(".MuiTableRow-root.table-row.MuiTableRow-hover");
    private static final By nameFilter = By.xpath("//*[@id='root']/div/main/div[2]/div/div[2]/div/div[1]/table/thead/tr/th[1]/div/span");
    private static final By editButton  = By.cssSelector(".MuiTableContainer-root.table-container > table > tbody > tr:nth-child(5) > td:nth-child(4) > div > div > button");
//    private static final By submitButton = By.className("MuiButtonBase-root MuiButton-root jss29 MuiButton-text");
    private static final By deleteButton = By.cssSelector(".MuiTableContainer-root.table-container > table > tbody > tr:nth-child(4) > td:nth-child(4) > div > button");
    private static final By okButton  = By.cssSelector(".MuiDialogActions-root.MuiDialogActions-spacing > button:nth-child(1)");

    public static void validateAddSkills(WebDriver driver) {
        Base.selectSideBarPage(driver, "Skills");
        ElementHelpers.waitForElementToBeVisible(driver, addSkillsButton);
        driver.findElement(addSkillsButton).click();
        driver.findElement(skillName).sendKeys("BackEnd");
        driver.findElement(close).click();
    }

    public static String validateSearchBar(WebDriver driver) {
        String skillName = "Android";
        Base.searchInPage(driver, skillName);
        return skillName;
    }

    public static List<String> validateNameFilter(WebDriver driver) throws InterruptedException {
        List<String> skillNames = new ArrayList<>();
        driver.findElement(searchBar).sendKeys(Keys.COMMAND, "a");
        driver.findElement(searchBar).sendKeys(Keys.DELETE);
        driver.findElement(nameFilter).click();
        List<WebElement> allSkills = driver.findElements(allRows);
        for (WebElement items : allSkills) {
            skillNames.add(items.getText().split(" ")[0]);
        }
        return skillNames;
    }

    public static List<String> validateCreatedAtFilter(WebDriver driver) {
        List<String> expectedTeamNames = new ArrayList<>();
        Base.selectFilter(driver, "Created At", "03-Dec-2021");
        List<WebElement> allSkills = driver.findElements(allRows);
        for (WebElement items : allSkills) {
            expectedTeamNames.add(items.getText().split(" ")[0]);
        }
        return expectedTeamNames;
    }

    public static List<String> validateInactiveStatusFilter(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        Base.clearFilterField(driver, "Created At");
        List<String> expectedSkillNames = new ArrayList<>();
        Base.selectFilter(driver, "Status", "INACTIVE");
        List<WebElement> allTeams = driver.findElements(allRows);
        for (WebElement items : allTeams) {
            expectedSkillNames.add(items.getText().split(" ")[0]);
        }
        return expectedSkillNames;
    }

    public static List<String> validateActiveStatusFilter(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        Base.clearFilterField(driver, "Created At");
        List<String> expectedSkillNames = new ArrayList<>();
        Base.selectFilter(driver, "Status", "ACTIVE");
        List<WebElement> allTeams = driver.findElements(allRows);
        for (WebElement items : allTeams) {
            expectedSkillNames.add(items.getText().split(" ")[0]);
        }
        return expectedSkillNames;
    }

    public static String editTeamName(WebDriver driver) {
        String expectedSkillName = "Node JS";
        List<WebElement> allTeams = driver.findElements(allRows);
        for (WebElement items : allTeams) {
            if ((items.getText().split(" ")[0]).equals("Node")) {
                driver.findElement(editButton).click();
                driver.findElement(skillName).sendKeys(expectedSkillName);
//                driver.findElement(submitButton).click();
            }
        }
        return expectedSkillName;
    }

    public static String deleteTeam(WebDriver driver) {
        String expectedSkillName = "Test";
        List<WebElement> allTeams = driver.findElements(allRows);
        for (WebElement items : allTeams) {
            if ((items.getText().split(" ")[0]).equals(expectedSkillName)) {
                driver.findElement(deleteButton).click();
                ElementHelpers.waitForElementToBeVisible(driver, okButton);
//                driver.findElement(okButton).click();
            }
        }
        return expectedSkillName;
    }
}
