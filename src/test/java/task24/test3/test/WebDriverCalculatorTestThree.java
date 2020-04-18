package task24.test3.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import task24.test3.page.EstimateWindowPage;
import task24.test3.page.StartCloudPage;

import java.util.Arrays;
import java.util.List;

public class WebDriverCalculatorTestThree {
    private WebDriver driver;
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
        EstimateWindowPage estimateWindowPage = new StartCloudPage(driver).openStartPage()
                .searchCalculatorInCloud().chooseCalculator().completeCalculatorForm();

        Assert.assertEquals(estimateWindowPage.checkCalculatorForm(expectedResults), " ", estimateWindowPage.checkCalculatorForm(expectedResults));
    }
    @AfterMethod(alwaysRun = true)
    public void browserTurnOff() {
        driver.quit();
        driver = null;
    }
}
