package task24.test1.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinHomePage {
    private static final String HOMEPAGE_URL = "http://pastebin.com";
    private final String CODE_ID_LOCATOR = "paste_code";
    private final String EXPIRATION_LOCATOR = "//select[@name='paste_expire_date']";
    private final String NAME_LOCATOR = "//input[@name='paste_name']";
    private final String CREATOR_ID_LOCATOR = "submit";
    private WebDriver driver;

    @FindBy(id = CODE_ID_LOCATOR)
    private WebElement codeInput;

    @FindBy(xpath = EXPIRATION_LOCATOR)
    private WebElement selectPasteExpiration;

    @FindBy(xpath = NAME_LOCATOR)
    private WebElement pasteNameInput;

    @FindBy(id = CREATOR_ID_LOCATOR)
    private WebElement createNewPaste;

    public PastebinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public PastebinHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public int countOfStepsForCreatingNewPaste(String code, String expiration, String name) {
        int counter = 0;
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.presenceOfElementLocated(By.id(CODE_ID_LOCATOR)));

        codeInput.sendKeys(code);
        counter++;

        Select select = new Select(selectPasteExpiration);
        select.selectByVisibleText(expiration);
        counter++;

        pasteNameInput.sendKeys(name);
        counter++;

        createNewPaste.click();
        counter++;

        return counter;
    }
}
