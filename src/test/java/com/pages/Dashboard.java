package com.pages;

import com.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Dashboard {
    private static final By numberOf = By.cssSelector("number-of-open-position-text");

    public static int getNumberOf(WebDriver driver,String category){
        try {
            List<WebElement> categories = driver.findElements(numberOf);
            if(category.equalsIgnoreCase("Positions")){
                return Integer.parseInt(categories.get(0).getText());
            } else if (category.equalsIgnoreCase("Applications")) {
                return Integer.parseInt(categories.get(1).getText());
            } else if (category.equalsIgnoreCase("Referrals")) {
                return Integer.parseInt(categories.get(1).getText());
            } else if (category.equalsIgnoreCase("Pending Resume Screening")) {
                return Integer.parseInt(categories.get(1).getText());
            } else if (category.equalsIgnoreCase("Interview Pending to be Scheduled")) {
                return Integer.parseInt(categories.get(1).getText());
            }
        } catch (Exception e) {
            WebDriverUtil.takeScreenShot(driver,"Dashboard");
            System.out.println(e.getClass());
            e.printStackTrace();
            return 0;
        }
        return 0;
    }
}
