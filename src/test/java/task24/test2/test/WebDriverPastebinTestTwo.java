package task24.test2.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import task24.test2.page.PastebinHomePageTwo;
import task24.test2.page.PastebinPastePage;

public class WebDriverPastebinTestTwo {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        driver = new ChromeDriver();
    }

    @Test(description = "Test N2 - Check of creating new paste in pastebin.com and content of new page")
    public void createAndCheckNewPaste() {
        String textOfCode = "git config --global user.name  \"New Sheriff in Town\"" + "\n" +
                "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")" + "\n" +
                "git push origin master --force";
        String textOfSyntax = "Bash";                   // Or it's better to declare variables in class ?
        String textOfExpiration = "10 Minutes";
        String textOfName = "how to gain dominance among developers";

        int rightFields = 0;
        StringBuilder message = new StringBuilder();

        PastebinPastePage pastePage = new PastebinHomePageTwo(driver).openHomePage()
                .createNewPaste(textOfCode, textOfSyntax, textOfExpiration, textOfName);

        if (pastePage.compareCode(textOfCode)) {
            rightFields++;
        } else {
            message.append("Code of new paste is wrong   ");
        }
        if (pastePage.compareTitle(textOfName)) {
            rightFields++;
        } else {
            message.append("Title of new paste is wrong   ");
        }
        if (pastePage.compareSyntax(textOfSyntax)) {
            rightFields++;
        } else {
            message.append("Syntax of new paste is wrong   ");
        }
        Assert.assertEquals(rightFields, 3, message.toString());
    }
    @AfterMethod(alwaysRun = true)
    public void browserTurnOff() {
        driver.quit();
        driver = null;
    }
}
