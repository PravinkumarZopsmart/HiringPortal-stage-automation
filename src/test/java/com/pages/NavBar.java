package com.pages;

import com.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class NavBar {
    private static final By applicatonSearchBar = By.cssSelector("#root > div > header > div > div > div > div.search > div > div > div > input");
    private static final By searchButton = By.cssSelector("#root > div > header > div > div > div > div.search > div > div > div > div > button > span.MuiIconButton-label > svg");
    private static final By bellNotification = By.cssSelector("#root > div > header > div > div > div > div:nth-child(3) > button");
    private static final By notifications = By.className("MuiAlert-message");
    private static final By accountButton = By.cssSelector("#root > div > header > div > div > div > div:nth-child(4) > button");
    private static final By logoutbutton = By.cssSelector("#customized-menu > div.MuiPaper-root.MuiMenu-paper.jss24.MuiPopover-paper.MuiPaper-elevation0.MuiPaper-rounded > ul > li > button");
    private static final By loginPageText = By.cssSelector(".form-box > button > span.MuiButton-label > p");

    public static List<String> searchApplication(WebDriver driver, String value) {
        try {
            driver.findElement(applicatonSearchBar).sendKeys(Keys.COMMAND, "a");
            driver.findElement(applicatonSearchBar).sendKeys(Keys.DELETE);
            driver.findElement(applicatonSearchBar).sendKeys(value);
            driver.findElement(searchButton).click();
            Thread.sleep(3000);
            List<WebElement> tableData = driver.findElements(By.cssSelector("td"));
            List<String> applicantsData = new ArrayList<>();
            for(WebElement item : tableData){
                applicantsData.add(item.getText());
            }
            return applicantsData;
        }
        catch (Exception e) {
            e.printStackTrace();
            WebDriverUtil.takeScreenShot(driver, "Searched Applications");
        }
        return null;
    }

    public static void validateBellNotification(WebDriver driver) {
        try{
            driver.findElement(bellNotification).click();
            String pendingTask = driver.findElement(notifications).getText();
            System.out.println(pendingTask);
        }
        catch (Exception e) {
            e.printStackTrace();
            WebDriverUtil.takeScreenShot(driver, "Notifications");
        }
    }

    public static String validateLogout(WebDriver driver) {
        try{
            driver.findElement(accountButton).click();
            driver.findElement(logoutbutton).click();
            String signInText = driver.findElement(loginPageText).getText();
            return signInText;
        }
        catch (Exception e) {
            e.printStackTrace();
            WebDriverUtil.takeScreenShot(driver, "Logout");

        }
        return null;
    }
}
