import datafortests.Browsers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public abstract class BrowserTest {
    protected WebDriver driver;

    public void setUpDriver(Browsers browserName) {
        if (browserName == Browsers.FIREFOX) {
            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName == Browsers.CHROME) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        } else if (browserName == Browsers.YANDEX) {
            System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver_v104.0.5112.29.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Users\\admin\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }
    }
}
