package taf.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInYandexDiscPage extends AbstractPage {

  private final String inputLoginLocator = "//*[@class='passp-form-field__input']";
  private final String inputPasswordLocator = "//input[@id='passp-field-passwd']";
  private final String loginMistakeLocator = "//*[@class='passp-form-field__error']";

  public LogInYandexDiscPage(WebDriver driver) {
    super(driver);
  }

  public LogInYandexDiscPage inputLogin(WebDriverWait wait, String login) {
    wait.until(elementToBeClickable(By.xpath(inputLoginLocator)));
    inputTextToField(By.xpath(inputLoginLocator), login);
    return this;
  }

  public YandexDiscPage inputPassword(WebDriverWait wait, String password) {
    wait.until(presenceOfElementLocated(By.xpath(inputPasswordLocator)));
    inputTextToField(By.xpath(inputPasswordLocator), password);
    return new YandexDiscPage(driver);
  }

  public boolean isMistakeEmpty(WebDriverWait wait) {
    return getElementList(By.xpath(loginMistakeLocator), wait)
        .isEmpty();
  }

  private List<WebElement> getElementList(By by, WebDriverWait wait) {
    wait.until(visibilityOfAllElementsLocatedBy(by));
    return driver.findElements(by);
  }
}
