package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class HomePage extends StellarBurgersPage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By entryBtn = By.xpath("//*[.='Войти в аккаунт']");
    private By personalAccountBtn = By.xpath("//*[.='Личный Кабинет']");
    private By makeOrderBtn = By.xpath("//*[.='Оформить заказ']");
    private By bunsBtn = By.xpath("//*[.='Булки']");
    private By sausesBtn = By.xpath("//*[.='Соусы']");
    private By ingredsBtn = By.xpath("//*[.='Начинки']");


    public void clickEntryBtn() {
        waitElementClickable(entryBtn);
        this.find(entryBtn).click();
    }

    public void clickPersonalAccountBtn() {
        waitElementClickable(personalAccountBtn);
        this.find(personalAccountBtn).click();
    }

    public WebElement getSauces() {
        return find(sausesBtn);
    }

    public WebElement getBuns() {
        return find(bunsBtn);
    }

    public WebElement getIngeds() {
        return find(ingredsBtn);
    }

    public ArrayList<WebElement> getCategories() {
         ArrayList<WebElement> categories = new ArrayList<>();
         categories.add(getBuns());
         categories.add(getSauces());
         categories.add(getIngeds());
         return categories;
    }

    public By getEntryBtn() {
        return entryBtn;
    }

    public By getPersonalAccountBtn() {
        return personalAccountBtn;
    }

    public By getMakeOrderBtn() {
        return makeOrderBtn;
    }

    public By getBunsBtn() {
        return bunsBtn;
    }

    public By getSausesBtn() {
        return sausesBtn;
    }
    public By getIngredsBtn() {
        return ingredsBtn;
    }

}
