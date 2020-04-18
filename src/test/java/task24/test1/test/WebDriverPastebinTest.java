package task24.test1.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import task24.test1.page.PastebinHomePage;

public class WebDriverPastebinTest {
    private WebDriver driver;
    private String code = "Hello from WebDriver";
    private String expiration = "10 Minutes";
    private String name = "helloweb";       // Or it's better to declare variables in test method ?

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        driver = new ChromeDriver();
    }

    @Test(description = "Test N1 - Check of creating new paste in pastebin.com")
    public void createNewPaste() {
        int expectedNumberOfStepsForCreatingNewPaste = new PastebinHomePage(driver)
                .openPage().countOfStepsForCreatingNewPaste(code, expiration, name);

        Assert.assertEquals(expectedNumberOfStepsForCreatingNewPaste, 4, "Not all operations was made");
    }
    @AfterMethod(alwaysRun = true)
    public void browserTurnOff() {
        driver.quit();
        driver = null;
    }
}
