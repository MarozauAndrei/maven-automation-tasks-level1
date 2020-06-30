package task28;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import task28.calculator.model.ComputerEngine;
import task28.calculator.page.EstimateWindowPage;
import task28.calculator.page.StartCloudPage;
import task28.calculator.service.ComputeEngineCreator;

public class WebDriverCalculatorTestFour extends CommonConditions {

  @Test(description = "Test N4 - Send Email and check of total cost")
  public void completeGoogleCalculatorAndSendEmail()
      throws IOException, UnsupportedFlavorException {
    ComputerEngine testEngine = ComputeEngineCreator.createNewComputerEngine();
    EstimateWindowPage estimate = new StartCloudPage(driver)
        .openStartPage()
        .clickSearchButton(wait)
        .inputSearchString()
        .selectCalculator(wait)
        .inputNumberOfInstance(wait, testEngine)
        .jsSelectVariable(testEngine.getMachineType())
        .jsClickGpuCheckbox()
        .inputNumberOfGpu(wait, testEngine)
        .jsSelectVariable(testEngine.getGpuType())
        .jsSelectVariable(testEngine.getLocalSsd())
        .jsSelectVariable(testEngine.getDatacenter())
        .jsSelectVariable(testEngine.getCommittedUsage())
        .jsSaveEstimate();
    String costFromCalculator = estimate.getEstimatedCost();
    String emailAddress = estimate
        .jsClickEmailButton(wait)
        .getEmaiAddress(wait);
    String costFromEmail = estimate
        .jsSendEmail(wait, emailAddress)
        .openReceiveMail(wait)
        .getCostFromEmail(wait);
    if (costFromEmail.isEmpty()) {
      costFromEmail = "No cost from Email";
    }
    Assert.assertTrue(costFromCalculator.contains(costFromEmail),
        " Cost from calculator <> cost from Email ");
  }
}
