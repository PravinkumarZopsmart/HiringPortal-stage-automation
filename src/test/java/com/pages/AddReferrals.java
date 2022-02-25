package com.pages;

import com.utils.ElementHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AddReferrals {
    private static final By addReferralButtton = By.cssSelector(".open-position-card:nth-child(3) button");
    private static final By addReferralTitle = By.cssSelector("body > div.MuiDialog-root.application_form_over_lay > div.MuiDialog-container.MuiDialog-scrollPaper > div > header > div > p");
    private static final By position = By.id("interviewer");
    private static final By positionItem = By.cssSelector("#menu-position > div.MuiPaper-root.MuiMenu-paper.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded > ul > li:nth-child(4)");
    private static final By name = By.id("name");
    private static final By email = By.id("email");
    private static final By phone = By.name("phone-number");
    private static final By noticePeriod = By.id("notice-period");
    private static final By currentSalary = By.name("current-salary");
    private static final By expectedSalary = By.name("expected-salary");
    private static final By currentLocation = By.id("current-location");
    private static final By addInfo = By.cssSelector("#link-add-button");
    private static final By skills = By.id("skill-add-button");
    private static final By enterSkillOne = By.id("skill-0");
    private static final By enterSkillOneLevel = By.id("skill-level-0");
    private static final By skillAddButton = By.id("skill-add-button");
    private static final By addEducation = By.id("education-add-button");
    private static final By collegeName = By.id("free-solo-2-demo-0");
    private static final By educationLevelOne = By.id("education-level-0");
    private static final By educationDegreeOne = By.id("education-level-0");
    private static final By specilization = By.id("education-specialization-0");
    private static final By year = By.name("education-year");
    private static final By addEducationButton = By.id("education-add-button");
    private static final By additionalInformation = By.cssSelector("#rdw-wrapper-additional-info > div.editor-class.rdw-editor-main > div > div.DraftEditor-editorContainer > div > div > div > div");
    private static final By save = By.cssSelector("#submit-button > span");
    private static final By close = By.cssSelector("body > div.MuiDialog-root.application_form_over_lay > div.MuiDialog-container.MuiDialog-scrollPaper > div > header > div > button > span.MuiIconButton-label > svg");
    private static final By totalReferrals = By.cssSelector("#root > div > main > div.MuiBox-root.jss45.jss43.jss44 > div > div.dashboard_tasks > div:nth-child(3) > div > div > a > span");

    public static String addReferralss(WebDriver driver) throws InterruptedException {
        driver.findElement(addReferralButtton).click();
        ElementHelpers.waitForElementToBeVisible(driver, addReferralTitle);
        driver.findElement(position).click();
        driver.findElement(positionItem).click();
        driver.findElement(name).sendKeys("Roushan Kumar");
        driver.findElement(email).sendKeys("roushankumar@gmail.com");
        driver.findElement(phone).sendKeys("8899456787");
        driver.findElement(noticePeriod).sendKeys("1 month");
        driver.findElement(currentSalary).sendKeys("6");
        driver.findElement(expectedSalary).sendKeys("10");
        driver.findElement(currentLocation).sendKeys("Noida");
        driver.findElement(addInfo).click();
        Thread.sleep(2000);
        driver.findElement(skills).click();
        driver.findElement(enterSkillOne).sendKeys("Golang");
        Select selectSkill = new Select(driver.findElement(enterSkillOneLevel));
        selectSkill.selectByIndex(2);
        driver.findElement(skillAddButton).click();
        Thread.sleep(2000);
        driver.findElement(addEducation).click();
        Thread.sleep(2000);
        driver.findElement(collegeName).sendKeys("Technocrates");
        Select educationLevel = new Select(driver.findElement(educationLevelOne));
        educationLevel.selectByIndex(1);
        Select degree = new Select(driver.findElement(educationDegreeOne));
        degree.selectByIndex(1);
        driver.findElement(specilization).sendKeys("Computer Science Engineering");
        driver.findElement(year).sendKeys("2019");
        driver.findElement(addEducationButton).click();
        driver.findElement(additionalInformation).sendKeys("Sample Additional Information");
//        driver.findElement(save).click();
        driver.findElement(close).click();
        return driver.findElement(totalReferrals).getText();
    }
}
