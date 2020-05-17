package taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileDirectoryPage {
    private final String USER_DIRECTORY_LOCATOR = "//*[@class='client-listing']";
    private final String CREATE_NEW_DOCUMENT_LOCATOR = "//*[text()='Текстовый документ']/parent::*";
    private final String NAME_DOCUMENT_LOCATOR_FORMAT = "//*[@title='%s.docx']";
    private final String DELETE_BUTTON_LOCATOR = "//*[contains(text(),'Удалить')]/parent::*[@data-lego='react']";
    private final String RETURN_TO_DISK_BUTTON_LOCATOR = "//button[@id='/disk']";
    private WebDriver driver;
    private Actions builder;

    public FileDirectoryPage(WebDriver driver) {
        this.driver = driver;
        this.builder = new Actions(driver);
    }

    public boolean checkNewDirectory (WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(USER_DIRECTORY_LOCATOR)));
        return driver.findElement(By.xpath(USER_DIRECTORY_LOCATOR)).isDisplayed();
    }

    public DocumentWordPage createNewDocument (WebDriverWait webDriverWait) {
        builder.moveToElement(driver.findElement(By.xpath(USER_DIRECTORY_LOCATOR))).contextClick().build().perform();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(CREATE_NEW_DOCUMENT_LOCATOR)));
        driver.findElement(By.xpath(CREATE_NEW_DOCUMENT_LOCATOR)).click();
        return new DocumentWordPage(driver);
    }

    public boolean checkExistOfDocument(WebDriverWait webDriverWait, String fileName) {
        String locator = String.format(NAME_DOCUMENT_LOCATOR_FORMAT, fileName);
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
            return driver.findElement(By.xpath(locator)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public void openDocument (WebDriverWait webDriverWait, String fileName) {
        driver.navigate().refresh();
        String locator = String.format(NAME_DOCUMENT_LOCATOR_FORMAT, fileName);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        builder.moveToElement(driver.findElement(By.xpath(locator))).doubleClick().build().perform();
    }

    public void deleteDocument(WebDriverWait webDriverWait, String fileName) throws InterruptedException {
        String locator = String.format(NAME_DOCUMENT_LOCATOR_FORMAT, fileName);
        builder.moveToElement(driver.findElement(By.xpath(locator))).contextClick().build().perform();
        Thread.sleep(3000);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(DELETE_BUTTON_LOCATOR)));
        driver.findElement(By.xpath(DELETE_BUTTON_LOCATOR)).click();
    }

    public MainMenuSectionFilePage returnToFileSection(WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(RETURN_TO_DISK_BUTTON_LOCATOR)));
        driver.findElement(By.xpath(RETURN_TO_DISK_BUTTON_LOCATOR)).click();
        return new MainMenuSectionFilePage(driver);
    }
}
