package task24.test4.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartCloudPageNew {
    private static final String START_PAGE_URL = "https://cloud.google.com/";
    private final String SEARCH_BUTTON_LOCATOR = "//input[@name='q']";
    private WebDriver driver;
    private String researchString = "Google Cloud Platform Pricing Calculator";

    @FindBy(xpath = SEARCH_BUTTON_LOCATOR)
    private WebElement searchButton;

    public StartCloudPageNew(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public StartCloudPageNew openStartPageNew() {
        driver.get(START_PAGE_URL);
        driver.manage().window().maximize();
        return this;
    }

    public SearchResultPageNew searchCalculatorInCloudNew () {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(SEARCH_BUTTON_LOCATOR)));
        searchButton.click();
        searchButton.sendKeys(researchString);
        searchButton.sendKeys(Keys.ENTER);
        return new SearchResultPageNew(driver);
    }
}
