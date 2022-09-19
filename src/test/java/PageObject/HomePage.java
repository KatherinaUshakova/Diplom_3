package PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage extends StellarBurgersPage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public By entryBtn = By.xpath("/html/body/div/div/main/section[2]/div/button");
    public By personalAccountBtn = By.xpath(".//header/nav/a/p");

    public By makeOrderBtn = By.xpath("html/body/div/div/main/section[2]/div/button");
    public By bunsBtn = By.xpath("/html/body/div/div/main/section[1]/div[1]/div[1]");
    public By sausesBtn = By.xpath("/html/body/div/div/main/section[1]/div[1]/div[2]");
    public By ingredsBtn = By.xpath("/html/body/div/div/main/section[1]/div[1]/div[3]");


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
}
