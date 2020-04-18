package task24.test4.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TenMinutesMailPage {
    final String BUFFER_BUTTON_LOCATOR = "//*[@id='copy_address']";
    final String MESSAGE_TOP_LOCATOR = "//*[@class='message_top']";
    final String TOTAL_COST_LOCATOR = "//table[@class='quote']/descendant::h3[not(contains(text(),'Total'))]";
    private WebDriver driver;

    @FindBy(xpath = BUFFER_BUTTON_LOCATOR)
    WebElement bufferButton;

    @FindBy(xpath = MESSAGE_TOP_LOCATOR)
    WebElement message;

    @FindBy(xpath = TOTAL_COST_LOCATOR)
    WebElement totalCost;

    public TenMinutesMailPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String getEmaiAddress() throws IOException, UnsupportedFlavorException {
        driver.manage().window().maximize();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(BUFFER_BUTTON_LOCATOR)));

        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.xpath(BUFFER_BUTTON_LOCATOR))).click().build().perform();
        return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
    }

    public String getCostFromEmail() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(MESSAGE_TOP_LOCATOR)));
        message.click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(TOTAL_COST_LOCATOR)));
        return totalCost.getText();
    }
}
