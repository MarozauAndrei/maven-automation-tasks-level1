package taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiscPage {

  private final String yandexDiscTitleLocator = "//title[contains(text(),'Яндекс.Диск')]";
  private final String mainMenuElementFormat = "//div[@class = 'navigation__item']/*[@title = '%s']";
  private final String mainMenuElementTitleLocator = "//div[@id='app']/descendant::h1";
  private final String changeUserLocator = "//*[@class='user2 user2 user2_hide-name']";
  private final String exitLocator = "//*[@class='menu__list-item']/ *[contains(text(),'Выйти')]";
  private WebDriver driver;

  public YandexDiscPage(WebDriver driver) {
    this.driver = driver;
  }

  public WebElement getTitle(WebDriverWait wait) {
    wait.until(presenceOfElementLocated(By.xpath(yandexDiscTitleLocator)));
    return driver.findElement(By.xpath(yandexDiscTitleLocator));
  }

  public YandexDiscPage openMainMenuSection(WebDriverWait wait, String partOfTitle) {
    String locator = String.format(mainMenuElementFormat, partOfTitle);
    wait.until(elementToBeClickable(By.xpath(locator)));
    driver.findElement(By.xpath(locator)).click();
    return this;
  }

  public String getTitleMainMenuSection(WebDriverWait wait) {
    wait.until(presenceOfElementLocated(By.xpath(mainMenuElementTitleLocator)));
    return driver.findElement(By.xpath(mainMenuElementTitleLocator)).getText();
  }

  public YandexDiscPage closeUserSession(WebDriverWait wait) {
    wait.until(presenceOfElementLocated(By.xpath(changeUserLocator)));
    wait.until(elementToBeClickable(By.xpath(changeUserLocator)));
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(By.xpath(changeUserLocator)))
        .click().build().perform();
    wait.until(presenceOfElementLocated(By.xpath(exitLocator)));
    action.moveToElement(driver.findElement(By.xpath(exitLocator)))
        .click().build().perform();
    return this;
  }
}
