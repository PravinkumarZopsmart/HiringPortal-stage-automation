package com.scripts;
import com.pages.Interviews;
import com.utils.*;
import com.pages.AddPosition;
import com.pages.Dashboard;
import com.pages.LoginPage;
import com.utils.ElementHelpers;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class InterviewTest {
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

    @Test
    public void todayInterviewTest(){
        Interviews.todayInterviews(driver);
    }

    @AfterSuite
    public void endDriverSession() {
        driver.close();
        driver.quit();
    }

}
