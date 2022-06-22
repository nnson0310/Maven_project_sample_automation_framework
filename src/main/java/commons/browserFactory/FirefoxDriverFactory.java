package commons.browserFactory;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverFactory implements BrowserFactory {
    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setAcceptInsecureCerts(true);

        //auto save downloaded file
        firefoxOptions.addPreference("browser.download.folderList", 2);
        firefoxOptions.addPreference("browser.download.manager.showWhenStarting", false);
        firefoxOptions.addPreference("browser.download.dir", GlobalConstants.DOWNLOAD_FILE_LOCATION);
        firefoxOptions.addPreference("browser.download.useDownloadDir", true);
        firefoxOptions.addPreference("browser.helperApps.neverAsk.openFile", "application/octet-stream");
        firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "text/plain," +
                "application/octet-stream," +
                "application/binary," +
                "text/csv," +
                "application/csv," +
                "application/excel," +
                "text/comma-separated-values," +
                "text/xml," +
                "application/xml," +
                "image/jpeg," +
                "image/png," +
                "text/html"
        );
        firefoxOptions.addPreference("pdfjs.disabled", true);

        //incognito
        // firefoxOptions.addArguments("-private");

        //disable browser logs
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.BROWSER_LOGS_PATH + "firefox.log");

        return new FirefoxDriver(firefoxOptions);
    }
}
