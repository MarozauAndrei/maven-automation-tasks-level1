package taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.support.ui.WebDriverWait;

public class FileBinPage {

  private final String documentLocatorFormat = "//*[@title='%s.docx']";
  private final String binAreaId = "app";
  private final String cleanBinButtonLocator = "//*[contains(text(),'Очистить Корзину')]";
  private final String deleteButtonLocator =
      "//div[@class='modal__content']/descendant::*[contains(text(),'Очистить')]/..";
  private WebDriver driver;

  public FileBinPage(WebDriver driver) {
    this.driver = driver;
  }

  public boolean checkExistOfDocumentInBin(WebDriverWait wait, String fileName) {
    String locator = String.format(documentLocatorFormat, fileName);
    wait.until(presenceOfAllElementsLocatedBy(By.id(binAreaId)));
    return driver.findElements(By.xpath(locator)).isEmpty();
  }

  public void cleanTheBin(WebDriverWait wait) {
    wait.until(elementToBeClickable(By.xpath(cleanBinButtonLocator)));
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(By.xpath(cleanBinButtonLocator)))
        .click().build().perform();
    wait.until(elementToBeClickable(By.xpath(deleteButtonLocator)));
    action.moveToElement(driver.findElement(By.xpath(deleteButtonLocator)))
        .click().build().perform();
  }
}
