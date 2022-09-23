import api.UserApi;
import data_for_tests.Browsers;
import data_for_tests.URLs;
import data_for_tests.User;
import io.restassured.response.Response;
import page_object.AccountPage;
import page_object.HomePage;
import page_object.LoginPage;
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
        this.setUpDriver(Browsers.FIREFOX);
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
        homePage.waitElementClickable(homePage.getPersonalAccountBtn());
        homePage.clickPersonalAccountBtn();

        this.loginPage.loginUser(email, password);
        Thread.sleep(500);
        homePage.waitElementClickable(homePage.getPersonalAccountBtn());
        homePage.clickPersonalAccountBtn();
    }

    @Test
    public void clickPersonalAccountBtnTest() throws InterruptedException {
        Thread.sleep(500);
        accountPage.waitElementClickable(accountPage.getSaveBtn());

        assertEquals(URLs.ACCOUNT_PAGE, driver.getCurrentUrl());
    }

    @Test
    public void clickConstructorFromPersonalAccountTest() {
        accountPage.clickConstructorBtn();
        assertEquals(URLs.HOME_PAGE, driver.getCurrentUrl());
    }

    @Test
    public void clickLogoFromPersonalAccountTest() {
        accountPage.waitElementClickable(accountPage.getLogoBtn());
        accountPage.clickLogoBtn();

        assertEquals(URLs.HOME_PAGE, driver.getCurrentUrl());
    }

    @Test
    public void logoutTest() throws InterruptedException {
        Thread.sleep(1000);

        accountPage.waitElementClickable(accountPage.getExitBtn());
        accountPage.clickExitBtn();
        loginPage.waitElementClickable(loginPage.getEmailInput());

        assertEquals(URLs.LOGIN_PAGE, driver.getCurrentUrl());
    }
}
