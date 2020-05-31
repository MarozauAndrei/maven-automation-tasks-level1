package taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiscPage extends AbstractPage {

  private final String yandexDiscTitleLocator = "//title[contains(text(),'Яндекс.Диск')]";
  private final String mainMenuElementFormat = "//div[@class = 'navigation__item']/*[@title = '%s']";
  private final String mainMenuElementTitleLocator = "//div[@id='app']/descendant::h1";
  private final String changeUserLocator = "//*[@class='user2 user2 user2_hide-name']";
  private final String exitLocator = "//*[@class='menu__list-item']/ *[contains(text(),'Выйти')]";

  public YandexDiscPage(WebDriver driver) {
    super(driver);
  }

  public WebElement getTitle(WebDriverWait wait) {
    wait.until(presenceOfElementLocated(By.xpath(yandexDiscTitleLocator)));
    return driver.findElement(By.xpath(yandexDiscTitleLocator));
  }

  public YandexDiscPage openMainMenuSection(WebDriverWait wait, String partOfTitle) {
    String locator = String.format(mainMenuElementFormat, partOfTitle);
    moveAndClickElement(By.xpath(locator), wait);
    return this;
  }

  public String getTitleMainMenuSection(WebDriverWait wait) {
    wait.until(presenceOfElementLocated(By.xpath(mainMenuElementTitleLocator)));
    return driver.findElement(By.xpath(mainMenuElementTitleLocator)).getText();
  }

  public YandexDiscPage clickUserManageButton(WebDriverWait wait) {
    moveAndClickElement(By.xpath(changeUserLocator), wait);
    return this;
  }

  public YandexDiscPage clickQuitUserButton(WebDriverWait wait) {
    moveAndClickElement(By.xpath(exitLocator), wait);
    return this;
  }
}
