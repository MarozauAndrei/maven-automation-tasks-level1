package task28.calculator.page;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import task28.calculator.model.ComputerEngine;

public class CloudCalculatorPage extends AbstractPage {

  private final String numberOfInstancesLocator = "//*[@id='input_59']";
  private final String selectLocatorFormat = "//*[contains(text(),'%s')]/parent::*";
  private final String gpuLocator = "//*[@aria-label='Add GPUs']/*[@class='md-container md-ink-ripple']";
  private final String numberOfGpuLocator = "//*[@placeholder='Number of GPUs']";
  private final String saveButtonLocator = "//form[@name='ComputeEngineForm']/descendant::" +
      "button[@class='md-raised md-primary cpc-button md-button md-ink-ripple']";

  public CloudCalculatorPage(WebDriver driver) {
    super(driver);
  }

  public CloudCalculatorPage inputNumberOfInstance(ComputerEngine engine) {
    moveToFrame(wait);
    inputText(wait, By.xpath(numberOfInstancesLocator), engine.getNumberOfInstances());
    return this;
  }

  public CloudCalculatorPage jsSelectVariable(String variable) {
    String locator = String.format(selectLocatorFormat, variable);
    jsClickElement(By.xpath(locator));
    return this;
  }

  public CloudCalculatorPage jsClickGpuCheckbox() {
    jsClickElement(By.xpath(gpuLocator));
    return this;
  }

  public CloudCalculatorPage inputNumberOfGpu(ComputerEngine engine) {
    inputText(wait, By.xpath(numberOfGpuLocator), engine.getNumberOfGpu());
    return this;
  }

  public EstimateWindowPage jsSaveEstimate() {
    jsClickElement(By.xpath(saveButtonLocator));
    return new EstimateWindowPage(driver);
  }

  private void inputText(WebDriverWait wait, By by, String text) {
    wait.until(presenceOfElementLocated(by));
    driver.findElement(by).sendKeys(text);
  }
}