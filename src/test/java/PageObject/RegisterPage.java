package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    public static final String Register_URL = "https://stellarburgers.nomoreparties.site/register";

    private final By registrationText = By.xpath(".//*[text()='Регистрация']");
    private final By nameField = By.xpath("//div/form/fieldset[1]//input[@name='name']");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private static final By registrationButton = By.xpath(".//*[text()='Зарегистрироваться']");
    private final By incorrectPasswordText = By.xpath(".//p[text() = 'Некорректный пароль']");
    private static final By linkEnter = By.xpath(".//a[text()='Войти']");

    private final WebDriver webDriver;

    public RegisterPage(WebDriver driver) {
        this.webDriver = driver;
    }

    public void open() {
        webDriver.get(Register_URL);
    }

    public boolean isRegistrationPageDisplayed() {
        return webDriver.findElement(registrationText).isDisplayed();
    }

    public void enterName(String name) {
        webDriver.findElement(nameField).sendKeys(name);
    }

    public void enterEmail(String email) {
        webDriver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        webDriver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegister() {
        webDriver.findElement(registrationButton).click();
    }

    public void clickRegisterButton() {
        webDriver.findElement(linkEnter).click();
    }

    public boolean isIncorrectPasswordMessageDisplayed() {
        return webDriver.findElement(incorrectPasswordText).isDisplayed();
    }
}