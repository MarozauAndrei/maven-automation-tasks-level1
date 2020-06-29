package taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiscStartPage extends AbstractPage {

  private final String startPageUrl = "http://disc.yandex.by";
  private final String loginButtonLocator = "//*[contains(text(),'Войти')]";

  public YandexDiscStartPage(WebDriver driver) {
    super(driver);
  }

  public YandexDiscStartPage openPage() {
    driver.get(startPageUrl);
    driver.manage().window().maximize();
    return this;
  }

  public LogInYandexDiscPage clickLogInButton(WebDriverWait wait) {
    clickElement(By.xpath(loginButtonLocator), wait);
    return new LogInYandexDiscPage(driver);
  }
}
