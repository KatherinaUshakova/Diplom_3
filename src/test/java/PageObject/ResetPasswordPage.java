package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage extends StellarBurgersPage{

    public By entryBtn = By.xpath(".//main/div/div/p/a");

    public ResetPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void clickEntryBtn() {
        this.find(entryBtn).click();
    }
}
