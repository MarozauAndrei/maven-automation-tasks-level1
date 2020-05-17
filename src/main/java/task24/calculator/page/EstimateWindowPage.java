package task24.calculator.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class EstimateWindowPage {
    private final String RESULT_FIELD_LOCATOR = "//*[@id='compute']/md-list";
    private final String RESULT_LIST_TAG = "md-list-item";
    private final String EMAIL_BUTTON_LOCATOR = "//button[contains(text(),'Email Estimate')]";
    private final String EMAIL_FIELD_LOCATOR = "//md-input-container/descendant::*[contains(text(),'Email')]/../ input";
    private final String EMAIL_SEND_LOCATOR = "//form[@name='emailForm']/descendant::button[contains(text(),'Send Email')]";
    private WebDriver driver;

    public EstimateWindowPage (WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getListOfEstimateResults (WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(RESULT_FIELD_LOCATOR)));
        WebElement resultOfCompletingCalculator = driver.findElement(By.xpath(RESULT_FIELD_LOCATOR));
        return resultOfCompletingCalculator.findElements(By.tagName(RESULT_LIST_TAG));
    }

    public void clickEmailButton(JavascriptExecutor executor) {
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(EMAIL_BUTTON_LOCATOR)));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
    }

    public void sendEmail(WebDriver driver, String emailAddress, JavascriptExecutor executor) {
        driver.findElement(By.xpath(EMAIL_FIELD_LOCATOR)).
                sendKeys(Keys.HOME + emailAddress);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(EMAIL_SEND_LOCATOR)));
    }
}
