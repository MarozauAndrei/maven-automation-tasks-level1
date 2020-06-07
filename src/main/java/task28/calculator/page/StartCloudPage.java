package task28.calculator.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartCloudPage extends AbstractPage {

  private static final String START_PAGE_URL = "https://cloud.google.com/";
  private final String searchButtonLocator = "//input[@name='q']";
  private String researchString = "Google Cloud Platform Pricing Calculator";

  public StartCloudPage(WebDriver driver) {
    super(driver);
  }

  public StartCloudPage openStartPage() {
    driver.get(START_PAGE_URL);
    driver.manage().window().maximize();
    return this;
  }

  public StartCloudPage clickSearchButton(WebDriverWait wait) {
    clickElement(wait, By.xpath(searchButtonLocator));
    return this;
  }

  public SearchResultPage inputSearchString() {
    driver.findElement(By.xpath(searchButtonLocator))
        .sendKeys(researchString, Keys.ENTER);
    return new SearchResultPage(driver);
  }
}
