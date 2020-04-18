package task24.test3.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage {
    private final String CALCULATOR_BUTTON_LOCATOR = "//div[@class='gsc-thumbnail-inside']/descendant::b[text()='Google Cloud Platform Pricing Calculator']";
    private WebDriver driver;

    @FindBy(xpath = CALCULATOR_BUTTON_LOCATOR)
    WebElement googleCalculatorButton;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CloudCalculatorPage chooseCalculator() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath(CALCULATOR_BUTTON_LOCATOR)));
        googleCalculatorButton.click();
        return new CloudCalculatorPage(driver);
    }
}
