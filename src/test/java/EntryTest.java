import Api.UserApi;
import DataForTests.Browsers;
import DataForTests.URLs;
import DataForTests.User;
import PageObject.HomePage;
import PageObject.LoginPage;
import PageObject.RegistrationPage;
import PageObject.ResetPasswordPage;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EntryTest extends BrowserTest {
    UserApi userApi = new UserApi();
    private String email = User.EMAIL;
    private String password = User.PASSWORD;
    private String name = User.NAME;
    HomePage homePage;
    LoginPage loginPage;
    RegistrationPage registrationPage;

    @Before
    public void setUp() {
        this.setUpDriver(Browsers.CHROME);
        RestAssured.baseURI = URLs.HOME_PAGE;

        this.homePage = new HomePage(this.driver);
        this.loginPage = new LoginPage(this.driver);
        this.registrationPage = new RegistrationPage(this.driver);

        this.userApi.saveAccessToken(this.userApi.createUser(email, password, name));
    }

    @After
    public void teardown() {
        driver.quit();
        this.userApi.deleteUser();
    }

    private void assertUrlLoginPage() {
        loginPage.waitElementClickable(loginPage.entryBtn);

        assertEquals(driver.getCurrentUrl(), URLs.LOGIN_PAGE);
    }

    private void assertUrlRedirectedToHomePage() throws InterruptedException {
        loginPage.loginUser(email, password);
        Thread.sleep(500);
        homePage.waitElementClickable(homePage.makeOrderBtn);

        assertEquals(driver.getCurrentUrl(), URLs.HOME_PAGE);
    }

    @Test
    public void clickEntryBtnMainPageTest() throws InterruptedException {
        this.driver.get(URLs.HOME_PAGE);
        Thread.sleep(500);
        this.homePage.clickEntryBtn();

        this.assertUrlLoginPage();
        this.assertUrlRedirectedToHomePage();
    }

    @Test
    public void entryPersonalAccountTest() throws InterruptedException {
        this.driver.get(URLs.HOME_PAGE);
        Thread.sleep(500);
        homePage.waitElementClickable(homePage.personalAccountBtn);
        this.homePage.clickPersonalAccountBtn();

        this.assertUrlLoginPage();
        this.assertUrlRedirectedToHomePage();
    }

    @Test
    public void entryRegistrationPageEntryBtnTest() throws InterruptedException {
        this.driver.get(URLs.REGISTRATION_PAGE);
        registrationPage.waitElementClickable(registrationPage.entryBtn);
        Thread.sleep(500);
        this.registrationPage.clickEntryBtn();

        this.assertUrlLoginPage();
        this.assertUrlRedirectedToHomePage();
    }

    @Test
    public void entryResetPasswordEntryBtnTest() throws InterruptedException {
        this.driver.get(URLs.LOGIN_PAGE);

        Thread.sleep(500);
        this.loginPage.clickResetPasswordLink();

        ResetPasswordPage resetPage = new ResetPasswordPage(this.driver);

        resetPage.clickEntryBtn();

        this.assertUrlLoginPage();
        this.assertUrlRedirectedToHomePage();
    }
}
