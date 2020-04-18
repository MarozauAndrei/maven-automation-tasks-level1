package task24.test3.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CloudCalculatorPage {
    private final String FIRST_FRAME_LOCATOR = "//*[@id='cloud-site']/devsite-iframe/iframe";
    private final String NUMBER_OF_INSTANCES_LOCATOR = "//*[@id='input_56']";
    private final String MACHINE_TYPE_LOCATOR = "//*[@id='select_81']";
    private final String GPU_LOCATOR = "//*[@aria-label='Add GPUs']/*[@class='md-container md-ink-ripple']";
    private final String NUMBER_OF_GPU_LOCATOR = "//*[@placeholder='Number of GPUs']";
    private final String GPU_TYPE_LOCATOR = "//*[@placeholder='GPU type']";
    private final String LOCAL_SSD_LOCATOR = "//*[@id='select_168']";
    private final String DATACENTER_LOCATOR = "//*[@id='select_83']";
    private final String USAGE_LOCATOR = "//*[@id='select_90']";
    private final String BUTTON_LOCATOR = "//form[@name='ComputeEngineForm']/descendant::" +
            "button[@class='md-raised md-primary cpc-button md-button md-ink-ripple']";
    private WebDriver driver;
    private String nameOfSecondFrame = "myFrame";

    @FindBy(xpath = FIRST_FRAME_LOCATOR)
    WebElement frameElement;

    @FindBy(xpath = NUMBER_OF_INSTANCES_LOCATOR)
    WebElement instancesElement;

    @FindBy(xpath = MACHINE_TYPE_LOCATOR)
    WebElement machineType;

    @FindBy(xpath = NUMBER_OF_GPU_LOCATOR)
    WebElement numberOfGpu;

    @FindBy(xpath = GPU_TYPE_LOCATOR)
    WebElement gpuType;

    @FindBy(xpath = LOCAL_SSD_LOCATOR)
    WebElement localSsd;

    @FindBy(xpath = DATACENTER_LOCATOR)
    WebElement datacenter;

    @FindBy(xpath = USAGE_LOCATOR)
    WebElement commitedUsage;

    public CloudCalculatorPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public EstimateWindowPage completeCalculatorForm() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(FIRST_FRAME_LOCATOR)));
        driver.switchTo().frame(frameElement).switchTo().frame(nameOfSecondFrame);

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(NUMBER_OF_INSTANCES_LOCATOR)));
        instancesElement.sendKeys("4");

        List<WebElement> optionsOfMachineType = machineType.findElements(By.tagName("md-option"));

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", optionsOfMachineType.get(6));

        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(GPU_LOCATOR)));

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(NUMBER_OF_GPU_LOCATOR)));
        numberOfGpu.sendKeys("1");

        List<WebElement> optionsOfGpuType = gpuType.findElements(By.tagName("md-option"));
        executor.executeScript("arguments[0].click();", optionsOfGpuType.get(3));

        List<WebElement> optionsOfLocalSsd = localSsd.findElements(By.tagName("md-option"));
        executor.executeScript("arguments[0].click();", optionsOfLocalSsd.get(2));

        List<WebElement> optionsOfDatacetnter = datacenter.findElements(By.tagName("md-option"));
        executor.executeScript("arguments[0].click();", optionsOfDatacetnter.get(8));

        List<WebElement> optionsOfUsage = commitedUsage.findElements(By.tagName("md-option"));
        executor.executeScript("arguments[0].click();", optionsOfUsage.get(1));

        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(BUTTON_LOCATOR)));

        return new EstimateWindowPage(driver);
    }
}
