package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends StellarBurgersPage {
    public LoginPage(WebDriver driver){
        super(driver);
    }

    public By registrationLink = By.xpath(".//main/div/div/p[1]/a");
    public By emailInput = By.xpath(".//main/div/form/fieldset[1]/div/div/input");
    public By passwordInput = By.xpath(".//main/div/form/fieldset[2]/div/div/input");
    public By entryBtn = By.xpath(".//main/div/form/button");
    public By resetPasswordLink = By.xpath("/html/body/div/div/main/div/div/p[2]/a");
    public By personalAccountBtn = By.xpath("/html/body/div/div/header/nav/a/p");


    public LoginPage fillEntryForm(String email, String password) {
        this.find(this.emailInput).sendKeys(email);
        this.find(this.passwordInput).sendKeys(password);
        return this;
    }

    public void clickEntryBtn() {
        waitElementClickable(entryBtn);
        this.find(entryBtn).click();
    }

    public void clickAccountBtn() {
        waitElementClickable(personalAccountBtn);
        this.find(personalAccountBtn).click();
    }

    public void clickResetPasswordLink() {
        waitElementClickable(resetPasswordLink);
        this.find(resetPasswordLink).click();
    }

    public void loginUser(String email, String password) {
        this.fillEntryForm(email, password).clickEntryBtn();
    }
}
