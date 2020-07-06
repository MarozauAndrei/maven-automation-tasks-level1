package task28;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import task28.calculator.model.ComputerEngine;
import task28.calculator.service.CalculatorService;
import task28.calculator.service.ComputeEngineCreator;

public class WebDriverCalculatorTestThree extends CommonConditions {

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
    ComputerEngine testEngine = ComputeEngineCreator.createNewComputerEngine();
    List<String> estimateResult = new CalculatorService()
        .writeInCalculator(driver, testEngine)
        .getListOfEstimateResults();
    estimateResult.remove(0);
    SoftAssert softAssert = new SoftAssert();
    for (int i = 1; i < estimateResult.size(); i++) {
      softAssert.assertEquals(estimateResult.get(i), expectedResults.get(i),
          estimateResult.get(i) + " - is wrong field   ");
    }
    softAssert.assertAll();
  }
}
