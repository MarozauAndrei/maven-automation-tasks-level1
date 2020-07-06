package task28.calculator.page;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import task28.calculator.utils.Log;

public class AbstractPage {

  private int webDriverTimeOut = 10;
  private final String firstFrameLocator = "//*[@id='cloud-site']/devsite-iframe/iframe";
  private String nameOfSecondFrame = "myFrame";
  protected WebDriver driver;
  protected WebDriverWait wait;
  protected JavascriptExecutor executor;
  protected Actions action;

  protected AbstractPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, webDriverTimeOut);
    this.executor = (JavascriptExecutor) driver;
    this.action = new Actions(driver);
  }

  void moveToFrame(WebDriverWait wait) {
    Log.info("move to frame");
    wait.until(presenceOfElementLocated(By.xpath(firstFrameLocator)));
    driver.switchTo().frame(driver.findElement(By.xpath(firstFrameLocator)))
        .switchTo().frame(nameOfSecondFrame);
  }

  void clickElement(WebDriverWait wait, By by) {
    Log.info("click element " + by);
    wait.until(elementToBeClickable(by));
    driver.findElement(by).click();
  }

  void jsClickElement(By by) {
    Log.info("java script click element " + by);
    executor.executeScript("arguments[0].click();", driver.findElement(by));
  }
}
