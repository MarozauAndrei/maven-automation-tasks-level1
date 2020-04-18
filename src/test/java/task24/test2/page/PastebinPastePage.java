package task24.test2.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinPastePage {
    private final String CODE_ID_LOCATOR = "paste_code";
    private final String TITLE_LOCATOR = "//*[@id='content_left']/descendant::div[@class='paste_box_line1']";
    private final String SYNTAX_LOCATOR = "//*[@id='code_buttons']/span[@class='h_640']/a";
    private WebDriver driver;

    @FindBy(id = CODE_ID_LOCATOR)
    private WebElement code;

    @FindBy(xpath = TITLE_LOCATOR)
    private WebElement title;

    @FindBy(xpath = SYNTAX_LOCATOR)
    private WebElement syntax;

    public PastebinPastePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean compareCode(String textOfCode) {
        boolean comparing = false;
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.presenceOfElementLocated(By.id(CODE_ID_LOCATOR)));
        if (code.getText().equals(textOfCode)) {
            comparing = true;
        }
        return comparing;
    }

    public boolean compareTitle(String textOfName) {
        boolean comparing = false;
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(TITLE_LOCATOR)));
        if (title.getText().equals(textOfName)) {
            comparing = true;
        }
        return comparing;
    }

    public boolean compareSyntax(String textOfSyntax) {
        boolean comparing = false;
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath(SYNTAX_LOCATOR)));
        if (syntax.getText().equals(textOfSyntax)) {
            comparing = true;
        }
        return comparing;
    }

}

