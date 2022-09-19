import Api.UserApi;
import DataForTests.Browsers;
import DataForTests.URLs;
import DataForTests.User;
import PageObject.AccountPage;
import PageObject.HomePage;
import PageObject.LoginPage;
import io.restassured.RestAssured;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class PersonalAccountTest extends BrowserTest{
    private UserApi userApi = new UserApi();
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;

    private String email = User.EMAIL;
    private String password = User.PASSWORD;
    private String name = User.NAME;

    @Before
    public void setUp() throws InterruptedException {
        this.setUpDriver(Browsers.CHROME);
        RestAssured.baseURI = URLs.HOME_PAGE;

        this.homePage = new HomePage(this.driver);
        this.loginPage = new LoginPage(this.driver);
        this.accountPage = new AccountPage(this.driver);

        this.userApi.saveAccessToken(this.userApi.createUser(email, password, name));

        makeLoggedInUser();
        Thread.sleep(500);
    }

    @After
    public void teardown() {
        driver.quit();
        this.userApi.deleteUser();
    }

    public void makeLoggedInUser() throws InterruptedException {
        this.driver.get(URLs.HOME_PAGE);
        Thread.sleep(500);
        homePage.waitElementClickable(homePage.personalAccountBtn);
        homePage.clickPersonalAccountBtn();

        this.loginPage.loginUser(email, password);
        Thread.sleep(500);
        homePage.waitElementClickable(homePage.personalAccountBtn);
        homePage.clickPersonalAccountBtn();
    }

    @Test
    public void clickPersonalAccountBtnTest() throws InterruptedException {
        Thread.sleep(500);
        accountPage.waitElementClickable(accountPage.saveBtn);

        assertEquals(driver.getCurrentUrl(), URLs.ACCOUNT_PAGE);
    }

    @Test
    public void clickConstructorAndLogoFromPersonalAccountTest() throws InterruptedException {
        accountPage.clickConstructorBtn();
        assertEquals(driver.getCurrentUrl(), URLs.HOME_PAGE);

        homePage.waitElementClickable(homePage.personalAccountBtn);
        homePage.clickPersonalAccountBtn();

        Thread.sleep(1000);
        accountPage.waitElementClickable(accountPage.logoBtn);
        accountPage.clickLogoBtn();

        assertEquals(driver.getCurrentUrl(), URLs.HOME_PAGE);
    }

    @Test
    public void logoutTest() throws InterruptedException {
        Thread.sleep(1000);

        accountPage.waitElementClickable(accountPage.exitBtn);
        accountPage.clickExitBtn();

        loginPage.waitElementClickable(loginPage.entryBtn);

        assertEquals(driver.getCurrentUrl(), URLs.LOGIN_PAGE);

        Thread.sleep(500);
        loginPage.waitElementClickable(loginPage.personalAccountBtn);
        loginPage.clickAccountBtn();

        assertEquals(driver.getCurrentUrl(), URLs.LOGIN_PAGE);
    }
}
