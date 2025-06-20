package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class PersonalAccountPage {
    private final WebDriver webDriver;

    // Локаторы

    private final By loginInput = By.xpath(".//label[text()='Логин']/following-sibling::input");
    private final By exitButton = By.xpath(".//button[text()='Выход']");


    public PersonalAccountPage (WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Методы для взаимодействия

    public String getLoginValue() {
        WebElement loginElement = waitForElementToBeVisible(loginInput);
        return loginElement.getAttribute("value");
    }

    public void verifyLoginData(String expectedLogin) {
        PersonalAccountPage accountPage = new PersonalAccountPage(webDriver);
        String actualLogin = accountPage.getLoginValue();
        assertEquals("Логин на странице не совпадает с ожидаемым", expectedLogin, actualLogin);
    }

    public WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}