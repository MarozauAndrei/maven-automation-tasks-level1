package taf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taf.pages.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YandexDiscTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private int webdriverTimeout = 15;
    private String validUserLogin = "marozauandrei";
    private String invalidLogin = "maarozaauaandrei";
    private String validUserPassword = "autotest";
    private String invalidPassword = "auto.test";
    private List<String> checkingElements = Arrays.asList(
            "Последние",
            "Фото",
            "Общий доступ",
            "История",
            "Архив",
            "Корзина",
            "Файлы"
    );
    private String directoryName = String.format("name-%d-%d", (int) (Math.random() * 100), (int) (Math.random() * 100));
    private String fileName = "file" + directoryName;
    private String textToFile = "Hello world!";
    private String signOfCleanBinLocator = "//*[contains(text(),'Корзина успешно очищена.')]";

    @BeforeClass(alwaysRun = true)
    public void browserSetUp() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, webdriverTimeout);
    }

    @Test(priority = 0, description = "Test №0 - Negative login in yandex disc ")
    public void checkNegativeLogin() {
        YandexDiscStartPage yandexDiscStartPage = new YandexDiscStartPage(driver).openPage();
        LogInYandexDiscPage logInYandexDiscPage = yandexDiscStartPage.clickLogInButton(webDriverWait);
        logInYandexDiscPage.inputLogin(webDriverWait, invalidLogin);
        Assert.assertTrue(logInYandexDiscPage.checkInputMistake(webDriverWait), " Mistake in login is not visible ");
        logInYandexDiscPage.returnToLoginField(webDriverWait);
    }

    @Test(priority = 1, description = "Test №1 - Negative password in yandex disc ")
    public void checkNegativePassword() {
        YandexDiscStartPage yandexDiscStartPage = new YandexDiscStartPage(driver).openPage();
        LogInYandexDiscPage logInYandexDiscPage = yandexDiscStartPage.clickLogInButton(webDriverWait);
        logInYandexDiscPage.inputLogin(webDriverWait, validUserLogin);
        logInYandexDiscPage.inputPassword(webDriverWait, invalidPassword);
        Assert.assertTrue(logInYandexDiscPage.checkInputMistake(webDriverWait), " Mistake in password is not visible ");
    }

    @Test(priority = 2, description = "Test №2 - Positive login & password in yandex disc ")
    public void checkPositiveLoginPassword() {
        LogInYandexDiscPage logInYandexDiscPage = new LogInYandexDiscPage(driver);;
        YandexDiscPage yandexDiscPage = logInYandexDiscPage.inputPassword(webDriverWait, validUserPassword);
        Assert.assertTrue(yandexDiscPage.getTitle(webDriverWait).isEnabled(), " Login in yandex disk failed! ");
    }

    @Test(priority = 3, description = "Test №3 - Check main menu ")
    public void checkMainMenu() {
        YandexDiscPage yandexDiscPage = new YandexDiscPage(driver);
        SoftAssert softAssert = new SoftAssert();
        for (String checkingElement : checkingElements) {
            yandexDiscPage.openMainMenuSection(webDriverWait, checkingElement);
            String titleOfMenuSection = yandexDiscPage.getTitleMainMenuSection(webDriverWait);
            switch (checkingElement) {
                case "Фото": {
                    softAssert.assertTrue(titleOfMenuSection.contains("фотографии"), "MISTAKE in - " + checkingElement);
                }
                break;
                case "Общий доступ": {
                    softAssert.assertTrue(titleOfMenuSection.contains("ссылки"), "MISTAKE in - " + checkingElement);
                }
                break;
                default: {
                    softAssert.assertTrue(titleOfMenuSection.contains(checkingElement), "MISTAKE in - " + checkingElement);
                }
            }
        }
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Test №4 - Create new directory in Files ")
    public void createAndCheckNewDirectory() {
        MainMenuSectionFilePage mainMenuSectionFilePage = new MainMenuSectionFilePage(driver);
        mainMenuSectionFilePage.createNewDirectory(webDriverWait, directoryName);
        FileDirectoryPage fileDirectoryPage = mainMenuSectionFilePage.openNewDirectory(webDriverWait, directoryName);
        Assert.assertTrue(fileDirectoryPage.checkNewDirectory(webDriverWait));
    }

    @Test(dependsOnMethods = {"createAndCheckNewDirectory"}, description = "Test №5 - Create new document in directory ")
    public void createAndCheckNewDocument() {
        FileDirectoryPage fileDirectoryPage = new FileDirectoryPage(driver);
        DocumentWordPage documentWordPage = fileDirectoryPage.createNewDocument(webDriverWait);
        ArrayList<String> tabsBeforeChecking = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabsBeforeChecking.get(1));
        documentWordPage.goToDocumentFrame(webDriverWait);
        documentWordPage.reNameDocument(webDriverWait, fileName);
        documentWordPage.writeText(webDriverWait, textToFile);
        documentWordPage.closeDocument(webDriverWait);
        driver.switchTo().window(tabsBeforeChecking.get(0));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(fileDirectoryPage.checkExistOfDocument(webDriverWait, fileName), " New document is not visible! ");
        fileDirectoryPage.openDocument(webDriverWait, fileName);
        ArrayList<String> tabsAfterChecking = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabsAfterChecking.get(1));
        documentWordPage.goToDocumentFrame(webDriverWait);
        String textFromDocument = documentWordPage.getTextFromDocument(webDriverWait);
        softAssert.assertEquals(textFromDocument, textToFile, " Text in document is wrong! ");
        softAssert.assertAll();
        documentWordPage.closeDocument(webDriverWait);
        driver.switchTo().window(tabsAfterChecking.get(0));
    }

    @Test(dependsOnMethods = {"createAndCheckNewDocument"}, description = "Test №6 - Delete document")
    public void checkOfDeleteDocument() throws InterruptedException {
        FileDirectoryPage fileDirectoryPage = new FileDirectoryPage(driver);
        fileDirectoryPage.deleteDocument(webDriverWait,fileName);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(fileDirectoryPage.checkExistOfDocument(webDriverWait, fileName), " Document not deleted from directory ");
        MainMenuSectionFilePage mainMenuSectionFilePage = fileDirectoryPage.returnToFileSection(webDriverWait);
        FileBinPage fileBinPage = mainMenuSectionFilePage.openBin(webDriverWait);
        softAssert.assertTrue(fileBinPage.checkExistOfDocumentInBin(webDriverWait, fileName), " Document not absent in bin ");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"checkOfDeleteDocument"}, description = "Test №6 - Delete document from bin")
    public void checkOfCleaningBin() {
        FileBinPage fileBinPage = new FileBinPage(driver);
        fileBinPage.cleanTheBin(webDriverWait);
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(signOfCleanBinLocator)));
        Assert.assertFalse(fileBinPage.checkExistOfDocumentInBin(webDriverWait, fileName), " Document not deleted from bin ");
    }

    @AfterClass(alwaysRun = true)
    public void browserTurnOff() {
        driver.quit();
        driver = null;
    }
}
