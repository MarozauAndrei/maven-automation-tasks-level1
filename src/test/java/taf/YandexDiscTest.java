package taf;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taf.pages.*;

public class YandexDiscTest {

  private int webdriverTimeout = 10;
  private String validUserLogin = "marozauandrei";
  private String invalidLogin = "maarozaauaandrei";
  private String validUserPassword = "autotest";
  private String invalidPassword = "auto.test";
  private String directoryName = String.format("name-%d", new Date().getTime());
  private String fileName = "file" + directoryName;
  private String textToFile = "Hello world!";
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
    List<WebElement> mistakeField = new YandexDiscStartPage(driver)
        .openPage()
        .clickLogInButton(wait)
        .inputLogin(wait, invalidLogin)
        .getMistakeField(wait);
    Assert.assertFalse(mistakeField.isEmpty(), " Mistake in login is not visible ");
  }

  @Test(priority = 1, description = "Test №1 - Negative password in yandex disc ")
  public void checkNegativePassword() {
    LogInYandexDiscPage loginPage = new YandexDiscStartPage(driver)
        .openPage()
        .clickLogInButton(wait);
    loginPage.inputLogin(wait, validUserLogin)
        .inputPassword(wait, invalidPassword);
    Assert.assertFalse(loginPage.getMistakeField(wait).isEmpty(),
        " Mistake in password is not visible ");
  }

  @Test(priority = 2, description = "Test №2 - Positive login & password in yandex disc ")
  public void checkPositiveLoginPassword() {
    YandexDiscPage discPage = new YandexDiscStartPage(driver)
        .openPage()
        .clickLogInButton(wait)
        .inputLogin(wait, validUserLogin)
        .inputPassword(wait, validUserPassword);
    Assert.assertTrue(discPage.getTitle(wait).isEnabled(), " Login failed! ");
    discPage.clickUserManageButton(wait)
        .clickQuitUserButton(wait);
  }

  @Test(priority = 3, description = "Test №3 - Check main menu ")
  public void checkMainMenu() {
    YandexDiscPage discPage = new YandexDiscStartPage(driver)
        .openPage()
        .clickLogInButton(wait)
        .inputLogin(wait, validUserLogin)
        .inputPassword(wait, validUserPassword);
    SoftAssert softAssert = new SoftAssert();
    for (String key : checkingMap.keySet()) {
      String title = discPage.openMainMenuSection(wait, key)
          .getTitleMainMenuSection(wait);
      softAssert.assertTrue(title.contains(checkingMap.get(key)), "MISTAKE in - " + key);
    }
    softAssert.assertAll();
    discPage.clickUserManageButton(wait)
        .clickQuitUserButton(wait);
  }

  @Test(priority = 4, description = "Test №4 - Create new directory in Files ")
  public void createAndCheckNewDirectory() {
    new YandexDiscStartPage(driver)
        .openPage()
        .clickLogInButton(wait)
        .inputLogin(wait, validUserLogin)
        .inputPassword(wait, validUserPassword);
    WebElement directoryTitle = new MainMenuSectionFilePage(driver)
        .contextClickFileSection(wait)
        .createNewDirectory(wait)
        .inputDirectoryName(wait, directoryName)
        .clickSaveDirectoryButton()
        .openDirectory(wait, directoryName)
        .getDirectoryTitle(wait);
    Assert.assertEquals(directoryTitle.getAttribute("title"), directoryName,
        " Directory was not created! ");
  }

  @Test(dependsOnMethods = {
      "createAndCheckNewDirectory"}, description = "Test №5 - Create new document in directory ")
  public void createAndCheckNewDocument() {
    FileDirectoryPage fileDirectory = new FileDirectoryPage(driver);
    DocumentWordPage docWord = fileDirectory
        .contextClickDirectoryArea(wait)
        .createNewDocument(wait)
        .goToDocumentTab()
        .goToDocumentFrame(wait)
        .clickButtonFile(wait)
        .clickButtonCloseMenu(wait)
        .clickButtonFile(wait)
        .clickButtonRename(wait)
        .inputFileName(fileName)
        .writeText(wait, textToFile)
        .clickButtonFile(wait)
        .clickButtonCloseDocument(wait)
        .leaveDocumentTab();

    List<WebElement> document = fileDirectory
        .returnToFileSection(wait)
        .openDirectory(wait, directoryName)
        .getElementNameDocument(wait, fileName);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertFalse(document.isEmpty(), " New document is not visible! ");

    String textFromDocument = fileDirectory
        .openDocument(wait, fileName)
        .goToDocumentTab()
        .goToDocumentFrame(wait)
        .getTextFromDocument(wait);
    softAssert.assertEquals(textFromDocument, textToFile, " Text in document is wrong! ");
    softAssert.assertAll();

    docWord
        .clickButtonFile(wait)
        .clickButtonCloseDocument(wait)
        .leaveDocumentTab();
  }

  @Test(dependsOnMethods = {"createAndCheckNewDirectory",
      "createAndCheckNewDocument"}, description = "Test №6 - Delete document")
  public void checkOfDeleteDocument() {
    FileDirectoryPage fileDirectory = new FileDirectoryPage(driver);
    List<WebElement> document = fileDirectory
        .contextClickDocument(wait, fileName)
        .deleteDocument(wait)
        .returnToFileSection(wait)
        .openDirectory(wait, directoryName)
        .getElementNameDocument(wait, fileName);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(document.isEmpty(), " Document not deleted from directory ");

    List<WebElement> documentFromBin = fileDirectory
        .returnToFileSection(wait)
        .openBin(wait)
        .getDocumentFromBin(wait, fileName);
    softAssert.assertFalse(documentFromBin.isEmpty(), " Document not absent in bin ");
    softAssert.assertAll();
  }

  @Test(dependsOnMethods = {
      "checkOfDeleteDocument"}, description = "Test №6 - Delete document from bin")
  public void checkOfCleaningBin() {
    List<WebElement> documentFromBin = new FileBinPage(driver)
        .clickButtonClearBin(wait)
        .clickButtonClear(wait)
        .getDocumentFromBin(wait, fileName);
    Assert.assertTrue(documentFromBin.isEmpty(), " Document not deleted from bin ");
  }

  @AfterClass(alwaysRun = true)
  public void browserTurnOff() {
//    driver.quit();
    driver = null;
  }
}
