package bankguru;

import commons.BaseTest;
import envConfig.Environment;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_01_Multi_Environment_Maven_Owner extends BaseTest {

    WebDriver driver;

    private String username, password;

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String browserName) {
        String envName = System.getProperty("env");
        System.out.println("Environment of command line = " + envName);
        if (envName == null || envName.equals("")) {
            envName = "dev";
        }
        ConfigFactory.setProperty("envName", envName);

        Environment environment = ConfigFactory.create(Environment.class);
        driver = getBrowserDriver(browserName, environment.appUrl());
    }

    @Test
    public void TC_01() {
        verifyTrue(true);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserAndKillProcess();
    }

}
