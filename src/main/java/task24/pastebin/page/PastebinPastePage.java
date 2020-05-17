package task24.pastebin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinPastePage {
    private final String CODE_ID = "paste_code";
    private final String TITLE_LOCATOR = "//*[@id='content_left']/descendant::div[@class='paste_box_line1']";
    private final String SYNTAX_LOCATOR = "//*[@id='code_buttons']/span[@class='h_640']/a";
    private WebDriver driver;

    public PastebinPastePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCodeFromPaste(WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(CODE_ID)));
        return driver.findElement(By.id(CODE_ID)).getText();
    }

    public String getTitleFromPaste() {
        return driver.findElement(By.xpath(TITLE_LOCATOR)).getText();
    }

    public String getSyntaxFromPaste() {
        return driver.findElement(By.xpath(SYNTAX_LOCATOR)).getText();
    }
}

