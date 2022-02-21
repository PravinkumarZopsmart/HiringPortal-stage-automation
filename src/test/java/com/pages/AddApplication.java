package com.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddApplication {
    private static final By addApplicationButton = By.cssSelector("#root > div > main > div.MuiBox-root.jss41.jss39.jss40 > div > div.dashboard_tasks > div:nth-child(2) > div > div > div > div > button > span.MuiButton-label");
    private static final By addApplicationTitle = By.cssSelector("body > div.MuiDialog-root.application_form_over_lay > div.MuiDialog-container.MuiDialog-scrollPaper > div > header > div > p");
    private static final By position = By.cssSelector("#interviewer");
    private static final By positionItem = By.cssSelector("#menu-position > div.MuiPaper-root.MuiMenu-paper.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded > ul > li:nth-child(4)");
    private static final By agency = By.cssSelector("#source");
    private static final By agencyItem = By.cssSelector("#menu-source > div.MuiPaper-root.MuiMenu-paper.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded > ul > li:nth-child(2)");
    private static final By name = By.cssSelector("#name");
    private static final By email = By.cssSelector("#email");
    private static final By phone = By.cssSelector("body > div.MuiDialog-root.application_form_over_lay > div.MuiDialog-container.MuiDialog-scrollPaper > div > div > div > div > form > div.applicant-info > div.jss101 > div > div:nth-child(5) > div > div > input");
    private static final By noticePeriod = By.id("notice-period");
    private static final By currentSalary = By.cssSelector("body > div.MuiDialog-root.application_form_over_lay > div.MuiDialog-container.MuiDialog-scrollPaper > div > div > div > div > form > div.applicant-info > div:nth-child(2) > div > div > div:nth-child(2) > div > div > input");
    private static final By expectedSalary = By.cssSelector("body > div.MuiDialog-root.application_form_over_lay > div.MuiDialog-container.MuiDialog-scrollPaper > div > div > div > div > form > div.applicant-info > div:nth-child(2) > div > div > div:nth-child(3) > div > div > input");
    private static final By currentLocation = By.id("current-location");
    private static final By expandButton = By.cssSelector("#link-add-button");
    private static final By skillsButton = By.cssSelector("#skill-add-button");
    private static final By addSkill = By.id("skill-0");
    private static final By additionalInformation = By.cssSelector("#rdw-wrapper-additional-info > div.editor-class.rdw-editor-main > div");
    private static final By save = By.cssSelector("#submit-button > span");
    private static final By close  = By.cssSelector("body > div.MuiDialog-root.application_form_over_lay > div.MuiDialog-container.MuiDialog-scrollPaper > div > header > div > button > span.MuiIconButton-label > svg");
    private static final By applicantsHeader = By.cssSelector("#root > div > main > div.MuiBox-root.jss89.jss69.jss88 > div > div.dashboard_tasks > div:nth-child(2) > span");
    private static final By totalApplication = By.cssSelector("#root > div > main > div.MuiBox-root.jss89.jss69.jss88 > div > div.dashboard_tasks > div:nth-child(2) > div > div > a > span");

    public static String addApplication(WebDriver driver) throws InterruptedException {
        driver.findElement(addApplicationButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(addApplicationTitle));
        driver.findElement(position).click();
        driver.findElement(positionItem).click();
        driver.findElement(agency).click();
        driver.findElement(agencyItem).click();
        driver.findElement(name).sendKeys("Ashok Kumar");
        driver.findElement(email).sendKeys("ashok@gmail.com");
        driver.findElement(phone).sendKeys("8823456765");
        driver.findElement(noticePeriod).sendKeys("3 Months");
        driver.findElement(currentSalary).sendKeys("4");
        driver.findElement(expectedSalary).sendKeys("12");
        driver.findElement(currentLocation).sendKeys("Hyderabad");
        driver.findElement(expandButton).click();
        driver.findElement(skillsButton).click();
        driver.findElement(addSkill).sendKeys("Java");
        driver.findElement(additionalInformation).sendKeys("sample Additional Information");
//        driver.findElement(save).click();
        driver.findElement(close).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(applicantsHeader));
        String totalApplicants = driver.findElement(totalApplication).getText();
        return totalApplicants;
    }
}

