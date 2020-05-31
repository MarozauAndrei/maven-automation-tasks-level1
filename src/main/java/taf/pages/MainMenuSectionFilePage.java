package taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenuSectionFilePage extends AbstractPage {

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

  public MainMenuSectionFilePage(WebDriver driver) {
    super(driver);
  }

  public MainMenuSectionFilePage contextClickFileSection(WebDriverWait wait) {
    contextClickElement(By.xpath(mainMenuElementBodyLocator), wait);
    return this;
  }

  public MainMenuSectionFilePage createNewDirectory(WebDriverWait wait) {
    clickElement(By.xpath(createNewDirectoryLocator), wait);
    return this;
  }

  public MainMenuSectionFilePage inputDirectoryName(WebDriverWait wait, String directoryName) {
    wait.until(visibilityOfAllElementsLocatedBy(By.xpath(nameDirectoryLocator)));
    action.moveToElement(driver.findElement(By.xpath(nameDirectoryLocator)))
        .sendKeys(Keys.DELETE)
        .sendKeys(directoryName)
        .build().perform();
    return this;
  }

  public MainMenuSectionFilePage clickSaveDirectoryButton() {
    driver.findElement(By.xpath(saveDirectoryButtonLocator)).click();
    return this;
  }

  public FileDirectoryPage openDirectory(WebDriverWait wait, String directoryName) {
    String newDirectoryLocator = String.format(newDirectoryLocatorFormat, directoryName);
    doubleClickElement(By.xpath(newDirectoryLocator), wait);
    return new FileDirectoryPage(driver);
  }

  public FileBinPage openBin(WebDriverWait wait) {
    doubleClickElement(By.xpath(binLocator), wait);
    return new FileBinPage(driver);
  }
}
