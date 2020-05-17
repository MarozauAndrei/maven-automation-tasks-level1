package task24.calculator.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {
//    private static final String RESOURCES_PATH = "src\\main\\resources\\";
    private static WebDriver driver;

    private DriverSingleton() {}

    public static WebDriver getDriver() {
        if (null == driver) {
            switch (System.getProperty("browser")) {
                case "firefox": {
//                    System.setProperty("webdriver.gecko.driver", RESOURCES_PATH + "geckodriver.exe");
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                default: {
//                    System.setProperty("webdriver.chrome.driver", RESOURCES_PATH + "chromedriver.exe");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
            }
        }
        return driver;
    }

    public static void closeBrowser() {
        driver.quit();
        driver = null;
    }
}
