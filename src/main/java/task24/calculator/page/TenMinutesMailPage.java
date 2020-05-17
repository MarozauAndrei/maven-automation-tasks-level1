package task24.calculator.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TenMinutesMailPage extends AbstractPage {
    final String BUFFER_BUTTON_LOCATOR = "//*[@id='copy_address']";
    final String MESSAGE_TOP_LOCATOR = "//*[@class='message_top']";
    final String TOTAL_COST_LOCATOR = "//table[@class='quote']/descendant::h3[not(contains(text(),'Total'))]";

    public TenMinutesMailPage (WebDriver driver) {
        super(driver);
    }

    public String getEmaiAddress(WebDriverWait webDriverWait) throws IOException, UnsupportedFlavorException {
        driver.manage().window().maximize();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(BUFFER_BUTTON_LOCATOR)));
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.xpath(BUFFER_BUTTON_LOCATOR))).click().build().perform();
        return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
    }

    public String getCostFromEmail(WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(MESSAGE_TOP_LOCATOR)));
//        driver.findElement(By.xpath(MESSAGE_TOP_LOCATOR)).click();
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.xpath(MESSAGE_TOP_LOCATOR))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TOTAL_COST_LOCATOR)));
        return driver.findElement(By.xpath(TOTAL_COST_LOCATOR)).getText();
    }
}
