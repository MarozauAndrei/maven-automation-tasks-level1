package taf.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInYandexDiscPage {
    private final String INPUT_LOGIN_LOCATOR = "//*[@class='passp-form-field__input']";
    private final String INPUT_PASSWORD_LOCATOR = "//input[@id='passp-field-passwd']";
    private final String LOGIN_MISTAKE_LOCATOR = "//*[@class='passp-form-field__error']";
    private final String RETURN_BUTTON_LOCATOR = "//*[@class='passp-previous-step-button__icon']";
    private WebDriver driver;
    private Actions builder;

    public LogInYandexDiscPage(WebDriver driver) {
        this.driver = driver;
        this.builder = new Actions(driver);
    }

    public void inputLogin(WebDriverWait webDriverWait, String login) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(INPUT_LOGIN_LOCATOR)));
        builder.moveToElement(driver.findElement(By.xpath(INPUT_LOGIN_LOCATOR))).click().sendKeys(login).sendKeys(Keys.ENTER).build().perform();
    }

    public YandexDiscPage inputPassword(WebDriverWait webDriverWait, String password) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(INPUT_PASSWORD_LOCATOR)));
        builder.moveToElement(driver.findElement(By.xpath(INPUT_PASSWORD_LOCATOR))).click().build().perform();
        builder.moveToElement(driver.findElement(By.xpath(INPUT_PASSWORD_LOCATOR))).sendKeys(password).sendKeys(Keys.ENTER).build().perform();
        return new YandexDiscPage(driver);
    }

    public boolean checkInputMistake(WebDriverWait webDriverWait) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(LOGIN_MISTAKE_LOCATOR)));
            return driver.findElement(By.xpath(LOGIN_MISTAKE_LOCATOR)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public void returnToLoginField(WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(RETURN_BUTTON_LOCATOR)));
        driver.findElement(By.xpath(RETURN_BUTTON_LOCATOR)).click();
    }
}
