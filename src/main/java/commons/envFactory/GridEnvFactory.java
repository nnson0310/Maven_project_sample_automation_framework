package commons.envFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class GridEnvFactory {

    WebDriver driver;

    private String browserName, ipAddress, port;

    public GridEnvFactory(String browserName, String ipAddress, String port) {
        this.browserName = browserName;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public WebDriver getDriver() {
        DesiredCapabilities capabilities = null;
        BROWSER_NAME browser = BROWSER_NAME.valueOf(browserName.toLowerCase());

        if (browser == BROWSER_NAME.firefox) {
            WebDriverManager.firefoxdriver().setup();

            capabilities = DesiredCapabilities.firefox();
            capabilities.setAcceptInsecureCerts(true);
            capabilities.setBrowserName(String.valueOf(BROWSER_NAME.firefox));
            capabilities.setPlatform(Platform.ANY);

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.merge(capabilities);
        } else if (browser == BROWSER_NAME.chrome) {
            WebDriverManager.chromedriver().setup();

            capabilities = DesiredCapabilities.chrome();
            capabilities.setAcceptInsecureCerts(true);
            capabilities.setBrowserName(String.valueOf(BROWSER_NAME.chrome));
            capabilities.setPlatform(Platform.ANY);

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.merge(capabilities);
        }
        else {
            throw new RuntimeException("Browser name is invalid");
        }

        try {
            driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, port)), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }
}
