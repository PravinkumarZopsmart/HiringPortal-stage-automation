package com.pages;
import com.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Interviews {
    private static final By interviews = By.cssSelector("#root > div > div > div > div.side-bar-with-button > div > div:nth-child(5) > a > div > div.side-bar-text");
    private static final By todayButton = By.cssSelector("#root > div > main > div.MuiBox-root.jss22.jss20.jss21 > div > div > div.MuiToolbar-root.MuiToolbar-regular.jss29.MuiToolbar-gutters > button > span.MuiButton-label");
    private static final By tableBody = By.cssSelector("#root > div > main > div.MuiBox-root.jss22.jss20.jss21 > div > div > div.jss58 > div > div:nth-child(2) > div > div.jss68 > table");

    public static void todayInterviews(WebDriver driver){
        driver.findElement(interviews).click();
        ElementHelpers.waitForElementToBeVisible(driver, todayButton);
        List<WebElement> interviewList = driver.findElements(By.className("jss391"));
        System.out.println(interviewList);
        for (WebElement w : interviewList) {
            System.out.println(w.getText());
        }
    }


}
