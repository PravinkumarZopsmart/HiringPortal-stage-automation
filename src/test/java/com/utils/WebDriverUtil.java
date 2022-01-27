package com.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class WebDriverUtil {
    public static WebDriver startWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }

    public static void moveToNextTab(WebDriver driver) {
        try {
            String currentWindow = driver.getWindowHandle();
            Set<String> windows = driver.getWindowHandles();
            List<String> windowsList = new ArrayList<>(windows);
            for (int i = 0; i < windowsList.size(); i++) {
                if (Objects.equals(windowsList.get(i), currentWindow)) {
                    driver.switchTo().window(windowsList.get(i + 1));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }

    public static void moveToFirstTab(WebDriver driver) {
        moveToTab(driver, 1);
    }

    public static void moveToTab(WebDriver driver, int number) {
        try {
            Set<String> windows = driver.getWindowHandles();
            List<String> windowsList = new ArrayList<>(windows);
            driver.switchTo().window(windowsList.get(number - 1));
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }
}
