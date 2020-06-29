package taf.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DocumentWordPage extends AbstractPage {

  private int indexDirectoryPage = 0;
  private final String documentFrameLocator = "//iframe[@class='editor-doc__iframe']";
  private final String headOfDocumentLocator = "//*[@id='id__0']";
  private final String textInDocumentLocator = "//*[@class='TextRun']";
  private final String fileButtonLocator =
      "//*[contains(text(),'Файл')]/ancestor::button/parent::div";
  private final String closeMenuLocator = "//*[@title='Закрыть меню']/..";
  private final String renameButtonLocator =
      "//*[contains(text(),'Переименовать')]/ancestor::*[@id='jbtnRenameDialog-Menu48']";
  private final String exitButtonLocator = "//*[@id='btnjClose-Menu32']";
  private final String pageTag = "html";
  private WebDriverWait wait;

  public DocumentWordPage(WebDriver driver) {
    super(driver);
    wait = new WebDriverWait(driver, 10);
    moveToDocumentFrame(wait);
  }

  public DocumentWordPage inputText(WebDriverWait wait, String text) {
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
    moveToDirectoryTab();
    return this;
  }

  public String getTextFromDocument(WebDriverWait wait) {
    wait.until(presenceOfAllElementsLocatedBy(By.xpath(headOfDocumentLocator)));
    wait.until(presenceOfAllElementsLocatedBy(By.xpath(textInDocumentLocator)));
    return driver.findElements(By.xpath(textInDocumentLocator)).get(0).getText();
  }

  private void moveToDirectoryTab() {
    ArrayList<String> tabsList = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(tabsList.get(indexDirectoryPage));
  }

  private void moveToDocumentFrame(WebDriverWait wait) {
    wait.until(presenceOfElementLocated(By.xpath(documentFrameLocator)));
    WebElement documentFrame = driver.findElement(By.xpath(documentFrameLocator));
    driver.switchTo().frame(documentFrame);
  }
}
