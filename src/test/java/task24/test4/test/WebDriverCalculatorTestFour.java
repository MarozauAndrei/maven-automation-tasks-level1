package task24.test4.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import task24.test4.page.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

public class WebDriverCalculatorTestFour {
    private final String MAIL_PAGE_URL = "https://10minutemail.com/";
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        driver = new ChromeDriver();
    }

    @Test(description = "Test N4 - Send Email and check of total cost")
    public void completeAndCheckGoogleCalculator() throws IOException, UnsupportedFlavorException {

        StartCloudPageNew startCloudPageNew = new StartCloudPageNew(driver);
        SearchResultPageNew searchResultPageNew = startCloudPageNew.openStartPageNew().searchCalculatorInCloudNew();
        CloudCalculatorPageNew cloudCalculatorPageNew = searchResultPageNew.chooseCalculatorNew();
        EstimateWindowPageNew estimateWindowPageNew = cloudCalculatorPageNew.completeCalculatorFormNew();
        String costFromCalculator = estimateWindowPageNew.getCostFromCalculator();
        estimateWindowPageNew.clickEmailButton();

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(MAIL_PAGE_URL);

        TenMinutesMailPage tenMinutesMailPage = new TenMinutesMailPage(driver);
        String emailAddress = tenMinutesMailPage.getEmaiAddress();

        driver.switchTo().window(tabs.get(0));

        WebElement frame = driver.findElement(By.xpath("//*[@id='cloud-site']/devsite-iframe/iframe"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }

        driver.switchTo().frame(frame).switchTo().frame("myFrame");

        estimateWindowPageNew.sendEmail(driver, emailAddress);

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }

        driver.switchTo().window(tabs.get(1));

        String totalCostFromEmail = tenMinutesMailPage.getCostFromEmail();

        Assert.assertTrue(costFromCalculator.contains(totalCostFromEmail), " Cost from calculator <> cost from Email ");

    }
    @AfterMethod(alwaysRun = true)
    public void browserTurnOff() {
        driver.quit();
        driver = null;
    }
}
