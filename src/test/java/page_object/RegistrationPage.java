package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends StellarBurgersPage {

    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    public By nameInput = By.xpath(".//main/div/form/fieldset[1]/div/div/input");
    public By emailInput = By.xpath(".//main/div/form/fieldset[2]/div/div/input");
    public By passwordInput = By.xpath(".//main/div/form/fieldset[3]/div/div/input");
    public By registerBtn = By.xpath(".//main/div/form/button");
    public By entryBtn = By.xpath("/html/body/div/div/main/div/div/p/a");
    public By passwordWrap = By.xpath(".//div/form/fieldset[3]/div/div");
    public By errorMessageWrongPassword = By.xpath(".//div/form/fieldset[3]/div/p");

    public RegistrationPage fillEntryForm(String email, String password, String name) {
        driver.findElement(this.nameInput).sendKeys(name);
        driver.findElement(this.emailInput).sendKeys(email);
        driver.findElement(this.passwordInput).sendKeys(password);

        return this;
    }

    public void clickRegistrationBtn() {
        waitElementClickable(registerBtn);
        this.find(registerBtn).click();
    }

    public void clickEntryBtn() {
        waitElementClickable(entryBtn);
        this.find(entryBtn).click();
    }
}
