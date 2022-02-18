package com.pages;

import com.utils.ElementHelpers;
import com.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

public class AddPosition {
    private static final By addPositionButton = By.cssSelector("#root > div > main > div.MuiBox-root.jss41.jss39.jss40 > div > div.dashboard_tasks > div:nth-child(1) > div > div > div > div > button");
    private static final By streamButton = By.cssSelector("#position-add-stream");
    private static final By streamListButton = By.cssSelector("#menu-stream > div.MuiPaper-root.MuiMenu-paper.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded > ul > li:nth-child(4)");
    private static final By levelButton  = By.cssSelector("#level");
    private static final By levelListButton  = By.cssSelector("#menu-level > div.MuiPaper-root.MuiMenu-paper.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded > ul > li.MuiButtonBase-root.MuiListItem-root.MuiMenuItem-root.Mui-selected.MuiMenuItem-gutters.MuiListItem-gutters.MuiListItem-button.Mui-selected");
    private static final By skillsButton = By.cssSelector("#Skill");
    private static final By skillsList = By.cssSelector("#Skill");
    private static final By skillsListItem = By.cssSelector("#menu-skill > div.MuiPaper-root.MuiMenu-paper.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded > ul > li:nth-child(3)");
    private static final By jobLocationDropdown = By.cssSelector("body > div.MuiDialog-root.form_overlay__position_form > div.MuiDialog-container.MuiDialog-scrollPaper > div > div > div > div > div > form > div.position-info > div > div > div:nth-child(5) > div > div > div > div > button.MuiButtonBase-root.MuiIconButton-root.MuiAutocomplete-popupIndicator > span.MuiIconButton-label > svg");
    private static final By getJobLocationItems = By.className("MuiAutocomplete-popper");
    private static final By getJobLocationItemsTagName = By.tagName("li");
    private static final By description = By.xpath("//div[@class='DraftEditor-root']//span");
    private static final By interviewer = By.cssSelector("#position-add-interviewers");
    private static final By humanResource = By.cssSelector("#hr");
    private static final By inviteTemplate = By.cssSelector("#invite-template");
    private static final By date = By.id("position-end-date");
    private static final By save = By.cssSelector("body > div.MuiDialog-root.form_overlay__position_form > div.MuiDialog-container.MuiDialog-scrollPaper > div > div > div > div > div > form > div.button-position > div > button");
    private static final By close = By.cssSelector("body > div.MuiDialog-root.form_overlay__position_form > div.MuiDialog-container.MuiDialog-scrollPaper > div > header > div > button > span.MuiIconButton-label > svg");
    private static final By noOfPositions = By.xpath("#root > div > main > div.MuiBox-root.jss76.jss74.jss75 > div > div.dashboard_tasks > div:nth-child(1) > div > div > a > span");

    public static int addPositions(WebDriver driver) throws InterruptedException {
        ElementHelpers.waitForElementToBeVisible(driver, addPositionButton);
        driver.findElement(addPositionButton).click();
//        Thread.sleep(3000);
//        driver.findElement(streamButton).click();
//        driver.findElement(streamListButton).click();
//        Thread.sleep(2000);
//        driver.findElement(levelButton).click();
//        Thread.sleep(2000);
//        driver.findElement(levelListButton).click();
//        driver.findElement(skillsButton).click();
//        List<WebElement> skills = new ArrayList<>(driver.findElements(skillsList));
//        driver.findElement(skillsListItem).click();
//
//        driver.findElement(jobLocationDropdown).click();
//        WebElement options = driver.findElement(getJobLocationItems);
//        List<WebElement> cities = options.findElements(getJobLocationItemsTagName);
//        for (WebElement w: cities){
//            if (w.getText().equals("Bangalore")){
//                w.click();
//                break;
//            }
//        }
//        driver.findElement(description).sendKeys("Looking for fullstack developer having skills set on Golang and Javascript");
//        driver.findElement(interviewer).sendKeys("Lalit");
        driver.findElement(By.id("hr")).click();
//        driver.findElement(By.id("hr")).sendKeys("Lalit");
//        driver.findElement(By.cssSelector("body > div.MuiDialog-root.form_overlay__position_form > div.MuiDialog-container.MuiDialog-scrollPaper > div > div > div > div > div > form > div.position-info > div > div > div:nth-child(8) > div > div > div")).sendKeys("Lalit");
        Thread.sleep(3000);
        driver.findElement(inviteTemplate).sendKeys("Please keep the camera onn while having the interview");
//        driver.findElement(date).sendKeys("02/02/2022");
//        driver.findElement(save).click();
//        Thread.sleep(2000);
//        driver.findElement(close).click();
        String numberOfPositions = driver.findElement(noOfPositions).getText();
        return Integer.parseInt(numberOfPositions);
    }


}
