package commons.browserFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HChromeDriverFactory implements BrowserFactory {
    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.chromedriver().setup();

        //set up headless options for chrome browser
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1200");

        return new ChromeDriver(options);
    }
}
