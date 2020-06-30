package task28.calculator.page;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TenMinutesMailPage extends AbstractPage {

  private final String mailPageUrl = "https://10minutemail.com/";
  private final String bufferButtonLocator = "//*[@id='copy_address']";
  private final String messageTopLocator = "//*[@class='message_top']";
  private final String totalCostLocator = "//table[@class='quote']/descendant::h3[not(contains(text(),'Total'))]";
  private final int indexMailPage = 1;

  public TenMinutesMailPage(WebDriver driver) {
    super(driver);
  }

  public String getEmaiAddress(WebDriverWait wait) throws IOException, UnsupportedFlavorException {
    openMailTab();
    moveAndClickElement(wait, By.xpath(bufferButtonLocator));
    return (String) Toolkit.getDefaultToolkit().getSystemClipboard()
        .getData(DataFlavor.stringFlavor);
  }

  public TenMinutesMailPage openReceiveMail(WebDriverWait wait) {
    moveToMailTab();
    clickElement(wait, By.xpath(messageTopLocator));
    return this;
  }

  public String getCostFromEmail(WebDriverWait wait) {
    wait.until(presenceOfElementLocated(By.xpath(totalCostLocator)));
    return driver.findElement(By.xpath(totalCostLocator)).getText();
  }

  private void moveAndClickElement(WebDriverWait wait, By by) {
    wait.until(elementToBeClickable(by));
    new Actions(driver).moveToElement(driver.findElement(by))
        .click().build().perform();
  }

  private void openMailTab() {
    executor.executeScript("window.open()");
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(indexMailPage));
    driver.get(mailPageUrl);
    driver.manage().window().maximize();
  }

  private void moveToMailTab() {
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(indexMailPage));
  }
}
