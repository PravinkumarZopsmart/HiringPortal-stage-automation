package com.scripts;
import com.pages.Skills;
import com.utils.*;
import com.pages.Base;
import com.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class SkillsTest {
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
    public void testAddSkills() {
       Skills.validateAddSkills(driver);
       String actualSkillName = Base.getRowByName(driver, "BackEnd").getText().split(" ")[0];
       Assert.assertEquals(actualSkillName, "BackEnd");
    }

    @Test(priority = 2)
    public void testSearchBar() {
        Skills.validateSearchBar(driver);
        Assert.assertEquals("Android", Base.getRowByName(driver, "Android").getText().split(" ")[0]);
    }

    @Test(priority = 3)
    public void testNameFilter() throws InterruptedException {
        List<WebElement> allSkills = Base.getAllRows(driver);
        List<String> actualResult = new ArrayList<>();
        for (WebElement item : allSkills) {
            actualResult.add(item.getText().split(" ")[0]);
        }
        Assert.assertEquals(actualResult, Skills.validateNameFilter(driver));
    }

    @Test(priority = 4)
    public void testCreatedAtFilter() {
        List<String> actualResult = new ArrayList<>();
        List<WebElement> allSkills = Base.getAllRows(driver);
        for (WebElement item : allSkills) {
            actualResult.add(item.getText().split(" ")[0]);
        }
        Assert.assertEquals(actualResult, Skills.validateCreatedAtFilter(driver));

    }

    @Test(priority = 5)
    public void testInactiveStatus() throws InterruptedException {
        List<WebElement> allRows = Base.getAllRows(driver);
        List<String> actualResult = new ArrayList<>();
        for (WebElement items : allRows) {
            actualResult.add(items.getText().split(" ")[0]);
        }
        Assert.assertEquals(actualResult, Skills.validateInactiveStatusFilter(driver));
    }

    @Test(priority = 6)
    public void testActiveStatus() throws InterruptedException {
        List<WebElement> allRows = Base.getAllRows(driver);
        List<String> actualResult = new ArrayList<>();
        for (WebElement items : allRows) {
            actualResult.add(items.getText().split(" ")[0]);
        }
        Assert.assertEquals(actualResult, Skills.validateActiveStatusFilter(driver));
    }

    @Test(priority = 7)
    public void testEditTeam() {
        String actualResult = Base.getRowByName(driver, "Node JS").getText().split(" ")[0];
        Assert.assertEquals(actualResult, Skills.editTeamName(driver));
    }

    @Test(priority = 8)
    public void testDeleteTestButton()  {
        String actualResult = null;
        String expectedResult = Skills.deleteTeam(driver);
        List<WebElement> allRows = Base.getAllRows(driver);
        for (WebElement items : allRows) {
            if (items.getText().split(" ")[0] == expectedResult) {
                actualResult = expectedResult;
            }
        }
        Assert.assertNull(actualResult);
    }

    @AfterSuite
    public void endWebDriverSession() throws InterruptedException {
        driver.close();
        driver.quit();
    }
}
