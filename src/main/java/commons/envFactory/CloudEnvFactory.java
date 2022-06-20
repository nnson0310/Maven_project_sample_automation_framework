package commons.envFactory;

import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CloudEnvFactory {

    WebDriver driver;

    private String browserName, os, osVersion, browserVersion;

    public CloudEnvFactory(String browserName, String os, String osVersion, String browserVersion) {
        this.browserName = browserName;
        this.os = os;
        this.osVersion = osVersion;
        this.browserVersion = browserVersion;
    }

    public WebDriver getDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", os);
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", browserVersion);
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("name", "Run on Saucelabs on " + os + " and on " + browserName);
        capabilities.setCapability("sauce:options", sauceOptions);

        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.SAUCELABS_URL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }
}
