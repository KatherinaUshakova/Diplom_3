package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends StellarBurgersPage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    private By saveBtn = By.xpath("//*[.='Сохранить']");
    private By constructorBtn = By.xpath("//*[.='Конструктор']");
    private By logoBtn = By.xpath("//a[@href='/']");
    private By exitBtn = By.xpath("//*[.='Выход']");

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

    public By getExitBtn() {
        return exitBtn;
    }

    public By getLogoBtn() {
        return logoBtn;
    }

    public By getConstructorBtn() {
        return constructorBtn;
    }

    public By getSaveBtn() {
        return saveBtn;
    }
}
