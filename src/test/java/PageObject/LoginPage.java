package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    private WebDriver webDriver;

    // Локаторы

    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By enterButton = By.xpath(".//button[text()='Войти']");


    public LoginPage(WebDriver webDriver) {

        this.webDriver = webDriver;
    }

    // Методы для взаимодействия



    public void enterEmail(String email) {
        webDriver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        webDriver.findElement(passwordInput).sendKeys(password);
    }

    public void clickEnterButton() {
        webDriver.findElement(enterButton).click();
    }


}