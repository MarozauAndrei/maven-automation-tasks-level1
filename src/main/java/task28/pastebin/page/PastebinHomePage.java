package task28.pastebin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinHomePage {
    private static final String HOMEPAGE_URL = "http://pastebin.com";
    private final String INPUT_NEW_PASTE_ID = "paste_code";
    private final String INPUT_EXPIRATION_LOCATOR = "//select[@name='paste_expire_date']";
    private final String INPUT_SYNTAX_LOCATOR = "//select[@name='paste_format']";
    private final String INPUT_NAME_LOCATOR = "//input[@name='paste_name']";
    private final String CREATE_NEW_PASTE_BUTTON_ID = "submit";
    private WebDriver driver;

    public PastebinHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public PastebinHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public void createNewPaste(WebDriverWait webDriverWait, String code, String expiration, String name) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(INPUT_NEW_PASTE_ID)));
        driver.findElement(By.id(INPUT_NEW_PASTE_ID)).sendKeys(code);
        Select select = new Select(driver.findElement(By.xpath(INPUT_EXPIRATION_LOCATOR)));
        select.selectByVisibleText(expiration);
        driver.findElement(By.xpath(INPUT_NAME_LOCATOR)).sendKeys(name);
    }

    public void setSyntaxInNewPaste(String Syntax) {
        Select selectSyntax = new Select(driver.findElement(By.xpath(INPUT_SYNTAX_LOCATOR)));
        selectSyntax.selectByVisibleText(Syntax);
    }

    public PastebinPastePage saveNewPaste() {
        driver.findElement(By.id(CREATE_NEW_PASTE_BUTTON_ID)).click();
        return new PastebinPastePage(driver);
    }
}