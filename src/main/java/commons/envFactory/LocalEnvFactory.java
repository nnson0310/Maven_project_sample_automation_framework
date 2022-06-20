package commons.envFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.Collections;

public class LocalEnvFactory {

    WebDriver driver;

    private String browserName;

    public LocalEnvFactory(String browserName) {
        this.browserName = browserName;
    }

    public WebDriver getDriver() {
        BROWSER_NAME browser = BROWSER_NAME.valueOf(browserName.toLowerCase());

        if (browser == BROWSER_NAME.firefox) {
            WebDriverManager.firefoxdriver().setup();

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setAcceptInsecureCerts(true);

            driver = new FirefoxDriver(firefoxOptions);
        } else if (browser == BROWSER_NAME.chrome) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setAcceptInsecureCerts(true);
            //use chromeOptions class to setup before running testcases
            //such as set language, disable notifications
            chromeOptions.addArguments("--lang=vi");
            chromeOptions.addArguments("--disable-infobars");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--disable-geolocation");
            chromeOptions.setExperimentalOption("useAutomationExtension", false);
            chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

            driver = new ChromeDriver(chromeOptions);
        } else if (browser == BROWSER_NAME.edge) {
            WebDriverManager.edgedriver().setup();

            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

            driver = new EdgeDriver(edgeOptions);
        } else if (browser == BROWSER_NAME.h_firefox) {
            WebDriverManager.firefoxdriver().setup();

            //set up headless options for firefox browser
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1200");

            driver = new FirefoxDriver(options);
        }
        else if (browser == BROWSER_NAME.h_chrome) {
            WebDriverManager.chromedriver().setup();

            //set up headless options for chrome browser
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1200");

            driver = new ChromeDriver(options);
        }
        else if (browser == BROWSER_NAME.brave) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");

            driver = new ChromeDriver(options);
        }
        else {
            throw new RuntimeException("Browser name is invalid");
        }

        return driver;
    }

}
