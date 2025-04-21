package utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BrowserDriver {
    public  WebDriver driver;

    public BrowserDriver() {
        this.driver = new ChromeDriver();
    }

    public  WebDriver getDriver() {
        return driver;
    }

    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
