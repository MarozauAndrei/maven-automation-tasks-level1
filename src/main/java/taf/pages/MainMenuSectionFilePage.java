package taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenuSectionFilePage {

  private final String mainMenuElementBodyLocator = "//*[@id='app']";
  private final String createNewDirectoryLocator =
      "//*[text()='Новая папка']/parent::*[@data-lego='react']";
  private final String nameDirectoryLocator =
      "//div[@class='dialog__body']/descendant::*[@class='textinput__control']";
  private final String saveDirectoryButtonLocator =
      "//*[@class='confirmation-dialog__footer']/button";
  private final String newDirectoryLocatorFormat =
      "//div[@class = 'listing__items']/descendant::*[@title='%s']";
  private final String binLocator =
      "//*[contains(text(),'Корзина')]/ancestor::div[@class = 'listing-item__info']";
  private WebDriver driver;
  private Actions action;

  public MainMenuSectionFilePage(WebDriver driver) {
    this.driver = driver;
    this.action = new Actions(driver);
  }

  public MainMenuSectionFilePage createNewDirectory(WebDriverWait wait, String directoryName) {
    wait.until(presenceOfAllElementsLocatedBy(By.xpath(mainMenuElementBodyLocator)));
    action.moveToElement(driver.findElement(By.xpath(mainMenuElementBodyLocator)))
        .contextClick().build().perform();
    wait.until(elementToBeClickable(By.xpath(createNewDirectoryLocator)));
    driver.findElement(By.xpath(createNewDirectoryLocator)).click();
    wait.until(visibilityOfAllElementsLocatedBy(By.xpath(nameDirectoryLocator)));
    action.moveToElement(driver.findElement(By.xpath(nameDirectoryLocator)))
        .sendKeys(Keys.DELETE)
        .sendKeys(directoryName)
        .build().perform();
    driver.findElement(By.xpath(saveDirectoryButtonLocator)).click();
    return this;
  }

  public FileDirectoryPage openNewDirectory(WebDriverWait wait, String directoryName) {
    String newDirectoryLocator = String.format(newDirectoryLocatorFormat, directoryName);
    wait.until(visibilityOfAllElementsLocatedBy(By.xpath(newDirectoryLocator)));
    action.moveToElement(driver.findElement(By.xpath(newDirectoryLocator)))
        .doubleClick().build().perform();
    return new FileDirectoryPage(driver);
  }

  public FileBinPage openBin(WebDriverWait wait) {
    wait.until(visibilityOfAllElementsLocatedBy(By.xpath(binLocator)));
    action.moveToElement(driver.findElement(By.xpath(binLocator)))
        .doubleClick().build().perform();
    return new FileBinPage(driver);
  }
}
