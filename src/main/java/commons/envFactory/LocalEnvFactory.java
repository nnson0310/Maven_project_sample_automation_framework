package commons.envFactory;

import commons.browserFactory.*;
import org.openqa.selenium.WebDriver;

public class LocalEnvFactory {

    WebDriver driver;

    private String browserName;

    public LocalEnvFactory(String browserName) {
        this.browserName = browserName;
    }

    public WebDriver getDriver() {
        BROWSER_NAME browser = BROWSER_NAME.valueOf(browserName.toLowerCase());

        if (browser == BROWSER_NAME.firefox) {
            driver = new FirefoxDriverFactory().getBrowserDriver();
        } else if (browser == BROWSER_NAME.chrome) {
            driver = new ChromeDriverFactory().getBrowserDriver();
        } else if (browser == BROWSER_NAME.edge) {
            driver =  new EdgeDriverFactory().getBrowserDriver();
        } else if (browser == BROWSER_NAME.h_firefox) {
            driver = new HFirefoxDriverFactory().getBrowserDriver();
        }
        else if (browser == BROWSER_NAME.h_chrome) {
            driver = new HChromeDriverFactory().getBrowserDriver();
        }
        else if (browser == BROWSER_NAME.brave) {
            driver = new BraveDriverFactory().getBrowserDriver();
        }
        else {
            throw new RuntimeException("Browser name is invalid");
        }

        return driver;
    }

}
