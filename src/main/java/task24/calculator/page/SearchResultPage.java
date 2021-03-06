package task24.calculator.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage {
    private final String CALCULATOR_BUTTON_LOCATOR = "//div[@class='gsc-thumbnail-inside']/descendant::b[text()='Google Cloud Platform Pricing Calculator']";
    private WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public CloudCalculatorPage chooseCalculator(WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(CALCULATOR_BUTTON_LOCATOR)));
        driver.findElement(By.xpath(CALCULATOR_BUTTON_LOCATOR)).click();
        return new CloudCalculatorPage(driver);
    }
}
