package PageObjectTests;


import Diplom.WebDriverCreator;
import PageObject.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)
public class ConstructorTest {
    private WebDriver webDriver;
    private String browser;

    public ConstructorTest(String browser) {
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

    // Переход к разделу «Булки»
    @Test
    public void testClickBunsTab() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();

        mainPage.isBunsTabActive();

    }

    // Переход к разделу «Соусы»
    @Test
    public void testClickSaucesTab() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        mainPage.clickSaucesTab();

        mainPage.isSaucesTabActive();

    }

    // Переход к разделу «Начинки»
    @Test
    public void testClickFillingsTab() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        mainPage.clickFillingsTab();

        mainPage.isFillingsTabActive();

    }


}
