package task28;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import task28.calculator.model.ComputerEngine;
import task28.calculator.page.EstimateWindowPage;
import task28.calculator.service.CalculatorService;
import task28.calculator.service.ComputeEngineCreator;
import task28.calculator.service.MailService;

public class WebDriverCalculatorTestFour extends CommonConditions {

  @Test(description = "Test N4 - Send Email and check of total cost")
  public void completeGoogleCalculatorAndSendEmail()
      throws IOException, UnsupportedFlavorException {
    ComputerEngine testEngine = ComputeEngineCreator.createNewComputerEngine();
    EstimateWindowPage estimate = new CalculatorService()
        .writeInCalculator(driver, testEngine);
    String costFromCalculator = estimate.getEstimatedCost();
    MailService mailService = new MailService();
    String emailAddress = mailService.getAddress(estimate);
    String costFromEmail = mailService.getCost(estimate, emailAddress);
    if (costFromEmail.isEmpty()) {
      costFromEmail = "No cost from Email";
    }
    Assert.assertTrue(costFromCalculator.contains(costFromEmail),
        " Cost from calculator <> cost from Email ");
  }
}
