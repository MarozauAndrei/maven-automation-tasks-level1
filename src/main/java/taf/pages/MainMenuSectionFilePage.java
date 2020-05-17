package taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenuSectionFilePage {
    private final String MAIN_MENU_ELEMENT_BODY_LOCATOR = "//*[@id='app']";
    private String CREATE_NEW_DIRECTORY_LOCATOR = "//*[text()='Новая папка']/parent::*[@data-lego='react']";
    private String NAME_DIRECTORY_LOCATOR = "//div[@class='dialog__body']/descendant::*[@class='textinput__control']";
    private String SAVE_DIRECTORY_BUTTON_LOCATOR = "//*[@class='confirmation-dialog__footer']/button";
    private final String NEW_DIRECTORY_LOCATOR_FORMAT = "//div[@class = 'listing__items']/descendant::*[contains(text(),'%s')]";
    private final String BIN_LOCATOR = "//*[contains(text(),'Корзина')]/ancestor::div[@class = 'listing-item__info']";
    private WebDriver driver;
    private Actions builder;

    public MainMenuSectionFilePage(WebDriver driver) {
        this.driver = driver;
        this.builder = new Actions(driver);
    }

    public void createNewDirectory (WebDriverWait webDriverWait, String directoryName) {
        builder.moveToElement(driver.findElement(By.xpath(MAIN_MENU_ELEMENT_BODY_LOCATOR))).contextClick().build().perform();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(CREATE_NEW_DIRECTORY_LOCATOR)));
        driver.findElement(By.xpath(CREATE_NEW_DIRECTORY_LOCATOR)).click();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(NAME_DIRECTORY_LOCATOR)));
        builder.moveToElement(driver.findElement(By.xpath(NAME_DIRECTORY_LOCATOR))).sendKeys(Keys.DELETE)
                .sendKeys(directoryName).build().perform();
        driver.findElement(By.xpath(SAVE_DIRECTORY_BUTTON_LOCATOR)).click();
    }

    public FileDirectoryPage openNewDirectory (WebDriverWait webDriverWait, String directoryName) {
        String newDirectoryLocator = String.format(NEW_DIRECTORY_LOCATOR_FORMAT, directoryName);
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(newDirectoryLocator)));
        builder.moveToElement(driver.findElement(By.xpath(newDirectoryLocator))).doubleClick().build().perform();
        return new FileDirectoryPage(driver);
    }

    public FileBinPage openBin (WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(BIN_LOCATOR)));
        builder.moveToElement(driver.findElement(By.xpath(BIN_LOCATOR))).doubleClick().build().perform();
        return new FileBinPage(driver);
    }
}
