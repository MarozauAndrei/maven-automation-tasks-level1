package task24;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import task24.calculator.page.CloudCalculatorPage;
import task24.calculator.page.EstimateWindowPage;
import task24.calculator.page.StartCloudPage;
import task24.calculator.page.TenMinutesMailPage;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebDriverCalculatorTestFour {
    private final String MAIL_PAGE_URL = "https://10minutemail.com/";
    private WebDriver driver;
    private int webDriverTimeOut = 10;

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        driver = new ChromeDriver();
    }

    @Test(description = "Test N4 - Send Email and check of total cost")
    public void completeGoogleCalculatorAndSendEmail() throws IOException, UnsupportedFlavorException {

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
        String costFromCalculator = optionsOfResult.get(6).getText();
        estimateWindowPage.clickEmailButton(executor);

        executor.executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(MAIL_PAGE_URL);
        TenMinutesMailPage tenMinutesMailPage = new TenMinutesMailPage(driver);
        String emailAddress = tenMinutesMailPage.getEmaiAddress(webDriverWait);
        driver.switchTo().window(tabs.get(0));
        cloudCalculatorPage.moveToFrame(webDriverWait);
        estimateWindowPage.sendEmail(driver, emailAddress, executor);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
        driver.switchTo().window(tabs.get(1));
        String totalCostFromEmail = tenMinutesMailPage.getCostFromEmail(webDriverWait);
        Assert.assertTrue(costFromCalculator.contains(totalCostFromEmail), " Cost from calculator <> cost from Email ");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTurnOff() {
        driver.quit();
        driver = null;
    }
}
