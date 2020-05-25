package taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class DocumentWordPage {

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
  private WebDriver driver;
  private Actions action;

  public DocumentWordPage(WebDriver driver) {
    this.driver = driver;
    this.action = new Actions(driver);
  }

  public DocumentWordPage goToDocumentFrame(WebDriverWait webDriverWait) {
    webDriverWait.until(presenceOfElementLocated(By.xpath(
        documentFrameLocator)));
    WebElement documentFrame = driver.findElement(By.xpath(documentFrameLocator));
    driver.switchTo().frame(documentFrame);
    return this;
  }

  public DocumentWordPage writeText(WebDriverWait wait, String text) {
    wait.until(presenceOfElementLocated(By.xpath(
        headOfDocumentLocator)));
    action.sendKeys(text).build().perform();
    return this;
  }

  public DocumentWordPage reNameDocument(WebDriverWait wait, String filename) {
    wait.until(elementToBeClickable(By.xpath(fileButtonLocator)));
    action.moveToElement(driver.findElement(By.xpath(fileButtonLocator)))
        .click().build().perform();
    wait.until(elementToBeClickable(By.xpath(closeMenuLocator)));
    action.moveToElement(driver.findElement(By.xpath(closeMenuLocator)))
        .click().build().perform();
    wait.until(elementToBeClickable(By.xpath(fileButtonLocator)));
    action.moveToElement(driver.findElement(By.xpath(fileButtonLocator)))
        .click().build().perform();
    wait.until(elementToBeClickable(By.xpath(renameButtonLocator)));
    action.moveToElement(driver.findElement(By.xpath(renameButtonLocator)))
        .click().build().perform();
    action.sendKeys(filename).sendKeys(Keys.ENTER).build().perform();
    return this;
  }

  public DocumentWordPage closeDocument(WebDriverWait wait) {
    wait.until(elementToBeClickable(By.xpath(fileButtonLocator)));
    wait.until(presenceOfElementLocated(By.xpath(fileButtonLocator)));
    action.moveToElement(driver.findElement(By.xpath(fileButtonLocator)))
        .click().build().perform();
    wait.until(presenceOfElementLocated(By.xpath(exitButtonLocator)));
    wait.until(elementToBeClickable(By.xpath(exitButtonLocator)));
    wait.until(presenceOfAllElementsLocatedBy(By.xpath(
        fileMenuLocator)));
    action.moveToElement(driver.findElement(By.xpath(exitButtonLocator)))
        .click().build().perform();
    return this;
  }

  public String getTextFromDocument(WebDriverWait wait) {
    wait.until(presenceOfAllElementsLocatedBy(By.xpath(headOfDocumentLocator)));
    wait.until(presenceOfAllElementsLocatedBy(By.xpath(textInDocumentLocator)));
    List<WebElement> textList = driver.findElements(By.xpath(textInDocumentLocator));
    return textList.get(0).getText();
  }
}
