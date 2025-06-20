package PageObjectTests;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import PageObject.RegisterPage;
import Api.AuthApi;

import Diplom.WebDriverCreator;

import java.util.UUID;

@RunWith(Parameterized.class)
public class RegistrationTest {
    private WebDriver webDriver;
    private String browser;

    public RegistrationTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "Браузер: {0}")
    public static Object[] browsers() {
        return new Object[]{"chrome", "yandex"};
    }

    @Before
    public void setUp() {

        webDriver = WebDriverCreator.createWebDriver(browser);
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void testRegistrationSuccess() {

        String email = "test_" + UUID.randomUUID() + "@mail.com";
        String password = "validPass123";
        String name = "Test User";

        RegisterPage registerPage = new RegisterPage(webDriver);

        registerPage.open();
        assertTrue("Страница регистрации не отображается", registerPage.isRegistrationPageDisplayed());

        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);

        registerPage.clickRegister();

        String token = AuthApi.loginUser(email, password).jsonPath().getString("accessToken");
        assertNotNull("Токен не получен после регистрации", token);
    }

    @Test
    public void testRegistrationWithShortPassword() {
        webDriver.get(RegisterPage.Register_URL);
        RegisterPage registerPage = new RegisterPage(webDriver);
        assertTrue(registerPage.isRegistrationPageDisplayed());

        String email = "test_" + UUID.randomUUID() + "@mail.com";
        String shortPassword = "123";
        String name = "Test User";

        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(shortPassword);
        registerPage.clickRegister();

        assertTrue(registerPage.isIncorrectPasswordMessageDisplayed());
    }
}