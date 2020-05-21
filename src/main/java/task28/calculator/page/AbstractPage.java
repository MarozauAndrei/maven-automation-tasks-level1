package task28.calculator.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class AbstractPage {
    protected WebDriver driver;
    protected JavascriptExecutor executor;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.executor = (JavascriptExecutor) driver;
    }
}
