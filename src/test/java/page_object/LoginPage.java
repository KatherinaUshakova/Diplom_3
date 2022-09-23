package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends StellarBurgersPage {
    public LoginPage(WebDriver driver){
        super(driver);
    }

    private By registrationLink = By.xpath("//*[.='Зарегистрироваться']");

    private By emailInput = By.name("name");

    private By passwordInput =  By.name("Пароль");

    private By entryBtn = By.xpath("//*[.='Войти']");

    private By resetPasswordLink = By.xpath("//*[.='Восстановить пароль']");
    private By personalAccountBtn = By.xpath("//*[.='Личный Кабинет']");


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


    public By getRegistrationLink() {
        return registrationLink;
    }

    public By getEmailInput() {
        return emailInput;
    }

    public By getPasswordInput() {
        return passwordInput;
    }

    public By getEntryBtn() {
        return entryBtn;
    }

    public By getResetPasswordLink() {
        return resetPasswordLink;
    }

    public By getPersonalAccountBtn() {
        return personalAccountBtn;
    }
}
