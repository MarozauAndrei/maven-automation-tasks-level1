package taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.support.ui.WebDriverWait;

public class FileDirectoryPage {

  private final String userDirectoryLocator = "//*[@class='client-listing']";
  private final String createNewDocumentLocator = "//*[text()='Текстовый документ']/parent::*";
  private final String nameDocumentLocatorFormat = "//*[@title='%s.docx']";
  private final String deleteButtonLocator =
      "//*[contains(text(),'Удалить')]/parent::*[@data-lego='react']";
  private final String returnToDiskButtonLocator = "//button[@id='/disk']";
  private WebDriver driver;
  private Actions action;

  public FileDirectoryPage(WebDriver driver) {
    this.driver = driver;
    this.action = new Actions(driver);
  }

  public boolean checkNewDirectory(WebDriverWait wait) {
    wait.until(visibilityOfAllElementsLocatedBy(By.xpath(
        userDirectoryLocator)));
    return driver.findElement(By.xpath(userDirectoryLocator)).isDisplayed();
  }

  public DocumentWordPage createNewDocument(WebDriverWait wait) {
    action.moveToElement(driver.findElement(By.xpath(userDirectoryLocator)))
        .contextClick().build().perform();
    wait.until(elementToBeClickable(By.xpath(createNewDocumentLocator)));
    driver.findElement(By.xpath(createNewDocumentLocator)).click();
    return new DocumentWordPage(driver);
  }

  public boolean checkExistOfDocument(String fileName) {
    String locator = String.format(nameDocumentLocatorFormat, fileName);
    return driver.findElements(By.xpath(locator)).isEmpty();
  }

  public FileDirectoryPage openDocument(WebDriverWait wait, String fileName) {
    driver.navigate().refresh();
    wait.until(presenceOfAllElementsLocatedBy(By.xpath(userDirectoryLocator)));
    String locator = String.format(nameDocumentLocatorFormat, fileName);
    wait.until(presenceOfElementLocated(By.xpath(locator)));
    action.moveToElement(driver.findElement(By.xpath(locator)))
        .doubleClick().build().perform();
    return this;
  }

  public FileDirectoryPage deleteDocument(WebDriverWait wait, String fileName) {
    driver.navigate().refresh();
    String locator = String.format(nameDocumentLocatorFormat, fileName);
    wait.until(presenceOfAllElementsLocatedBy(By.xpath(locator)));
    action.moveToElement(driver.findElement(By.xpath(locator)))
        .contextClick().build().perform();
    wait.until(elementToBeClickable(By.xpath(deleteButtonLocator)));
    driver.findElement(By.xpath(deleteButtonLocator)).click();
    return this;
  }

  public MainMenuSectionFilePage returnToFileSection(WebDriverWait wait) {
    wait.until(elementToBeClickable(By.xpath(
        returnToDiskButtonLocator)));
    driver.findElement(By.xpath(returnToDiskButtonLocator)).click();
    return new MainMenuSectionFilePage(driver);
  }
}
