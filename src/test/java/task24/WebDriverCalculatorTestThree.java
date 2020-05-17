package task24;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import task24.calculator.model.ComputerEngine;
import task24.calculator.page.CloudCalculatorPage;
import task24.calculator.page.EstimateWindowPage;
import task24.calculator.page.StartCloudPage;
import task24.calculator.service.ComputeEngineCreator;

import java.util.Arrays;
import java.util.List;

public class WebDriverCalculatorTestThree extends CommonConditions{
    private final List<String> expectedResults = Arrays.asList(
            "VM class: regular",
            "Instance type: n1-standard-8",
            "Region: Frankfurt",
            "Total available local SSD space 2x375 GiB",
            "Commitment term: 1 Year",
            "Estimated Component Cost: USD 1,082.77 per 1 month"
    );

    @Test(description = "Test N3 - Check of completing google calculator")
    public void completeAndCheckGoogleCalculator() {
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
        List<WebElement> optionsOfResult = estimateWindowPage.getListOfEstimateResults(webDriverWait);
        optionsOfResult.remove(0);

        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < optionsOfResult.size(); i++) {
            softAssert.assertEquals(optionsOfResult.get(i).getText(), expectedResults.get(i),
                    optionsOfResult.get(i).getText() + " - is wrong field   ");
            }
        softAssert.assertAll();
    }
}
