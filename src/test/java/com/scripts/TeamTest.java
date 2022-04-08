package com.scripts;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pages.Base;
import com.pages.LoginPage;
import com.pages.Streams;
import com.pages.Teams;
import com.utils.ElementHelpers;
import com.utils.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TeamTest {
    private static WebDriver driver;
    private static final String URL = "https://stage.hiringmotion.com/";
    private static final String TEAMS = "TEAMS";

    @BeforeClass
    public void loginToHiringPortal() {
        Base.deleteScreenshots();
        driver = WebDriverUtil.startWebDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
    }

    @BeforeMethod
    public void selectTeamsSection() {
        Base.selectSideBarPage(driver,TEAMS);
    }

    @Test
    public void testAddTeams(){
        String addedTeamName = Teams.addTeam(driver);
        System.out.println("Added team Name : "+ addedTeamName);
        Base.selectSideBarPage(driver,TEAMS); //to update the team that is added
        Base.searchInPage(driver,addedTeamName);
        List<WebElement> allRows = Base.getAllRows(driver);
        assert allRows != null;
        boolean checkAddedTeam = false;
        for(WebElement row: allRows) {
            Map<String, Object> rowDetails = Teams.getTeamsDetails(row);
            assert rowDetails != null;
            String name = (String) rowDetails.get("name");
            if(name.equals(addedTeamName)){
                checkAddedTeam = true;
                break;
            }
        }
        assertTrue(checkAddedTeam);
    }

    @Test
    public void searchTeamTest(){
        String toSearch = "Test";
        Base.searchInPage(driver,"Test");
        List<WebElement> allRows = Base.getAllRows(driver);
        assert allRows != null;
        for (WebElement row : allRows) {
            Map<String, Object> rowDetails = Teams.getTeamsDetails(row);
            assert rowDetails != null;
            String name = (String) rowDetails.get("name");
            System.out.println(name);
            System.out.println(toSearch);
            assertTrue(name.contains(toSearch));
        }
    }

    @AfterClass
    public void endWebDriverSession() {
        driver.close();
        driver.quit();
    }
}
