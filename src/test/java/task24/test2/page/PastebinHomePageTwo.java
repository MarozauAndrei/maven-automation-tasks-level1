package task24.test2.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinHomePageTwo {
    private static final String HOMEPAGE_URL = "http://pastebin.com";
    private final String CODE_ID_LOCATOR = "paste_code";
    private final String EXPIRATION_LOCATOR = "//select[@name='paste_expire_date']";
    private final String SYNTAX_LOCATOR = "//select[@name='paste_format']";
    private final String NAME_LOCATOR = "//input[@name='paste_name']";
    private final String CREATOR_ID_LOCATOR = "submit";
    private WebDriver driver;

    @FindBy(id = CODE_ID_LOCATOR)
    private WebElement codeInput;

    @FindBy(xpath = SYNTAX_LOCATOR)
    private WebElement selectSyntaxHighlighting;

    @FindBy(xpath = EXPIRATION_LOCATOR)
    private WebElement selectPasteExpiration;

    @FindBy(xpath = NAME_LOCATOR)
    private WebElement pasteNameInput;

    @FindBy(id = CREATOR_ID_LOCATOR)
    private WebElement createNewPaste;

    public PastebinHomePageTwo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinHomePageTwo openHomePage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public PastebinPastePage createNewPaste(String textOfCode, String textOfSyntax, String textOfExpiration, String textOfName) {
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.presenceOfElementLocated(By.id(CODE_ID_LOCATOR)));

        codeInput.sendKeys(textOfCode);

        Select selectSyntax = new Select(selectSyntaxHighlighting);
        selectSyntax.selectByVisibleText(textOfSyntax);

        Select selectExpiration = new Select(selectPasteExpiration);
        selectExpiration.selectByVisibleText(textOfExpiration);

        pasteNameInput.sendKeys(textOfName);

        createNewPaste.click();

        return new PastebinPastePage(driver);
    }
}
