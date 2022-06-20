package bankguru;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.bankguru.LoginPageObject;
import pageObjects.bankguru.PageGeneratorManager;

public class TC_04_Selenium_Grid extends BaseTest {

    WebDriver driver;

    private String username, password;

    PageGeneratorManager pageGeneratorManager;

    LoginPageObject loginPage;

    @Parameters({"browser", "url", "ipAddress", "port", "environment"})
    @BeforeClass
    public void setUp(
            @Optional("firefox") String browserName,
            @Optional("localhost") String ipAddress,
            @Optional("4444") String port,
            @Optional("local") String environment,
            @Optional("Window") String os,
            @Optional("10") String osVersion,
            @Optional("latest") String browserVersion
    ) {

        driver = getBrowserDriver(browserName, environment, ipAddress, port, os, osVersion, browserVersion);

        loginPage = pageGeneratorManager.getLoginPageObject(driver);
    }

    @Test
    public void TC_01() {

       sleepInSeconds(2);

       verifyTrue(true);

       sleepInSeconds(2);

       verifyFalse(false);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserAndKillProcess();
    }

}
