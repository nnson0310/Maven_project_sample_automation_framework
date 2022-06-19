package bankguru;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.bankguru.LoginPageObject;
import pageObjects.bankguru.PageGeneratorManager;

public class TC_04_Selenium_Grid extends BaseTest {

    WebDriver driver;

    private String username, password;

    PageGeneratorManager pageGeneratorManager;

    LoginPageObject loginPage;

    @Parameters({"browser", "url", "ipAddress", "port"})
    @BeforeClass
    public void setUp(String browserName, String pageUrl, String ipAddress, String port) {

        driver = getBrowserDriver(browserName, pageUrl, ipAddress, port);

        loginPage = pageGeneratorManager.getLoginPageObject(driver);
    }

    @Test
    public void TC_01() {

       sleepInSeconds(2);

       verifyTrue(true);

       sleepInSeconds(2);

       verifyFalse(true);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserAndKillProcess();
    }

}
