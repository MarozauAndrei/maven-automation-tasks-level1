package task28.calculator.service;

import org.openqa.selenium.WebDriver;
import task28.calculator.model.ComputerEngine;
import task28.calculator.page.EstimateWindowPage;
import task28.calculator.page.StartCloudPage;

public class CalculatorService {

  public EstimateWindowPage writeInCalculator(WebDriver driver, ComputerEngine testEngine) {
    return new StartCloudPage(driver)
        .openStartPage()
        .clickSearchButton()
        .inputSearchString()
        .selectCalculator()
        .inputNumberOfInstance(testEngine)
        .jsSelectVariable(testEngine.getMachineType())
        .jsClickGpuCheckbox()
        .inputNumberOfGpu(testEngine)
        .jsSelectVariable(testEngine.getGpuType())
        .jsSelectVariable(testEngine.getLocalSsd())
        .jsSelectVariable(testEngine.getDatacenter())
        .jsSelectVariable(testEngine.getCommittedUsage())
        .jsSaveEstimate();
  }
}
