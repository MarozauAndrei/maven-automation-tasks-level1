package taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileBinPage {
    private final String DOCUMENT_LOCATOR_FORMAT = "//*[@title='%s.docx']";
    private final String CLEAN_BIN_BUTTON_LOCATOR = "//*[contains(text(),'Очистить Корзину')]";
    private final String DELETE_BUTTON_LOCATOR = "//div[@class='modal__content']/descendant::*[contains(text(),'Очистить')]/..";
    private WebDriver driver;

    public FileBinPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkExistOfDocumentInBin(WebDriverWait webDriverWait, String fileName) {
        String locator = String.format(DOCUMENT_LOCATOR_FORMAT, fileName);
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
            return driver.findElement(By.xpath(locator)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public void cleanTheBin(WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(CLEAN_BIN_BUTTON_LOCATOR)));
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.xpath(CLEAN_BIN_BUTTON_LOCATOR))).click().build().perform();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(DELETE_BUTTON_LOCATOR)));
        builder.moveToElement(driver.findElement(By.xpath(DELETE_BUTTON_LOCATOR))).click().build().perform();
    }
}
