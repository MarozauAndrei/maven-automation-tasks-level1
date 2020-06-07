package task28.calculator.page;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {

  protected final String firstFrameLocator = "//*[@id='cloud-site']/devsite-iframe/iframe";
  protected String nameOfSecondFrame = "myFrame";
  protected WebDriver driver;
  protected JavascriptExecutor executor;
  protected Actions action;

  protected AbstractPage(WebDriver driver) {
    this.driver = driver;
    this.executor = (JavascriptExecutor) driver;
    this.action = new Actions(driver);
  }

  void moveToFrame(WebDriverWait wait) {
    wait.until(presenceOfElementLocated(By.xpath(firstFrameLocator)));
    driver.switchTo().frame(driver.findElement(By.xpath(firstFrameLocator)))
        .switchTo().frame(nameOfSecondFrame);
  }

  void clickElement(WebDriverWait wait, By by) {
    wait.until(elementToBeClickable(by));
    driver.findElement(by).click();
  }

  void jsClickElement(By by) {
    executor.executeScript("arguments[0].click();", driver.findElement(by));
  }
}
