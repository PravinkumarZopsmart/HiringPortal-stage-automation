package com.scripts;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pages.Base;
import com.pages.LoginPage;
import com.pages.Teams;
import com.utils.ElementHelpers;
import com.utils.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TeamTest {
    private static WebDriver driver;
    private static final String URL = "https://stage.hiringmotion.com/";

    @BeforeSuite
    public void setUpDriver() {
        Base.deleteScreenshots();
        driver = WebDriverUtil.startWebDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @BeforeClass
    public void loginToHiringPortal() {
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
    }
    @Test(priority = 1)
    public void testAddTeams(){
        String newTeamName = Base.getRowByName(driver, "Test").getText();
        Assert.assertEquals(Teams.addTeam(driver), newTeamName);
    }

    @Test(priority = 2)
    public void searchTeamTest(){
        String expectedResult = Teams.searchTeam(driver);
        String actualResult = Base.getRowByName(driver, "Test").getText().split(" ")[0];
    }

    @Test
    public void testNameFilter() {
        List<String> actualResult = new ArrayList<>();
        List<WebElement> allTeams = Base.getAllRows(driver);
        for (WebElement items : allTeams) {
            actualResult.add(items.getText().split(" ")[0]);
        }
        Assert.assertEquals(actualResult, Teams.validateNameFilter(driver));
    }

    @Test
    public void testCreatedAtFilter() {
        String actualResult = Base.getRowByName(driver, "Krogo").getText().split(" ")[0];
        Assert.assertEquals(actualResult, Teams.validateCreatedAtFilter(driver));
    }

    @Test
    public void testInactiveStatus() {
         List<WebElement> allRows = Base.getAllRows(driver);
         List<String> actualResult = new ArrayList<>();
         for (WebElement items : allRows) {
             actualResult.add(items.getText().split(" ")[0]);
         }
         Assert.assertEquals(Teams.validateInactiveStatusFilter(driver), allRows);
    }

    @Test
    public void testActiveStatus() {
        List<WebElement> allRows = Base.getAllRows(driver);
        List<String> actualResult = new ArrayList<>();
        for (WebElement items : allRows) {
            actualResult.add(items.getText().split(" ")[0]);
        }
        Assert.assertEquals(Teams.validateActiveStatusFilter(driver), allRows);
    }

    @Test
    public void testDeleteTestButton()  {
        String actualResult = null;
        String expectedResult = Teams.deleteTeam(driver);
        List<WebElement> allRows = Base.getAllRows(driver);
        for (WebElement items : allRows) {
            if (items.getText().split(" ")[0] == expectedResult) {
                actualResult = expectedResult;
            }
        }
        Assert.assertNull(actualResult);
        }

    @Test
    public void testEditTeam() {
        String actualResult = Base.getRowByName(driver, "Test2").getText().split(" ")[0];
        Assert.assertEquals(actualResult, Teams.editTeamName(driver));
    }
    @AfterSuite
    public void endWebDriverSession() {
        driver.close();
        driver.quit();
    }
}
