package task24;

import java.util.Arrays;
import java.util.List;
import task24.calculator.page.CloudCalculatorPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import task24.calculator.page.EstimateWindowPage;
import task24.calculator.page.StartCloudPage;

public class WebDriverCalculatorTestThree {

  private WebDriver driver;
  private int webDriverTimeOut = 10;
  private final List<String> expectedResults = Arrays.asList(
      "VM class: regular",
      "Instance type: n1-standard-8",
      "Region: Frankfurt",
      "Total available local SSD space 2x375 GiB",
      "Commitment term: 1 Year",
      "Estimated Component Cost: USD 1,082.77 per 1 month"
  );

  @BeforeMethod(alwaysRun = true)
  public void browserSetUp() {
    driver = new ChromeDriver();
  }

  @Test(description = "Test N3 - Check of completing google calculator")
  public void completeAndCheckGoogleCalculator() {
    WebDriverWait webDriverWait = new WebDriverWait(driver, webDriverTimeOut);
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    CloudCalculatorPage cloudCalculatorPage = new StartCloudPage(driver).openStartPage()
        .searchCalculatorInCloud(webDriverWait).chooseCalculator(webDriverWait);
    cloudCalculatorPage.moveToFrame(webDriverWait);
    cloudCalculatorPage.setNumberOfInstance(webDriverWait);
    cloudCalculatorPage.setMachineType(executor);
    cloudCalculatorPage.completeGpuArea(webDriverWait, executor);
    cloudCalculatorPage.setLocalssd(executor);
    cloudCalculatorPage.setDatacenterLocation(executor);
    cloudCalculatorPage.setCommittedUsage(executor);
    EstimateWindowPage estimateWindowPage = cloudCalculatorPage.saveEstimate(executor);
    List<WebElement> optionsOfResult = estimateWindowPage.getListOfEstimateResults(webDriverWait);
    optionsOfResult.remove(0);

    SoftAssert softAssert = new SoftAssert();
    for (int i = 0; i < optionsOfResult.size(); i++) {
      softAssert.assertEquals(optionsOfResult.get(i).getText(), expectedResults.get(i),
          optionsOfResult.get(i).getText() + " - is wrong field   ");
    }
    softAssert.assertAll();
  }

  @AfterMethod(alwaysRun = true)
  public void browserTurnOff() {
    driver.quit();
    driver = null;
  }
}
