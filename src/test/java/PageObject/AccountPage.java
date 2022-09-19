package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends StellarBurgersPage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public By saveBtn = By.xpath("/html/body/div/div/main/div/div/div/div/button[2]");
    public By constructorBtn = By.xpath("/html/body/div/div/header/nav/ul/li[1]/a/p");
    public By logoBtn = By.xpath("/html/body/div/div/header/nav/div/a");

    public By exitBtn = By.xpath("/html/body/div/div/main/div/nav/ul/li[3]/button");

    public void clickConstructorBtn() {
        waitElementClickable(constructorBtn);
        this.find(constructorBtn).click();
    }

    public void clickLogoBtn() {
        waitElementClickable(logoBtn);
        this.find(logoBtn).click();
    }

    public void clickExitBtn() {
        this.find(exitBtn).click();
    }
}
