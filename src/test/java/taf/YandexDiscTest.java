package taf;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import taf.pages.DocumentWordPage;
import taf.pages.FileBinPage;
import taf.pages.FileDirectoryPage;
import taf.pages.LogInYandexDiscPage;
import taf.pages.MainMenuSectionFilePage;
import taf.pages.YandexDiscPage;
import taf.pages.YandexDiscStartPage;

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
  private Map<String, String> checkingMap = new HashMap<String, String>() {{
    put("Последние", "Последние");
    put("Файлы", "Файлы");
    put("Фото", "фотографии");
    put("Общий доступ", "ссылки");
    put("История", "История");
    put("Архив", "Архив");
    put("Корзина", "Корзина");
  }};

  @BeforeClass(alwaysRun = true)
  public void browserSetUp() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, webdriverTimeout);
  }

  @Test(priority = 0, description = "Test №0 - Negative login in yandex disc ")
  public void checkNegativeLogin() {
    boolean isMistakeEmpty = new YandexDiscStartPage(driver)
        .openPage()
        .clickLogInButton(wait)
        .inputLogin(wait, invalidLogin)
        .isMistakeEmpty(wait);
    Assert.assertFalse(isMistakeEmpty, " Mistake in login is not visible ");
  }

  @Test(priority = 1, description = "Test №1 - Negative password in yandex disc ")
  public void checkNegativePassword() {
    LogInYandexDiscPage loginPage = new YandexDiscStartPage(driver)
        .openPage()
        .clickLogInButton(wait);
    loginPage.inputLogin(wait, validUserLogin)
        .inputPassword(wait, invalidPassword);
    Assert.assertFalse(loginPage.isMistakeEmpty(wait),
        " Mistake in password is not visible ");
  }

  @Test(priority = 2, description = "Test №2 - Positive login & password in yandex disc ")
  public void checkPositiveLoginPassword() {
    YandexDiscPage discPage = new YandexDiscStartPage(driver)
        .openPage()
        .clickLogInButton(wait)
        .inputLogin(wait, validUserLogin)
        .inputPassword(wait, validUserPassword);
    boolean isPageTitlePresent = discPage.pageTitleIsEnabled(wait);
    discPage.clickUserManageButton(wait)
        .clickQuitUserButton(wait);
    Assert.assertTrue(isPageTitlePresent, " Login failed! ");
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
    discPage.clickUserManageButton(wait)
        .clickQuitUserButton(wait);
    softAssert.assertAll();
  }

  @Test(priority = 4, description = "Test №4 - Create new directory in Files ")
  public void createAndCheckNewDirectory() {
    new YandexDiscStartPage(driver)
        .openPage()
        .clickLogInButton(wait)
        .inputLogin(wait, validUserLogin)
        .inputPassword(wait, validUserPassword);
    String directoryTitle = new MainMenuSectionFilePage(driver)
        .contextClickFileSection(wait)
        .createNewDirectory(wait)
        .inputDirectoryName(wait, directoryName)
        .clickSaveDirectoryButton()
        .openDirectory(wait, directoryName)
        .getDirectoryTitle(wait);
    Assert.assertEquals(directoryTitle, directoryName, " Directory was not created! ");
  }

  @Test(dependsOnMethods = {
      "createAndCheckNewDirectory"}, description = "Test №5 - Create new document in directory ")
  public void createAndCheckNewDocument() {
    FileDirectoryPage fileDirectory = new FileDirectoryPage(driver);
    DocumentWordPage docWord = fileDirectory
        .contextClickDirectoryArea(wait)
        .createNewDocument(wait)
        .clickButtonFile(wait)
        .clickButtonCloseMenu(wait)
        .clickButtonFile(wait)
        .clickButtonRename(wait)
        .inputFileName(fileName)
        .inputText(wait, textToFile)
        .clickButtonFile(wait)
        .clickButtonCloseDocument(wait);
    boolean isDocumentEmpty = fileDirectory
        .returnToFileSection(wait)
        .openDirectory(wait, directoryName)
        .isElementEmpty(wait, fileName);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertFalse(isDocumentEmpty, " New document is not visible! ");
    String textFromDocument = fileDirectory
        .openDocument(wait, fileName)
        .getTextFromDocument(wait);
    softAssert.assertEquals(textFromDocument, textToFile, " Text in document is wrong! ");
    docWord
        .clickButtonFile(wait)
        .clickButtonCloseDocument(wait);
    softAssert.assertAll();
  }

  @Test(dependsOnMethods = {"createAndCheckNewDirectory",
      "createAndCheckNewDocument"}, description = "Test №6 - Delete document")
  public void checkOfDeleteDocument() {
    FileDirectoryPage fileDirectory = new FileDirectoryPage(driver);
    boolean isDocumentEmpty = fileDirectory
        .contextClickDocument(wait, fileName)
        .deleteDocument(wait)
        .returnToFileSection(wait)
        .openDirectory(wait, directoryName)
        .isElementEmpty(wait, fileName);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(isDocumentEmpty, " Document not deleted from directory ");
    boolean isDocumentFromBinEmpty = fileDirectory
        .returnToFileSection(wait)
        .openBin(wait)
        .isDocumentFromBinEmpty(wait, fileName);
    softAssert.assertFalse(isDocumentFromBinEmpty, " Document not absent in bin ");
    softAssert.assertAll();
  }

  @Test(dependsOnMethods = {"createAndCheckNewDirectory", "createAndCheckNewDocument",
      "checkOfDeleteDocument"}, description = "Test №6 - Delete document from bin")
  public void checkOfCleaningBin() {
    boolean isDocumentFromBinEmpty = new FileBinPage(driver)
        .clickButtonClearBin(wait)
        .clickButtonClear(wait)
        .isDocumentFromBinEmpty(wait, fileName);
    Assert.assertTrue(isDocumentFromBinEmpty, " Document not deleted from bin ");
  }

  @AfterClass(alwaysRun = true)
  public void browserTurnOff() {
    driver.quit();
    driver = null;
  }
}
