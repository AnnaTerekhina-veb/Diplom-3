package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPage {
    private final WebDriver webDriver;
    private static final String RECOVERY_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    // Локатор
    private final By linkEnter = By.xpath(".//a[text()='Войти']");

    public RecoveryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }



    public void  openRecoveryPage() {
        webDriver.get(RECOVERY_PASSWORD_URL);
    }

    // Метод для перехода по ссылке "Войти"
    public void clickLinkEnter() {
        webDriver.findElement(linkEnter).click();
    }
}