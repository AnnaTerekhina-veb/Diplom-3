
package Diplom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverCreator {

    public static WebDriver createWebDriver(String browserType) {
        System.out.println("Выбранный браузер: " + browserType);
        if (browserType == null || browserType.equalsIgnoreCase("chrome")) {
            System.out.println("Создаём ChromeDriver");
            return createChromeDriver();
        } else if (browserType.equalsIgnoreCase("yandex")) {
            System.out.println("Создаём YandexDriver");
            return createYandexDriver();
        } else {
            System.out.println("Неизвестный тип браузера. По умолчанию создаём ChromeDriver");
            return createChromeDriver();
        }
    }

    private static WebDriver createChromeDriver() {
        System.out.println("Настройка ChromeOptions для Chrome");
        ChromeOptions options = new ChromeOptions();
        System.out.println("Создаём WebDriver с опциями: " + options);
        return new ChromeDriver(options);
    }

    private static WebDriver createYandexDriver() {
        // Пути к драйверу и браузеру — фиксированные, без переменных окружения
        String driverPath = "C:\\cygwin64\\home\\aleks\\WebDriver\\yandexdriver.exe"; // или ваш путь
        String browserPath = "C:\\Users\\aleks\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe"; // пример пути к Яндекс браузеру

        System.out.println("Установка системного свойства webdriver.chrome.driver: " + driverPath);
        System.setProperty("webdriver.chrome.driver", driverPath);

        ChromeOptions options = new ChromeOptions();
        System.out.println("Настройка binary в ChromeOptions для YandexBrowser: " + browserPath);
        options.setBinary(browserPath);
        System.out.println("Создаём YandexDriver с опциями: " + options);

        return new ChromeDriver(options);
    }
}
