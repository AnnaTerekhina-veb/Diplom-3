package PageObjectTests;

import Api.AuthApi;
import Diplom.WebDriverCreator;
import PageObject.*;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



@RunWith(Parameterized.class)
public class LoginPageTest {
    private WebDriver webDriver;
    private String browser;
    private String accessToken;

    public LoginPageTest(String browser) {
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
        if (accessToken != null) {
            Response response = AuthApi.deleteUser(accessToken);
            System.out.println("Удаление пользователя: " + response.getStatusCode());
        }
        if (webDriver != null) {
            webDriver.quit();
        }
    }


    //вход по кнопке «Войти в аккаунт» на главной
    @Test
    public void testLoginViaButton() {

        String email = "testuser_" + System.currentTimeMillis() + "@mail.com";
        String password = "TestPassword123!";
        String name = "Test User";

        AuthApi.UserCredentials user = AuthApi.registerAndLogin(email, password, name);
        this.accessToken = user.token;

        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        mainPage.clickEnterAccount();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickEnterButton();
        mainPage.clickEnterLK();

        new PersonalAccountPage(webDriver).verifyLoginData(email);
    }

    // Вход через личный кабинет
    @Test
    public void testLoginViaPersonalAccount() {

        String email = "testuser_" + System.currentTimeMillis() + "@mail.com";
        String password = "TestPassword123!";
        String name = "Test User";

        AuthApi.UserCredentials user = AuthApi.registerAndLogin(email, password, name);
        this.accessToken = user.token;

        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        mainPage.clickEnterLK();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickEnterButton();
        mainPage.clickEnterLK();
        new PersonalAccountPage(webDriver).verifyLoginData(email);

    }

    // вход через кнопку в форме регистрации
    @Test
    public void testLoginViaRegistration() {


        String email = "testuser_" + System.currentTimeMillis() + "@mail.com";
        String password = "TestPassword123!";
        String name = "Test User";

        AuthApi.UserCredentials user = AuthApi.registerAndLogin(email, password, name);
        this.accessToken = user.token;

        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.open();
        registerPage.clickRegisterButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickEnterButton();

        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickEnterLK();
        new PersonalAccountPage(webDriver).verifyLoginData(email);

    }

    // вход через кнопку в форме восстановления пароля.
    @Test
    public void testLoginViaRecoveryPage() {

        String email = "testuser_" + System.currentTimeMillis() + "@mail.com";
        String password = "TestPassword123!";
        String name = "Test User";

        AuthApi.UserCredentials user = AuthApi.registerAndLogin(email, password, name);
        this.accessToken = user.token;

        RecoveryPage recoveryPage = new RecoveryPage(webDriver);
        recoveryPage.openRecoveryPage();
        recoveryPage.clickLinkEnter();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickEnterButton();

        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickEnterLK();
        new PersonalAccountPage(webDriver).verifyLoginData(email);
    }

    // переход по клику на «Личный кабинет»
    @Test
    public void testClickPersonalAccount() {

        String email = "testuser_" + System.currentTimeMillis() + "@mail.com";
        String password = "TestPassword123!";
        String name = "Test User";
        AuthApi.UserCredentials user = AuthApi.registerAndLogin(email, password, name);
        this.accessToken = user.token;

        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        mainPage.clickEnterAccount();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickEnterButton();

        mainPage.clickEnterLK();
        new PersonalAccountPage(webDriver).verifyLoginData(email);

    }

    // переход по клику на «Конструктор» из личного кабинета

    @Test
    public void testClickConstructor() {

        String email = "testuser_" + System.currentTimeMillis() + "@mail.com";
        String password = "TestPassword123!";
        String name = "Test User";

        AuthApi.UserCredentials user = AuthApi.registerAndLogin(email, password, name);
        this.accessToken = user.token;

        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        mainPage.clickEnterAccount();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickEnterButton();

        mainPage.clickEnterLK();
        mainPage.clickConstructor();
    }
// переход по клику на логотип Stellar Burgers из личного кабинета
    @Test
    public void testClickStellarBurgers() {

        String email = "testuser_" + System.currentTimeMillis() + "@mail.com";
        String password = "TestPassword123!";
        String name = "Test User";

        AuthApi.UserCredentials user = AuthApi.registerAndLogin(email, password, name);
        this.accessToken = user.token;

        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        mainPage.clickEnterAccount();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickEnterButton();

        mainPage.clickEnterLK();
        mainPage.clickLogoBurgers();
    }

    @Test
    public void testClickExitPA() {

        String email = "testuser_" + System.currentTimeMillis() + "@mail.com";
        String password = "TestPassword123!";
        String name = "Test User";

        AuthApi.UserCredentials user = AuthApi.registerAndLogin(email, password, name);
        this.accessToken = user.token;

        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        mainPage.clickEnterAccount();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickEnterButton();

        mainPage.clickEnterLK();
        PersonalAccountPage page = new PersonalAccountPage(webDriver);

        page.waitForElementToBeClickable(By.xpath(".//button[text()='Выход']")).click();
    }


}


