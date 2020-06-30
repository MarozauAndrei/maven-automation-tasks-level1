package task28;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import task28.calculator.driver.DriverSingleton;
import task28.calculator.utils.TestListener;

@Listeners({TestListener.class})
public class CommonConditions {

  private int webDriverTimeOut = 10;
  protected WebDriver driver;
  protected WebDriverWait wait;

  @BeforeMethod(alwaysRun = true)
  public void browserSetUp() {
    driver = DriverSingleton.getDriver();
    wait = new WebDriverWait(driver, webDriverTimeOut);
  }

  @AfterMethod(alwaysRun = true)
  public void browserTurnOff() {
    DriverSingleton.closeBrowser();
  }
}
