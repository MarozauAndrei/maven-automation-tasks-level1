package taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class DocumentWordPage {
    private final String DOCUMENT_FRAME_LOCATOR = "//iframe[@class='editor-doc__iframe']";
    private final String HEAD_OF_DOCUMENT_LOCATOR = "//*[@id='id__0']";
    private final String TEXT_IN_DOCUMENT_LOCATOR = "//*[@class='TextRun']";
    private final String FILE_BUTTON_LOCATOR = "//*[contains(text(),'Файл')]/ancestor::button";
    private final String CLOSE_MENU_LOCATOR = "//*[@title='Закрыть меню']/..";
    private final String RENAME_BUTTON_LOCATOR = "//*[contains(text(),'Переименовать')]/ancestor::*[@id='jbtnRenameDialog-Menu48']";
    private final String FILE_MENU_LOCATOR = "//*[@id='menuJewel']";
    private final String EXIT_BUTTON_LOCATOR = "//*[@id='btnjClose-Menu32']";
    private WebDriver driver;
    private Actions builder;

    public DocumentWordPage (WebDriver driver) {
        this.driver = driver;
        this.builder = new Actions(driver);
    }

    public void goToDocumentFrame(WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(DOCUMENT_FRAME_LOCATOR)));
        WebElement documentFrame = driver.findElement(By.xpath(DOCUMENT_FRAME_LOCATOR));
        driver.switchTo().frame(documentFrame);
    }

    public void writeText(WebDriverWait webDriverWait, String text) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(HEAD_OF_DOCUMENT_LOCATOR)));
        builder.sendKeys(text).build().perform();
    }

    public void reNameDocument(WebDriverWait webDriverWait, String filename) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(FILE_BUTTON_LOCATOR)));
        builder.moveToElement(driver.findElement(By.xpath(FILE_BUTTON_LOCATOR))).click().build().perform();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(CLOSE_MENU_LOCATOR)));
        builder.moveToElement(driver.findElement(By.xpath(CLOSE_MENU_LOCATOR))).click().build().perform();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(FILE_BUTTON_LOCATOR)));
        builder.moveToElement(driver.findElement(By.xpath(FILE_BUTTON_LOCATOR))).click().build().perform();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(RENAME_BUTTON_LOCATOR)));
        builder.moveToElement(driver.findElement(By.xpath(RENAME_BUTTON_LOCATOR))).click().build().perform();
        builder.sendKeys(filename).sendKeys(Keys.ENTER).build().perform();
    }

    public void closeDocument(WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(FILE_BUTTON_LOCATOR)));
        builder.moveToElement(driver.findElement(By.xpath(FILE_BUTTON_LOCATOR))).click().build().perform();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(EXIT_BUTTON_LOCATOR)));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(EXIT_BUTTON_LOCATOR)));
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(FILE_MENU_LOCATOR)));
        builder.moveToElement(driver.findElement(By.xpath(EXIT_BUTTON_LOCATOR))).click().build().perform();
    }

    public String getTextFromDocument(WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(TEXT_IN_DOCUMENT_LOCATOR)));
        List<WebElement> elementsWithText = driver.findElements(By.xpath(TEXT_IN_DOCUMENT_LOCATOR));
        return elementsWithText.get(0).getText();
    }
}
