package com.pages;

import com.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application extends Base {
    private static final int nameIndex = 0;
    private static final int emailIndex = 1;
    private static final int phoneIndex = 2;
    private static final int positionIndex = 3;
    private static final int statusIndex = 4;
    private static final int createdAtIndex = 5;
    private static final int resumeIndex = 6;
    private static final By pageHeadingLocator = By.cssSelector(".head-text-applications-list");
    private static final By applicantName = By.cssSelector(".interview_header h3");
    private static final By applicantStatus = By.cssSelector(".interview_header h5");
    private static final By applicationDetailsButtons = By.cssSelector(".interview_header_content " +
            ".MuiButtonBase-root.MuiButton-root.MuiButton-text");
    private static final By viewInformationDetails = By.cssSelector(".same-row-view-info .single-view-view-information");
    private static final By closeButton = By.cssSelector(".MuiDialog-container.MuiDialog-scrollPaper > div > header > div > button");
    private static final By closeApplicationButton = By.xpath("//button[@data-testid='testDrop']");
    private static final By textEditor = By.cssSelector(".ql-editor.ql-blank");

    public static String getPageHeading(WebDriver driver) {
        ElementHelpers.waitForElementToBeVisible(driver, pageHeadingLocator);
        return driver.findElement(pageHeadingLocator).getText();
    }

    public static Map<String, String> getApplicantDetailsByStatus(WebDriver driver, String status) {
        do {
            List<WebElement> getAllRows = driver.findElements(allRows);
            for (WebElement getAllRow : getAllRows) {
                if (getAllRow.findElement(By.cssSelector("td:nth-child(5)")).getText().equalsIgnoreCase(status)) {
                    return getApplicantDetailsByStatus(getAllRow);
                }
            }
        } while (moveToNextPage(driver));
        return null;
    }

    public static Map<String, String> getApplicantDetailsByStatus(WebElement row) {
        Map<String, String> applicantDetails = new HashMap<>();
        try {
            List<WebElement> columns = row.findElements(By.cssSelector("td"));
            String name = columns.get(nameIndex).getText();
            String email = columns.get(emailIndex).getText();
            String phone = columns.get(phoneIndex).getText();
            String position = columns.get(positionIndex).getText();
            String status = columns.get(statusIndex).getText();
            String createdAt = columns.get(createdAtIndex).getText();
            applicantDetails.put("name", name);
            applicantDetails.put("email", email);
            applicantDetails.put("phone", phone);
            applicantDetails.put("position", position);
            applicantDetails.put("status", status);
            applicantDetails.put("createdAt", createdAt);
            return applicantDetails;
        } catch (Exception e) {
            return applicantDetails;
        }
    }

    public static boolean enterApplicantDetails(WebDriver driver, String status) {
        try {
            do {
                List<WebElement> getAllRows = driver.findElements(allRows);
                for (WebElement getAllRow : getAllRows) {
                    if (getAllRow.findElement(By.cssSelector("td:nth-child(5)")).getText().equalsIgnoreCase(status)) {
                        driver.get(getAllRow.findElement(By.cssSelector("td a")).getAttribute("href"));
                        return true;
                    }
                }
            } while (moveToNextPage(driver));
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
            WebDriverUtil.takeScreenShot(driver, "enterApplicantDetails");
            return false;
        }
        return false;
    }

    public static Map<String, String> getApplicantNameAndStatus(WebDriver driver) {
        Map<String, String> applicantNameAndStatus = new HashMap<>();
        try {
            ElementHelpers.waitForElementToBeVisible(driver, applicantName);
            applicantNameAndStatus.put("name", driver.findElement(applicantName).getText());
            applicantNameAndStatus.put("status", driver.findElement(applicantStatus).getText());
            return applicantNameAndStatus;
        } catch (Exception e) {
            System.out.println(e.getClass());
            WebDriverUtil.takeScreenShot(driver, "nameAndStatus");
        }
        return applicantNameAndStatus;
    }

    public static boolean isApplicationDetailsButton(WebDriver driver, String buttonName) {
        try {
            List<WebElement> buttons = driver.findElements(applicationDetailsButtons);
            for (WebElement button : buttons) {
                if (button.getText().equalsIgnoreCase(buttonName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            WebDriverUtil.takeScreenShot(driver, "isButtonExists");
            System.out.println(e.getClass());
            return false;
        }
    }

    public static Map<String, String> getEmailAndPhone(WebDriver driver) {
        Map<String, String> emailAndPhone = new HashMap<>();
        try {
            List<WebElement> buttons = driver.findElements(applicationDetailsButtons);
            for (WebElement button : buttons) {
                if (button.getText().equalsIgnoreCase("View information")) {
                    button.click();
                    break;
                }
            }
            ElementHelpers.waitForElementToBeVisible(driver, viewInformationDetails);
            List<WebElement> info = driver.findElements(viewInformationDetails);
            emailAndPhone.put("email", info.get(0).getText().split(" ")[1]);
            emailAndPhone.put("phone", info.get(1).getText().split(" ")[1]);
            driver.findElement(closeButton).click();
        } catch (Exception e) {
            WebDriverUtil.takeScreenShot(driver, "getEmailAndPhone");
            System.out.println(e.getClass());
        }
        return emailAndPhone;
    }

    public static void closeApplication(WebDriver driver, String name) {
        try {
            WebElement applicant = getRowByName(driver, name);
            assert applicant != null;
            applicant.findElement(By.cssSelector("td a")).click();
            ElementHelpers.waitForElementToBeVisible(driver, textEditor);
            driver.findElement(textEditor).sendKeys("Test");
            driver.findElement(closeApplicationButton).click();
            driver.findElement(confirmButton).click();
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
        }
    }

    public static String closeApplication(WebDriver driver) {
        String name = null;
        try {
            ElementHelpers.waitForElementToBeVisible(driver, allRows);
            List<WebElement> getAllRows = driver.findElements(allRows);
            WebElement applicant = getAllRows.get(1);
            Thread.sleep(5000);
            name = applicant.findElement(By.tagName("td")).getText();
            applicant.findElement(By.cssSelector("td a")).click();
            Thread.sleep(5000);
            ElementHelpers.waitForElementToBeVisible(driver, textEditor);
            driver.findElement(textEditor).sendKeys("Test");
            driver.findElement(closeApplicationButton).click();
            driver.findElement(confirmButton).click();
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
        }
        return name;
    }

    public static void reOpenApplication(WebDriver driver, String name) {
        try {
            Base.selectFilter(driver, "status", "closed");
            WebElement applicant = getRowByName(driver, name);
            assert applicant != null;
            applicant.findElement(By.cssSelector("td a")).click();
            Thread.sleep(4000);
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
            e.printStackTrace();
        }
    }
}