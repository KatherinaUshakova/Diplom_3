package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StellarBurgersPage {
    protected final WebDriver driver;

    public StellarBurgersPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTextOfElement(By element) {
        return find(element).getText();
    }

    public static boolean ifElementContainsClass(WebElement element, String active) {
        return element.getAttribute("class").contains(active);
    }

    public WebElement find(By by) {
        return driver.findElement(by);
    }

    public void waitElementClickable(By by) {
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementClickable(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}
