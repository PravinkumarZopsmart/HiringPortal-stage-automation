package com.pages;

import com.utils.ElementHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddPosition {
    private static final By addPositionButton = By.cssSelector("#root > div > main > div.MuiBox-root.jss41.jss39.jss40 > div > div.dashboard_tasks > div:nth-child(1) > div > div > div > div > button");
    private static final By streamButton = By.cssSelector("#position-add-stream");
    private static final By streamListButton = By.cssSelector("#menu- > div.MuiPaper-root.MuiMenu-paper.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded > ul > li:nth-child(2)");
    private static final By levelButton  = By.cssSelector("#position-add-level");
    private static final By levelListButton  = By.cssSelector("#menu- > div.MuiPaper-root.MuiMenu-paper.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded > ul > li:nth-child(2)");
    private static final By skillsButton = By.id("position-add-skill");
    private static final By skillsListItem = By.cssSelector("#menu- > div.MuiPaper-root.MuiMenu-paper.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded > ul > li:nth-child(2)");
    //private static final By skillsListItem = By.cssSelector("#menu-skill > div.MuiPaper-root.MuiMenu-paper.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded > ul > li:nth-child(3)");
    private static final By jobLocationDropdown = By.cssSelector("body > div.MuiDialog-root.form_overlay__position_form > div.MuiDialog-container.MuiDialog-scrollPaper > div > div > div > div > div > form > div.position-info > div > div > div:nth-child(5) > div > div > div > div > button.MuiButtonBase-root.MuiIconButton-root.MuiAutocomplete-popupIndicator > span.MuiIconButton-label > svg");
    private static final By getJobLocationItems = By.className("MuiAutocomplete-popper");
    private static final By getJobLocationItemsTagName = By.tagName("li");
    private static final By description = By.xpath("//div[@class='DraftEditor-root']//span");
    private static final By hiringManager = By.id("position-add-interviewers");
    private static final By hiringManagerListItem = By.cssSelector("#menu- > div.MuiPaper-root.MuiMenu-paper.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded > ul > li:nth-child(6)");
    private static final By humanResource = By.id("hr");
    private static final By humanResourceItem = By.cssSelector("body > div.MuiDialog-root.form_overlay__position_form > div.MuiDialog-container.MuiDialog-scrollPaper > div > div > div > div > div > form > div.position-info > div > div > div:nth-child(8) > div > div > div > div.MuiChip-root.MuiAutocomplete-tag.MuiChip-deletable > span");
    private static final By inviteTemplate = By.cssSelector("#invite-template");
    private static final By date = By.id("position-end-date");
    private static final By save = By.cssSelector("body > div.MuiDialog-root.form_overlay__position_form > div.MuiDialog-container.MuiDialog-scrollPaper > div > div > div > div > div > form > div.button-position > div > button");
    private static final By close = By.cssSelector("body > div.MuiDialog-root.form_overlay__position_form > div.MuiDialog-container.MuiDialog-scrollPaper > div > header > div > button > span.MuiIconButton-label > svg");
    private static final By noOfPositions = By.cssSelector("#root > div > main > div.MuiBox-root.jss147.jss136.jss146 > div > div.dashboard_tasks > div:nth-child(1) > div > div > a > span");

    public static String dashboardElements(WebDriver driver){
        String dashElements = driver.findElements(By.className("open-position-card")).toString();
        return dashElements;
    }

    public static String addPositions(WebDriver driver) throws InterruptedException {
        ElementHelpers.waitForElementToBeVisible(driver,addPositionButton);
        driver.findElement(addPositionButton).click();
        Thread.sleep(3000);
        driver.findElement(streamButton).click();
        driver.findElement(streamListButton).click();
        Thread.sleep(2000);
        driver.findElement(levelButton).click();
        driver.findElement(levelListButton).click();
        driver.findElement(skillsButton).click();
        driver.findElement(skillsListItem).click();

        driver.findElement(jobLocationDropdown).click();
        WebElement options = driver.findElement(getJobLocationItems);
        List<WebElement> cities = options.findElements(getJobLocationItemsTagName);
        for (WebElement w: cities){
            if (w.getText().equals("Bangalore")){
                w.click();
                break;
            }
        }
        driver.findElement(description).sendKeys("Looking for fullstack developer having skills set on Golang and Javascript");
        Thread.sleep(2000);
        driver.findElement(hiringManager).click();
        driver.findElement(hiringManagerListItem).click();
        driver.findElement(humanResource).click();
        WebElement humanResourceItems = driver.findElement(By.cssSelector("body > div.MuiAutocomplete-popper"));
        humanResourceItems.click();
        Thread.sleep(3000);
        driver.findElement(inviteTemplate).sendKeys("Please keep the camera onn while having the interview");
        driver.findElement(date).sendKeys("02/02/2022");
        //driver.findElement(save).click();
        Thread.sleep(2000);
        driver.findElement(close).click();
        Thread.sleep(2000);
        String numberOfPositions = driver.findElement(noOfPositions).getText();
        System.out.println(numberOfPositions);
        return numberOfPositions;
    }


}

