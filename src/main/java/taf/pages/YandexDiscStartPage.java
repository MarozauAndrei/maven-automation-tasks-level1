package taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiscStartPage {

  private final String startPageUrl = "http://disc.yandex.by";
  private final String loginButtonLocator = "//*[contains(text(),'Войти')]";
  private WebDriver driver;

  public YandexDiscStartPage(WebDriver driver) {
    this.driver = driver;
  }

  public YandexDiscStartPage openPage() {
    driver.get(startPageUrl);
    driver.manage().window().maximize();
    return this;
  }

  public LogInYandexDiscPage clickLogInButton(WebDriverWait wait) {
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loginButtonLocator)));
    driver.findElement(By.xpath(loginButtonLocator)).click();
    return new LogInYandexDiscPage(driver);
  }
}
