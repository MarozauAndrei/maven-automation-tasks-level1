package task28;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import task28.calculator.model.ComputerEngine;
import task28.calculator.page.CloudCalculatorPage;
import task28.calculator.page.EstimateWindowPage;
import task28.calculator.page.StartCloudPage;
import task28.calculator.page.TenMinutesMailPage;
import task28.calculator.service.ComputeEngineCreator;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

public class WebDriverCalculatorTestFour extends CommonConditions {
    private final String MAIL_PAGE_URL = "https://10minutemail.com/";

    @Test(description = "Test N4 - Send Email and check of total cost")
    public void completeGoogleCalculatorAndSendEmail() throws IOException, UnsupportedFlavorException {

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        CloudCalculatorPage cloudCalculatorPage = new StartCloudPage(driver).openStartPage()
                .searchCalculatorInCloud(webDriverWait).chooseCalculator(webDriverWait);
        cloudCalculatorPage.moveToFrame(webDriverWait);

        ComputerEngine testComputerEngine = ComputeEngineCreator.createNewComputerEngine();
        cloudCalculatorPage.setNumberOfInstance(webDriverWait, testComputerEngine);
        cloudCalculatorPage.setMachineType(webDriverWait, testComputerEngine);
        cloudCalculatorPage.completeGpuArea(webDriverWait, testComputerEngine);
        cloudCalculatorPage.setLocalssd(testComputerEngine);
        cloudCalculatorPage.setDatacenterLocation(testComputerEngine);
        cloudCalculatorPage.setCommittedUsage(testComputerEngine);

        EstimateWindowPage estimateWindowPage = cloudCalculatorPage.saveEstimate();
        String costFromCalculator = estimateWindowPage.getEstimatedCost();
        estimateWindowPage.clickEmailButton();

        executor.executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(MAIL_PAGE_URL);
        TenMinutesMailPage tenMinutesMailPage = new TenMinutesMailPage(driver);
        String emailAddress = tenMinutesMailPage.getEmaiAddress(webDriverWait);
        driver.switchTo().window(tabs.get(0));
        cloudCalculatorPage.moveToFrame(webDriverWait);
        estimateWindowPage.sendEmail(driver, emailAddress);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
        driver.switchTo().window(tabs.get(1));
        String totalCostFromEmail = tenMinutesMailPage.getCostFromEmail(webDriverWait);
        Assert.assertTrue(costFromCalculator.contains(totalCostFromEmail), " Cost from calculator <> cost from Email ");
    }
}
