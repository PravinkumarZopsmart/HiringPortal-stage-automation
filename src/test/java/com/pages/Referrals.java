package com.pages;

<<<<<<< Updated upstream

=======
import com.utils.*;
>>>>>>> Stashed changes
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Referrals extends Base{
    private static final By addReferralButton = By.cssSelector(".add-referral-button button");
    private static final By referralPage = By.cssSelector(".complete-form-applicant");
    private static final By positionSpan = By.id("interviewer");
    private static final By positionList = By.cssSelector("#menu-position .MuiList-root.MuiMenu-list.MuiList-padding li");
    private static final By pageHeader = By.cssSelector(".head-text-referrals-list");
    private static final By formName = By.id("name");
    private static final By submitButton = By.id("submit-button");
    private static final By closeButton = By.cssSelector(".MuiDialog-container.MuiDialog-scrollPaper header button");

    public static void addReferrals(WebDriver driver) {
        driver.findElement(addReferralButton).click();
        ElementHelpers.waitForElementToBeVisible(driver,referralPage);
    }

    public static String getPageHeader(WebDriver driver) {
        try {
            return driver.findElement(pageHeader).getText();
        } catch (Exception e) {
            WebDriverUtil.takeScreenShot(driver,"gePageHeading");
            return null;
        }
    }

    private static void selectAnyPosition(WebDriver driver) {
        try {
            driver.findElement(positionSpan).click();
            ElementHelpers.waitForElementToBeVisible(driver,By.cssSelector("#menu-position .MuiList-root.MuiMenu-list.MuiList-padding li:nth-child(3)"));
            ElementHelpers.waitForElementToBeVisible(driver,positionList);
            List<WebElement> positions = driver.findElements(positionList);
            System.out.println(positions.size());
            int randomIndex =(int)Math.floor(Math.random()*(positions.size()-1+1)+1);
            positions.get(randomIndex).click();
        } catch (Exception e) {
            System.out.println(e.getClass());
            WebDriverUtil.takeScreenShot(driver,"selectPositionInReferrals");
        }
    }

    public static boolean isSubmitButton(WebDriver driver) {
        ElementHelpers.waitForElementToBeVisible(driver,submitButton);
        return driver.findElement(submitButton).isEnabled();
    }

    public static void closeAddReferralPage(WebDriver driver) {
        try{
            ElementHelpers.waitForElementToBeVisible(driver,closeButton);
            driver.findElement(closeButton).click();
        } catch (Exception e) {
            System.out.println(e.getClass());
            WebDriverUtil.takeScreenShot(driver,"closeAddReferral");
        }
    }
}
