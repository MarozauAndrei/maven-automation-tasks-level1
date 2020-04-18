package task24.test3.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class EstimateWindowPage {
    private final String RESULT_FIELD_LOCATOR = "//*[@id='compute']/md-list";
    private WebDriver driver;

    @FindBy(xpath = RESULT_FIELD_LOCATOR)
    WebElement resultOfCompletingCalculator;

    public EstimateWindowPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String checkCalculatorForm (List<String> expectedResults) {
        String string = " ";
        StringBuilder stringBuilder = new StringBuilder().append("\n").append("The wrong fields: ");
        int countOfWriteFields = 0;

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(RESULT_FIELD_LOCATOR)));
        List<WebElement> optionsOfResult = resultOfCompletingCalculator.findElements(By.tagName("md-list-item"));
        optionsOfResult.remove(0);
        for (int i = 0; i < optionsOfResult.size(); i++) {
            if (!optionsOfResult.get(i).getText().equals(expectedResults.get(i))) {
                stringBuilder.append("\n").append(optionsOfResult.get(i).getText());
                countOfWriteFields++;
            }
        }
        if (countOfWriteFields > 0) {
            return stringBuilder.append("\n").toString();
        } else {
            return string;
        }
    }
}
