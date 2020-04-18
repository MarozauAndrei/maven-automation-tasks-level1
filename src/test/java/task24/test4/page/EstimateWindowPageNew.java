package task24.test4.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class EstimateWindowPageNew {
    private final String RESULT_FIELD_LOCATOR = "//*[@id='compute']/md-list";
    private final String EMAIL_BUTTON_LOCATOR = "//button[contains(text(),'Email Estimate')]";
    private final String EMAIL_FIELD_LOCATOR = "//md-input-container/descendant::*[contains(text(),'Email')]/../ input";
    private final String EMAIL_SEND_LOCATOR = "//form[@name='emailForm']/descendant::button[contains(text(),'Send Email')]";
    private WebDriver driver;

    @FindBy(xpath = RESULT_FIELD_LOCATOR)
    WebElement resultOfCompletingCalculator;

    @FindBy(xpath = EMAIL_BUTTON_LOCATOR )
    WebElement emailButton;

    public EstimateWindowPageNew (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCostFromCalculator() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(RESULT_FIELD_LOCATOR)));
        List<WebElement> optionsOfResult = resultOfCompletingCalculator.findElements(By.tagName("md-list-item"));
        return optionsOfResult.get(6).getText();
    }

    public void clickEmailButton() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", emailButton);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
    }

    public void sendEmail(WebDriver driver, String emailAddress) {
        WebElement xxxx = driver.findElement(By.xpath(EMAIL_FIELD_LOCATOR));
        xxxx.sendKeys(Keys.HOME + emailAddress);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(EMAIL_SEND_LOCATOR)));
    }
}
