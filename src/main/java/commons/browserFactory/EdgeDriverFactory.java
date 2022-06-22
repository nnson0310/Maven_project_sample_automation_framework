package commons.browserFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;

import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;

public class EdgeDriverFactory implements BrowserFactory {

    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.edgedriver().setup();

        if (!IS_OS_WINDOWS) {
            throw new BrowserNotSupportedException("Edge browser");
        }

        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        return new EdgeDriver(edgeOptions);
    }
}
