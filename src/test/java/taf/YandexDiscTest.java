package taf;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

public class YandexDiscTest {

  private int webdriverTimeout = 15;
  private int indexDirectoryPage = 0;
  private int indexWordPage = 1;
  private String validUserLogin = "marozauandrei";
  private String invalidLogin = "maarozaauaandrei";
  private String validUserPassword = "autotest";
  private String invalidPassword = "auto.test";
  private String directoryName = String.format("name-%d", new Date().getTime());
  private String fileName = "file" + directoryName;
  private String textToFile = "Hello world!";
  private String signOfCleanBinLocator = "//*[contains(text(),'Корзина успешно очищена.')]";
  private WebDriver driver;
  private WebDriverWait wait;
  private static Map<String, String> checkingMap = new HashMap<>();

  static {
    checkingMap.put("Последние", "Последние");
    checkingMap.put("Файлы", "Файлы");
    checkingMap.put("Фото", "фотографии");
    checkingMap.put("Общий доступ", "ссылки");
    checkingMap.put("История", "История");
    checkingMap.put("Архив", "Архив");
    checkingMap.put("Корзина", "Корзина");
  }

  @BeforeClass(alwaysRun = true)
  public void browserSetUp() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, webdriverTimeout);
  }

  @Test(priority = 0, description = "Test №0 - Negative login in yandex disc ")
  public void checkNegativeLogin() {
    boolean negativeLoginResult = new YandexDiscStartPage(driver).openPage()
        .clickLogInButton(wait)
        .inputLogin(wait, invalidLogin)
        .checkInputMistake(wait);
    Assert.assertTrue(negativeLoginResult, " Mistake in login is not visible ");
  }

  @Test(priority = 1, description = "Test №1 - Negative password in yandex disc ")
  public void checkNegativePassword() {
    boolean negativePasswordResult = new YandexDiscStartPage(driver).openPage()
        .clickLogInButton(wait)
        .inputLogin(wait, validUserLogin)
        .inputPassword(wait, invalidPassword)
        .checkInputMistake(wait);
    Assert.assertTrue(negativePasswordResult, " Mistake in password is not visible ");
  }

  @Test(priority = 2, description = "Test №2 - Positive login & password in yandex disc ")
  public void checkPositiveLoginPassword() {
    new YandexDiscStartPage(driver).openPage()
        .clickLogInButton(wait)
        .inputLogin(wait, validUserLogin)
        .inputPassword(wait, validUserPassword);
    YandexDiscPage discPage = new YandexDiscPage(driver);
    Assert.assertTrue(discPage.getTitle(wait).isEnabled(), " Login failed! ");
    discPage.closeUserSession(wait);
  }

  @Test(priority = 3, description = "Test №3 - Check main menu ")
  public void checkMainMenu() {
    new YandexDiscStartPage(driver).openPage()
        .clickLogInButton(wait)
        .inputLogin(wait, validUserLogin)
        .inputPassword(wait, validUserPassword);
    YandexDiscPage discPage = new YandexDiscPage(driver);
    SoftAssert softAssert = new SoftAssert();
    for (String key : checkingMap.keySet()) {
      String title = discPage.openMainMenuSection(wait, key)
          .getTitleMainMenuSection(wait);
      softAssert.assertTrue(title.contains(checkingMap.get(key)), "MISTAKE in - " + key);
    }
    softAssert.assertAll();
    discPage.closeUserSession(wait);
  }

  @Test(priority = 4, description = "Test №4 - Create new directory in Files ")
  public void createAndCheckNewDirectory() {
    new YandexDiscStartPage(driver).openPage()
        .clickLogInButton(wait)
        .inputLogin(wait, validUserLogin)
        .inputPassword(wait, validUserPassword);
    boolean createNewDirectoryResult = new MainMenuSectionFilePage(driver)
        .createNewDirectory(wait, directoryName)
        .openNewDirectory(wait, directoryName).checkNewDirectory(wait);
    Assert.assertTrue(createNewDirectoryResult, " Directory was not created! ");
  }

  @Test(dependsOnMethods = {
      "createAndCheckNewDirectory"}, description = "Test №5 - Create new document in directory ")
  public void createAndCheckNewDocument() {
    FileDirectoryPage fileDirectory = new FileDirectoryPage(driver);
    DocumentWordPage docWord = fileDirectory.createNewDocument(wait);
    ArrayList<String> tabsBeforeChecking = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(tabsBeforeChecking.get(indexWordPage));
    docWord.goToDocumentFrame(wait)
        .reNameDocument(wait, fileName)
        .writeText(wait, textToFile)
        .closeDocument(wait);
    driver.switchTo().window(tabsBeforeChecking.get(indexDirectoryPage));
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertFalse(fileDirectory.checkExistOfDocument(fileName),
        " New document is not visible! ");
    fileDirectory.openDocument(wait, fileName);
    ArrayList<String> tabsAfterChecking = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(tabsAfterChecking.get(indexWordPage));
    String textFromDocument = docWord.goToDocumentFrame(wait)
        .getTextFromDocument(wait);
    softAssert.assertEquals(textFromDocument, textToFile, " Text in document is wrong! ");
    softAssert.assertAll();
    docWord.closeDocument(wait);
    driver.switchTo().window(tabsAfterChecking.get(indexDirectoryPage));
  }

  @Test(dependsOnMethods = {"createAndCheckNewDirectory",
      "createAndCheckNewDocument"}, description = "Test №6 - Delete document")
  public void checkOfDeleteDocument() {
    FileDirectoryPage directory = new FileDirectoryPage(driver);
    directory.deleteDocument(wait, fileName);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(directory.checkExistOfDocument(fileName),
        " Document not deleted from directory ");
    FileBinPage bin = directory.returnToFileSection(wait).openBin(wait);
    softAssert.assertFalse(bin.checkExistOfDocumentInBin(wait, fileName),
        " Document not absent in bin ");
    softAssert.assertAll();
  }

  @Test(dependsOnMethods = {
      "checkOfDeleteDocument"}, description = "Test №6 - Delete document from bin")
  public void checkOfCleaningBin() {

    FileBinPage fileBinPage = new FileBinPage(driver);
    fileBinPage.cleanTheBin(wait);
    wait.until(ExpectedConditions
        .visibilityOfAllElementsLocatedBy(By.xpath(signOfCleanBinLocator)));
    Assert.assertTrue(fileBinPage.checkExistOfDocumentInBin(wait, fileName),
        " Document not deleted from bin ");
  }

  @AfterClass(alwaysRun = true)
  public void browserTurnOff() {
    driver.quit();
    driver = null;
  }
}
