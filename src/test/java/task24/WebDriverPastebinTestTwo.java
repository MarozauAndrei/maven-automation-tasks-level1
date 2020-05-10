package task24;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import task24.pastebin.page.PastebinHomePage;
import task24.pastebin.page.PastebinPastePage;

public class WebDriverPastebinTestTwo {
    private WebDriver driver;
    private int webDriverTimeOut = 10;
    private String textOfCode = "git config --global user.name  \"New Sheriff in Town\"" + "\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")" + "\n" +
            "git push origin master --force";
    private String textOfSyntax = "Bash";
    private String textOfExpiration = "10 Minutes";
    private String textOfName = "how to gain dominance among developers";

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        driver = new ChromeDriver();
    }

    @Test(description = "Test N2 - Check of creating new paste in pastebin.com and content of new page")
    public void createAndCheckNewPaste() {
        PastebinHomePage pastebinHomePage = new PastebinHomePage(driver).openPage();
        WebDriverWait webDriverWait = new WebDriverWait(driver, webDriverTimeOut);
        pastebinHomePage.createNewPaste(webDriverWait, textOfCode, textOfExpiration, textOfName);
        pastebinHomePage.setSyntaxInNewPaste(textOfSyntax);
        PastebinPastePage pastePage = pastebinHomePage.saveNewPaste();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(pastePage.getCodeFromPaste(webDriverWait), textOfCode,"Code of new paste is wrong   ");
        softAssert.assertEquals(pastePage.getTitleFromPaste(), textOfName, "Title of new paste is wrong   ");
        softAssert.assertEquals(pastePage.getSyntaxFromPaste(), textOfSyntax, "Syntax of new paste is wrong   ");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void browserTurnOff() {
        driver.quit();
        driver = null;
    }
}
