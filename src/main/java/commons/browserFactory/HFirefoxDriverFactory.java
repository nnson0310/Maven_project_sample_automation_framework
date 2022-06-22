package commons.browserFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class HFirefoxDriverFactory implements BrowserFactory {
    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.firefoxdriver().setup();

        //set up headless options for firefox browser
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1200");

        return new FirefoxDriver(options);
    }
}
