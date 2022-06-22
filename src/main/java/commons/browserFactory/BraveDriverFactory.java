package commons.browserFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;

public class BraveDriverFactory implements BrowserFactory {

    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        if (IS_OS_WINDOWS) {
            options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
        }
        else {
            throw new IllegalStateException("Brave is not supported");
        }

        return new ChromeDriver(options);
    }
}
