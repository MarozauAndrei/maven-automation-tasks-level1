package taf.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileDirectoryPage extends AbstractPage {

  private int indexWordPage = 1;
  private final String directoryTitleLocator = "//*[@class='client-listing']/descendant::h1";
  private final String userDirectoryLocator = "//*[@class='client-listing']";
  private final String createNewDocumentLocator = "//*[text()='Текстовый документ']/parent::*";
  private final String deleteButtonLocator =
      "//*[contains(text(),'Удалить')]/parent::*[@data-lego='react']";
  private final String returnToDiskButtonLocator = "//button[@id='/disk']";
  private final String pageTag = "html";

  public FileDirectoryPage(WebDriver driver) {
    super(driver);
  }

  public String getDirectoryTitle(WebDriverWait wait) {
    wait.until(visibilityOfAllElementsLocatedBy(By.xpath(directoryTitleLocator)));
    return driver.findElement(By.xpath(directoryTitleLocator)).getAttribute("title");
  }

  public FileDirectoryPage contextClickDirectoryArea(WebDriverWait wait) {
    contextClickElement(By.xpath(userDirectoryLocator), wait);
    return this;
  }

  public DocumentWordPage createNewDocument(WebDriverWait wait) {
    clickElement(By.xpath(createNewDocumentLocator), wait);
    moveToDocumentTab();
    return new DocumentWordPage(driver);
  }

  public boolean isElementEmpty(WebDriverWait wait, String fileName) {
    driver.navigate().refresh();
    wait.until(presenceOfAllElementsLocatedBy(By.tagName(pageTag)));
    return driver.findElements(By.xpath(makeLocator(fileName))).isEmpty();
  }

  public DocumentWordPage openDocument(WebDriverWait wait, String fileName) {
    wait.until(presenceOfAllElementsLocatedBy(By.xpath(userDirectoryLocator)));
    doubleClickElement(By.xpath(makeLocator(fileName)), wait);
    moveToDocumentTab();
    return new DocumentWordPage(driver);
  }

  public FileDirectoryPage contextClickDocument(WebDriverWait wait, String fileName) {
    driver.navigate().refresh();
    contextClickElement(By.xpath(makeLocator(fileName)), wait);
    return this;
  }

  public FileDirectoryPage deleteDocument(WebDriverWait wait) {
    clickElement(By.xpath(deleteButtonLocator), wait);
    return this;
  }

  public MainMenuSectionFilePage returnToFileSection(WebDriverWait wait) {
    clickElement(By.xpath(returnToDiskButtonLocator), wait);
    return new MainMenuSectionFilePage(driver);
  }

  private void moveToDocumentTab() {
    ArrayList<String> tabsList = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(tabsList.get(indexWordPage));
  }
}
