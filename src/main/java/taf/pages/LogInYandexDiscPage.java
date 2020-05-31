package taf.pages;

import java.util.List;
import org.openqa.selenium.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

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

  public List<WebElement> getMistakeField(WebDriverWait wait) {
    wait.until(visibilityOfAllElementsLocatedBy(By.xpath(loginMistakeLocator)));
    return driver.findElements(By.xpath(loginMistakeLocator));
  }
}
