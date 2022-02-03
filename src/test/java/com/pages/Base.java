package com.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class Base {
    private static final By sideBarButtonLocator = By.cssSelector(".side-bar-buttons a");

    public static void selectSideBarPage(WebDriver driver, String button) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sideBarButtonLocator));
        List<WebElement> sideButtons = driver.findElements(sideBarButtonLocator);
        for (WebElement sideButton : sideButtons) {
            if (sideButton.getText().equalsIgnoreCase(button)) {
                sideButton.click();
                break;
            }
        }
    }
}
