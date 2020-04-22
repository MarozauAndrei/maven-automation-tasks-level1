package task24;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import task24.pastebin.page.PastebinHomePage;

public class WebDriverPastebinTestOne {
    private WebDriver driver;
    private int webDriverTimeOut = 10;
    private String code = "Hello from WebDriver";
    private String expiration = "10 Minutes";
    private String name = "helloweb";

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        driver = new ChromeDriver();
    }

    @Test(description = "Test N1 - Check of creating new paste in pastebin.com")
    public void checkOfCreatingNewPaste() {
        String creatingNewPaste = "Creating is start";
        PastebinHomePage pastebinHomePage = new PastebinHomePage(driver).openPage();
        WebDriverWait webDriverWait = new WebDriverWait(driver, webDriverTimeOut);
        pastebinHomePage.createNewPaste(webDriverWait, code, expiration, name);
        creatingNewPaste = "Creating is finish";
        Assert.assertEquals(creatingNewPaste, "Creating is finish", "New paste is not create");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTurnOff() {
        driver.quit();
        driver = null;
    }
}
