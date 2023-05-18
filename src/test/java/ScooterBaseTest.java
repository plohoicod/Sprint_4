import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.LoggerFactory;

import static ru.yandex.scooter.helpers.Constants.browserName;
import static ru.yandex.scooter.helpers.Constants.scooterMainUrl;

public class ScooterBaseTest {
    WebDriver driver;

    Logger logger = LoggerFactory.getLogger(ScooterBaseTest.class);

    @Before
    public void startUp() {
        BasicConfigurator.configure();

        switch (browserName) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                driver = new FirefoxDriver(options);
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options1 = new ChromeOptions();
                options1.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                driver = new ChromeDriver(options1);
                break;
            default:
                throw new RuntimeException("Wrong browser");
        }
        driver.get(scooterMainUrl);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
