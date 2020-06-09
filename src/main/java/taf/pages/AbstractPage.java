package taf.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {

  protected final String documentLocatorFormat = "//*[@title='%s.docx']";
  protected WebDriver driver;
  protected Actions action;

  public AbstractPage(WebDriver driver) {
    this.driver = driver;
    this.action = new Actions(driver);
  }

  protected void clickElement(By by, WebDriverWait wait) {
    wait.until(elementToBeClickable(by));
    driver.findElement(by).click();
  }

  protected void moveAndClickElement(By by, WebDriverWait wait) {
    wait.until(elementToBeClickable(by));
    action.moveToElement(driver.findElement(by))
        .click().build().perform();
  }

  protected void doubleClickElement(By by, WebDriverWait wait) {
    wait.until(elementToBeClickable(by));
    action.moveToElement(driver.findElement(by))
        .doubleClick().build().perform();
  }

  protected void contextClickElement(By by, WebDriverWait wait) {
    wait.until(presenceOfAllElementsLocatedBy(by));
    action.moveToElement(driver.findElement(by))
        .contextClick().build().perform();
  }

  protected void inputTextToField(By by, String text) {
    action.moveToElement(driver.findElement(by))
        .click()
        .sendKeys(text)
        .sendKeys(Keys.ENTER)
        .build().perform();
  }

  protected String makeLocator(String fileName) {
    return String.format(documentLocatorFormat, fileName);
  }
}
