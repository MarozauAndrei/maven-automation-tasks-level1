package taf.pages;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.support.ui.WebDriverWait;

public class DocumentWordPage extends AbstractPage {

  private int indexDirectoryPage = 0;
  private int indexWordPage = 1;
  private final String documentFrameLocator = "//iframe[@class='editor-doc__iframe']";
  private final String headOfDocumentLocator = "//*[@id='id__0']";
  private final String textInDocumentLocator = "//*[@class='TextRun']";
  private final String fileButtonLocator =
      "//*[contains(text(),'Файл')]/ancestor::button/parent::div";
  private final String closeMenuLocator = "//*[@title='Закрыть меню']/..";
  private final String renameButtonLocator =
      "//*[contains(text(),'Переименовать')]/ancestor::*[@id='jbtnRenameDialog-Menu48']";
  private final String fileMenuLocator = "//*[@id='menuJewel']";
  private final String exitButtonLocator = "//*[@id='btnjClose-Menu32']";
  private final String pageTag = "html";

  public DocumentWordPage(WebDriver driver) {
    super(driver);
  }

  public DocumentWordPage goToDocumentTab() {
    ArrayList<String> tabsList = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(tabsList.get(indexWordPage));
    return this;
  }

  public DocumentWordPage leaveDocumentTab() {
    ArrayList<String> tabsList = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(tabsList.get(indexDirectoryPage));
    return this;
  }

  public DocumentWordPage goToDocumentFrame(WebDriverWait webDriverWait) {
    webDriverWait.until(presenceOfElementLocated(By.xpath(documentFrameLocator)));
    WebElement documentFrame = driver.findElement(By.xpath(documentFrameLocator));
    driver.switchTo().frame(documentFrame);
    return this;
  }

  public DocumentWordPage writeText(WebDriverWait wait, String text) {
    wait.until(presenceOfElementLocated(By.xpath(headOfDocumentLocator)));
    action.sendKeys(text).build().perform();
    return this;
  }

  public DocumentWordPage clickButtonFile(WebDriverWait wait) {
    wait.until(presenceOfAllElementsLocatedBy(By.tagName(pageTag)));
    moveAndClickElement(By.xpath(fileButtonLocator), wait);
    return this;
  }

  public DocumentWordPage clickButtonCloseMenu(WebDriverWait wait) {
    moveAndClickElement(By.xpath(closeMenuLocator), wait);
    return this;
  }

  public DocumentWordPage clickButtonRename(WebDriverWait wait) {
    moveAndClickElement(By.xpath(renameButtonLocator), wait);
    return this;
  }

  public DocumentWordPage inputFileName(String filename) {
    action.sendKeys(filename).sendKeys(Keys.ENTER).build().perform();
    return this;
  }

  public DocumentWordPage clickButtonCloseDocument(WebDriverWait wait) {
    moveAndClickElement(By.xpath(exitButtonLocator), wait);
    return this;
  }

  public String getTextFromDocument(WebDriverWait wait) {
    wait.until(presenceOfAllElementsLocatedBy(By.xpath(headOfDocumentLocator)));
    wait.until(presenceOfAllElementsLocatedBy(By.xpath(textInDocumentLocator)));
    return driver.findElements(By.xpath(textInDocumentLocator)).get(0).getText();
  }
}
