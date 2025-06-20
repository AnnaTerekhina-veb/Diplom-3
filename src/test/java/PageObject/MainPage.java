package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MainPage {
    private static WebDriver webDriver;

    // Локаторы
    public static final String URL = "https://stellarburgers.nomoreparties.site";
    private final By enterAccount = By.xpath(".//button[text()='Войти в аккаунт']");
    private static final By enterLK = By.xpath(".//p[text()='Личный Кабинет']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By logoBurgers = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/descendant::a");
    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");
    private final By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]/span");

    // Переменная для хранения пользователя, если нужно

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    // Методы для взаимодействия
    public void clickEnterAccount() {
        webDriver.findElement(enterAccount).click();
    }

    public void openMainPage() {
        webDriver.get(URL);
    }

    public void clickEnterLK() {
        webDriver.findElement(enterLK).click();
    }

    public void clickConstructor() {
        webDriver.findElement(constructorButton).click();
    }

    public void clickLogoBurgers() {
        webDriver.findElement(logoBurgers).click();
    }

    public void clickBunsTab() {
        webDriver.findElement(bunsTab).click();
    }

    public void clickSaucesTab() {
        webDriver.findElement(saucesTab).click();
    }

    public void clickFillingsTab() {
        webDriver.findElement(fillingsTab).click();
    }


    public boolean isBunsTabActive() {
        WebElement element = webDriver.findElement(activeTab);
        return element.isDisplayed() && element.getText().equals("Булки");
    }

    public boolean isSaucesTabActive() {
        WebElement element = webDriver.findElement(activeTab);
        return element.isDisplayed() && element.getText().equals("Соусы");
    }

    public boolean isFillingsTabActive() {
        WebElement element = webDriver.findElement(activeTab);
        return element.isDisplayed() && element.getText().equals("Начинки");
    }

}