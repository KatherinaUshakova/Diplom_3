import data_for_tests.Browsers;
import data_for_tests.Constants;
import data_for_tests.URLs;
import page_object.HomePage;
import page_object.StellarBurgersPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertTrue;

public class ConstructorSectionsTest extends BrowserTest {
    HomePage homePage;

    @Before
    public void setUp() {
        this.setUpDriver(Browsers.CHROME);
        this.homePage = new HomePage(this.driver);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void constructorFieldsTest() throws InterruptedException {
        driver.get(URLs.HOME_PAGE);

        ArrayList<WebElement> list = homePage.getCategories();
        Collections.shuffle(list);

        for (WebElement category: list) {
            Thread.sleep(1000);
            homePage.waitElementClickable(category);
            Thread.sleep(1000);
            category.click();
            homePage.waitElementClickable(category);
            Thread.sleep(1000);
            assertTrue(StellarBurgersPage.ifElementContainsClass(category, Constants.ACTIVE_CATEGORY_CLASS));
        }
    }
}
