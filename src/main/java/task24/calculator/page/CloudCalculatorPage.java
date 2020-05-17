package task24.calculator.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import task24.calculator.model.ComputerEngine;

import java.util.ArrayList;
import java.util.List;

public class CloudCalculatorPage extends AbstractPage {
    private final String FIRST_FRAME_LOCATOR = "//*[@id='cloud-site']/devsite-iframe/iframe";
    private final String NUMBER_OF_INSTANCES_LOCATOR = "//*[@id='input_58']";
    private final String MACHINE_TYPE_LOCATOR = "//*[@id='select_83']";
    private final String GPU_LOCATOR = "//*[@aria-label='Add GPUs']/*[@class='md-container md-ink-ripple']";
    private final String NUMBER_OF_GPU_LOCATOR = "//*[@placeholder='Number of GPUs']";
    private final String GPU_TYPE_LOCATOR = "//*[@placeholder='GPU type']";
    private final String LOCAL_SSD_LOCATOR = "//*[@id='select_170']";
    private final String DATACENTER_LOCATOR = "//*[@id='select_85']";
    private final String USAGE_LOCATOR = "//*[@id='select_92']";
    private final String SAVE_BUTTON_LOCATOR = "//form[@name='ComputeEngineForm']/descendant::" +
            "button[@class='md-raised md-primary cpc-button md-button md-ink-ripple']";
    private final String RESULT_LIST_TAG = "md-option";
    private String nameOfSecondFrame = "myFrame";

    public CloudCalculatorPage (WebDriver driver) {
        super(driver);
    }

    public void moveToFrame(WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(FIRST_FRAME_LOCATOR)));
        WebElement firstFrame = driver.findElement(By.xpath(FIRST_FRAME_LOCATOR));
        driver.switchTo().frame(firstFrame).switchTo().frame(nameOfSecondFrame);
    }

    public void setNumberOfInstance (WebDriverWait webDriverWait, ComputerEngine computerEngine) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(NUMBER_OF_INSTANCES_LOCATOR)));
        driver.findElement(By.xpath(NUMBER_OF_INSTANCES_LOCATOR)).sendKeys(computerEngine.getNumberOfInstances());
    }

    public void setMachineType(WebDriverWait webDriverWait, ComputerEngine computerEngine) {
        List<WebElement> optionsOfMachineType = driver.findElement(By.xpath(MACHINE_TYPE_LOCATOR))
                .findElements(By.tagName(RESULT_LIST_TAG));
        for (int i = 0; i < optionsOfMachineType.size(); i++) {
            executor.executeScript("arguments[0].click();", optionsOfMachineType.get(i));
            if (driver.findElement(By.xpath(MACHINE_TYPE_LOCATOR)).getText().equals(computerEngine.getMachineType())) {
                i = optionsOfMachineType.size();
            }
        }
    }

    public void completeGpuArea(WebDriverWait webDriverWait, ComputerEngine computerEngine) {
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(GPU_LOCATOR)));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(NUMBER_OF_GPU_LOCATOR)));
        driver.findElement(By.xpath(NUMBER_OF_GPU_LOCATOR)).sendKeys(computerEngine.getNumberOfGpu());
        List<WebElement> optionsOfGpuType = driver.findElement(By.xpath(GPU_TYPE_LOCATOR))
                .findElements(By.tagName(RESULT_LIST_TAG));
        for (int i = 0; i < optionsOfGpuType.size(); i++) {
            executor.executeScript("arguments[0].click();", optionsOfGpuType.get(i));
            if (driver.findElement(By.xpath(GPU_TYPE_LOCATOR)).getText().equals(computerEngine.getGpuType())) {
                i = optionsOfGpuType.size();
            }
        }
    }

    public void setLocalssd(ComputerEngine computerEngine) {
        List<WebElement> optionsOfLocalSsd = driver.findElement(By.xpath(LOCAL_SSD_LOCATOR))
                .findElements(By.tagName(RESULT_LIST_TAG));
        for (int i = 0; i < optionsOfLocalSsd.size(); i++) {
            executor.executeScript("arguments[0].click();", optionsOfLocalSsd.get(i));
            if (driver.findElement(By.xpath(LOCAL_SSD_LOCATOR)).getText().equals(computerEngine.getLocalSsd())) {
                i = optionsOfLocalSsd.size();
            }
        }
    }

    public void setDatacenterLocation(ComputerEngine computerEngine) {
        List<WebElement> optionsOfDatacetnter = driver.findElement(By.xpath(DATACENTER_LOCATOR))
                .findElements(By.tagName(RESULT_LIST_TAG));
        for (int i = 0; i < optionsOfDatacetnter.size(); i++) {
            executor.executeScript("arguments[0].click();", optionsOfDatacetnter.get(i));
            if (driver.findElement(By.xpath(DATACENTER_LOCATOR)).getText().contains(computerEngine.getDatacenterLocation())) {
                i = optionsOfDatacetnter.size();
            }
        }
    }

    public void setCommittedUsage(ComputerEngine committedUsage) {
            List<WebElement> optionsOfUsage = driver.findElement(By.xpath(USAGE_LOCATOR))
                .findElements(By.tagName(RESULT_LIST_TAG));
        for (int i = 0; i < optionsOfUsage.size(); i++) {
            executor.executeScript("arguments[0].click();", optionsOfUsage.get(i));
            if (driver.findElement(By.xpath(USAGE_LOCATOR)).getText().equals(committedUsage.getCommittedUsage())) {
                i = optionsOfUsage.size();
            }
        }
    }

    public EstimateWindowPage saveEstimate() {
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(SAVE_BUTTON_LOCATOR)));
        return new EstimateWindowPage(driver);
    }
}
