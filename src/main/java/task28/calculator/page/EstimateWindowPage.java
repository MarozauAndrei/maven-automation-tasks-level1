package task28.calculator.page;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EstimateWindowPage extends AbstractPage {

  private final String resultFieldLocator = "//*[@id='compute']/md-list";
  private final String resultListTag = "md-list-item";
  private final String estimatedCostLocator = "//*[@id='compute']/descendant::*[contains(text(),'Estimated')]";
  private final String emailButtonLocator = "//button[contains(text(),'Email Estimate')]";
  private final String EemailAreaLocator = "//md-dialog[@aria-label='Email Estiamte']/form";
  private final String emailAddressFieldLocator = "//md-input-container/descendant::*[contains(text(),'Email')]/../ input";
  private final String emailSendLocator = "//form[@name='emailForm']/descendant::button[contains(text(),'Send Email')]";
  private final int indexEstimatePage = 0;

  public EstimateWindowPage(WebDriver driver) {
    super(driver);
  }

  public List<String> getListOfEstimateResults() {
    List<WebElement> optionsOfResult = getListOfEstimateElements(wait);
    return getTextFromElements(optionsOfResult);
  }

  public String getEstimatedCost() {
    return driver.findElement(By.xpath(estimatedCostLocator)).getText();
  }

  public TenMinutesMailPage jsClickEmailButton() {
    jsClickElement(By.xpath(emailButtonLocator));
    wait.until(presenceOfAllElementsLocatedBy(By.xpath(EemailAreaLocator)));
    return new TenMinutesMailPage(driver);
  }

  public TenMinutesMailPage jsSendEmail(String emailAddress) {
    moveToEstimateTab();
    moveToFrame(wait);
    driver.findElement(By.xpath(emailAddressFieldLocator)).
        sendKeys(Keys.HOME + emailAddress);
    jsClickElement(By.xpath(emailSendLocator));
    return new TenMinutesMailPage(driver);
  }

  private List<WebElement> getListOfEstimateElements(WebDriverWait wait) {
    wait.until(presenceOfElementLocated(By.xpath(resultFieldLocator)));
    return driver.findElement(By.xpath(resultFieldLocator))
        .findElements(By.tagName(resultListTag));
  }

  private List<String> getTextFromElements(List<WebElement> elements) {
    ArrayList<String> text = new ArrayList<>();
    for (WebElement element : elements) {
      text.add(element.getText());
    }
    return text;
  }

  private void moveToEstimateTab() {
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(indexEstimatePage));
  }
}
