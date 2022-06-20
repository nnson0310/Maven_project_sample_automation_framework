package bankguru;

import commons.BaseTest;
import envConfig.Environment;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.bankguru.LoginPageObject;
import pageObjects.bankguru.PageGeneratorManager;

import java.sql.SQLException;

public class TC_03_Database_Connect extends BaseTest {

    WebDriver driver;

    private String username, password;

    PageGeneratorManager pageGeneratorManager;

    LoginPageObject loginPage;

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String browserName) {

//        String envName = System.getProperty("env");
//        System.out.println("Environment of command line = " + envName);
//        if (envName == null || envName.equals("")) {
//            envName = "dev";
//        }
//        ConfigFactory.setProperty("envName", envName);
//
//        Environment environment = ConfigFactory.create(Environment.class);
//        driver = getBrowserDriver(browserName, environment.appUrl());
//
//        loginPage = pageGeneratorManager.getLoginPageObject(driver);
    }

    @Test
    public void TC_01() throws SQLException {

        int actorNum = 200;

        int actorCountFromDB = loginPage.getActorNumberFromDB(driver);

        Assert.assertEquals(actorNum, actorCountFromDB);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserAndKillProcess();
    }

}
