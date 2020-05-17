package taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiscStartPage {
    private final String START_PAGE_URL = "http://disc.yandex.by";
    private final String LOGIN_BUTTON_LOCATOR = "//*[contains(text(),'Войти')]";
    private WebDriver driver;

    public YandexDiscStartPage (WebDriver driver) {
        this.driver = driver;
    }

    public YandexDiscStartPage openPage() {
        driver.get(START_PAGE_URL);
        driver.manage().window().maximize();
        return this;
    }

    public LogInYandexDiscPage clickLogInButton(WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(LOGIN_BUTTON_LOCATOR)));
        driver.findElement(By.xpath(LOGIN_BUTTON_LOCATOR)).click();
        return new LogInYandexDiscPage(driver);
    }
}
