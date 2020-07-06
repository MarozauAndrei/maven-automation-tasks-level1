package task28.calculator.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage extends AbstractPage {

  private final String calculatorButtonLocator = "//div[@class='gsc-thumbnail-inside']/" +
      "descendant::b[text()='Google Cloud Platform Pricing Calculator']";

  public SearchResultPage(WebDriver driver) {
    super(driver);
  }

  public CloudCalculatorPage selectCalculator() {
    clickElement(wait, By.xpath(calculatorButtonLocator));
    return new CloudCalculatorPage(driver);
  }
}
