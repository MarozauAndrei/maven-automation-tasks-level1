package task24;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CommonConditions {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    private int webDriverTimeOut = 10;

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
//        driver = DriverSingleton.getDriver();
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, webDriverTimeOut);
    }

    @AfterMethod(alwaysRun = true)
    public void browserTurnOff() {
//        DriverSingleton.closeBrowser();
//        driver.quit();
        driver = null;
    }
}
