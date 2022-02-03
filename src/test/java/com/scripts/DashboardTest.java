package com.scripts;

import com.pages.Dashboard;
import com.pages.LoginPage;
import com.utils.ElementHelpers;
import com.utils.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DashboardTest {
    private static WebDriver driver;
    private static final String URL = "https://stage.hiringmotion.com/";

    @BeforeSuite
    public void setUpDriver() {
        driver = WebDriverUtil.startWebDriver();
        driver.get(URL);
    }

    @BeforeClass
    public void loginToHiringPortal() {
        LoginPage.login(driver);
        ElementHelpers.waitForDOMToLoad(driver);
    }

    @Test(priority = 0)
    public void dashElementsTest(){
        System.out.println(Dashboard.dashboardElements(driver));

    }

    @Test(priority = 1)
    public void addPositionTest() throws InterruptedException {
        String actual = Dashboard.addPositions(driver);
        Assert.assertEquals(actual, 15);
    }
}
