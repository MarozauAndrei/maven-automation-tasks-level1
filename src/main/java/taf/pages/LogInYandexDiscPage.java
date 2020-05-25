package taf.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInYandexDiscPage {

  private final String inputAreaLocator = "//*[@class='passp-page-overlay']";
  private final String inputLoginLocator = "//*[@class='passp-form-field__input']";
  private final String inputPasswordLocator = "//input[@id='passp-field-passwd']";
  private final String loginMistakeLocator = "//*[@class='passp-form-field__error']";
  private WebDriver driver;
  private Actions action;

  public LogInYandexDiscPage(WebDriver driver) {
    this.driver = driver;
    this.action = new Actions(driver);
  }

  public LogInYandexDiscPage inputLogin(WebDriverWait wait, String login) {
    wait.until(presenceOfAllElementsLocatedBy(By.xpath(inputAreaLocator)));
    if (driver.findElements(By.xpath(inputPasswordLocator)).isEmpty()) {
      wait.until(elementToBeClickable(By.xpath(inputLoginLocator)));
      action.moveToElement(driver.findElement(By.xpath(inputLoginLocator)))
          .click()
          .sendKeys(login)
          .sendKeys(Keys.ENTER)
          .build().perform();
    }
    return this;
  }

  public LogInYandexDiscPage inputPassword(WebDriverWait wait, String password) {
    wait.until(presenceOfElementLocated(By.xpath(inputPasswordLocator)));
    action.moveToElement(driver.findElement(By.xpath(inputPasswordLocator)))
        .click().build().perform();
    action.moveToElement(driver.findElement(By.xpath(inputPasswordLocator)))
        .sendKeys(password)
        .sendKeys(Keys.ENTER)
        .build().perform();
    return this;
  }

  public boolean checkInputMistake(WebDriverWait wait) {
    wait.until(visibilityOfAllElementsLocatedBy(By.xpath(loginMistakeLocator)));
    return !driver.findElements(By.xpath(loginMistakeLocator)).isEmpty();
  }
}
