import api.UserApi;
import data_for_tests.Browsers;
import data_for_tests.Constants;
import data_for_tests.URLs;
import data_for_tests.User;
import page_object.RegistrationPage;
import page_object.StellarBurgersPage;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class RegistrationWithParametersTest extends BrowserTest{
    String password;
    boolean shouldFail;
    private String email = User.EMAIL;
    private String name = User.NAME;
    private RegistrationPage registrationPage;
    UserApi userApi = new UserApi();

    @Before
    public void setUp() {
        this.setUpDriver(Browsers.CHROME);
        RestAssured.baseURI = URLs.HOME_PAGE;

        this.driver.get(URLs.REGISTRATION_PAGE);
        this.registrationPage = new RegistrationPage(this.driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private void assertionsForFailedRegistration() {
        assertEquals(
                Constants.WRONG_PASSWORD_MESSAGE,
                registrationPage.getTextOfElement(registrationPage.errorMessageWrongPassword)
        );

        assertTrue(
                StellarBurgersPage.ifElementContainsClass(
                        registrationPage.find(registrationPage.passwordWrap),
                        Constants.ERROR_BORDER_CLASS
                )
        );
    }

    public RegistrationWithParametersTest(String password, boolean shouldFail) {
        this.password = password;
        this.shouldFail = shouldFail;
    }

    @Parameterized.Parameters (name = "Тестовые данные: {0} {1}")
    public static Object[][] getPasswords() {
        return new Object[][] {
                {"12345", true},
                {"abcdef", false},
                {"123abc7", false}
        };
    }

    @Test
    public void passwordLessThanSixCharsErrorTest(){
        registrationPage.waitElementClickable(registrationPage.registerBtn);

        registrationPage.fillEntryForm(email, this.password, name).clickRegistrationBtn();

        if (shouldFail) {
            assertionsForFailedRegistration();
            return;
        }

        Response resp = this.userApi.loginUser(email, password);
        this.userApi.saveAccessToken(resp);

        assertEquals(SC_OK, resp.getStatusCode());

        this.userApi.deleteUser();
    }
}
