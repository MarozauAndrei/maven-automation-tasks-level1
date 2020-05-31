package taf.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.openqa.selenium.support.ui.WebDriverWait;

public class FileBinPage extends AbstractPage {

  private final String binAreaId = "app";
  private final String cleanBinButtonLocator = "//*[contains(text(),'Очистить Корзину')]";
  private final String deleteButtonLocator =
      "//div[@class='modal__content']/descendant::*[contains(text(),'Очистить')]/..";
  private final String signOfCleanBinLocator = "//*[contains(text(),'Корзина успешно очищена.')]";

  public FileBinPage(WebDriver driver) {
    super(driver);
  }

  public List<WebElement> getDocumentFromBin(WebDriverWait wait, String fileName) {
    driver.navigate().refresh();
    wait.until(presenceOfAllElementsLocatedBy(By.id(binAreaId)));
    return driver.findElements(By.xpath(makeLocator(fileName)));
  }

  public FileBinPage clickButtonClearBin(WebDriverWait wait) {
    moveAndClickElement(By.xpath(cleanBinButtonLocator), wait);
    return this;
  }

  public FileBinPage clickButtonClear(WebDriverWait wait) {
    moveAndClickElement(By.xpath(deleteButtonLocator), wait);
    wait.until(visibilityOfAllElementsLocatedBy(By.xpath(signOfCleanBinLocator)));
    return this;
  }
}
