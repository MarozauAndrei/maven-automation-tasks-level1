package task28;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import task28.calculator.driver.DriverSingleton;
import task28.calculator.utils.TestListener;

@Listeners({TestListener.class})
public class CommonConditions {

  protected WebDriver driver;

  @BeforeMethod(alwaysRun = true)
  public void browserSetUp() {
    driver = DriverSingleton.getDriver();
  }

  @AfterMethod(alwaysRun = true)
  public void browserTurnOff() {
    DriverSingleton.closeBrowser();
  }
}
