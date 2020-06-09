package main.java.task24.calculator.page;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CloudCalculatorPage {
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
        private WebDriver driver;
    private String nameOfSecondFrame = "myFrame";

    public CloudCalculatorPage (WebDriver driver) {
        this.driver = driver;
    }

    public void moveToFrame(WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(FIRST_FRAME_LOCATOR)));
        WebElement firstFrame = driver.findElement(By.xpath(FIRST_FRAME_LOCATOR));
        driver.switchTo().frame(firstFrame).switchTo().frame(nameOfSecondFrame);
    }

    public void setNumberOfInstance (WebDriverWait webDriverWait) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(NUMBER_OF_INSTANCES_LOCATOR)));
        driver.findElement(By.xpath(NUMBER_OF_INSTANCES_LOCATOR)).sendKeys("4");
    }

    public void setMachineType(JavascriptExecutor executor) {
        List<WebElement> optionsOfMachineType = driver.findElement(By.xpath(MACHINE_TYPE_LOCATOR))
                .findElements(By.tagName(RESULT_LIST_TAG));
        executor.executeScript("arguments[0].click();", optionsOfMachineType.get(6));
    }

    public void completeGpuArea(WebDriverWait webDriverWait, JavascriptExecutor executor) {
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(GPU_LOCATOR)));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(NUMBER_OF_GPU_LOCATOR)));
        driver.findElement(By.xpath(NUMBER_OF_GPU_LOCATOR)).sendKeys("1");
        List<WebElement> optionsOfGpuType = driver.findElement(By.xpath(GPU_TYPE_LOCATOR))
                .findElements(By.tagName(RESULT_LIST_TAG));
        executor.executeScript("arguments[0].click();", optionsOfGpuType.get(3));
    }

    public void setLocalssd(JavascriptExecutor executor) {
        List<WebElement> optionsOfLocalSsd = driver.findElement(By.xpath(LOCAL_SSD_LOCATOR))
                .findElements(By.tagName(RESULT_LIST_TAG));
        executor.executeScript("arguments[0].click();", optionsOfLocalSsd.get(2));
    }

    public void setDatacenterLocation(JavascriptExecutor executor) {
        List<WebElement> optionsOfDatacetnter = driver.findElement(By.xpath(DATACENTER_LOCATOR))
                .findElements(By.tagName(RESULT_LIST_TAG));
        executor.executeScript("arguments[0].click();", optionsOfDatacetnter.get(9));
    }

    public void setCommittedUsage(JavascriptExecutor executor) {
            List<WebElement> optionsOfUsage = driver.findElement(By.xpath(USAGE_LOCATOR))
                .findElements(By.tagName(RESULT_LIST_TAG));
        executor.executeScript("arguments[0].click();", optionsOfUsage.get(1));
    }

    public task24.calculator.page.EstimateWindowPage saveEstimate(JavascriptExecutor executor) {
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(SAVE_BUTTON_LOCATOR)));
        return new task24.calculator.page.EstimateWindowPage(driver);
    }
}
