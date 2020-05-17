package taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiscPage {
    private final String YANDEX_DISC_TITLE_LOCATOR = "//title[contains(text(),'Яндекс.Диск')]";
    private final String MAIN_MENU_ELEMENT_FORMAT = "//div[@class = 'navigation__item']/*[@title = '%s']";
    private final String MAIN_MENU_ELEMENT_TITLE_LOCATOR = "//div[@id='app']/descendant::h1";
    private WebDriver driver;

    public YandexDiscPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getTitle(WebDriverWait webDriverWait) throws NoSuchElementException {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(YANDEX_DISC_TITLE_LOCATOR)));
        return driver.findElement(By.xpath(YANDEX_DISC_TITLE_LOCATOR));
    }

    public void openMainMenuSection(WebDriverWait webDriverWait, String partOfTitle) {
        String locator = String.format(MAIN_MENU_ELEMENT_FORMAT, partOfTitle);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        driver.findElement(By.xpath(locator)).click();
    }

    public String getTitleMainMenuSection(WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MAIN_MENU_ELEMENT_TITLE_LOCATOR)));
        WebElement headTitleOfWindow = driver.findElement(By.xpath(MAIN_MENU_ELEMENT_TITLE_LOCATOR));
        return headTitleOfWindow.getText();
    }
}
