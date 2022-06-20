package bankguru;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.saucedemo.InventoryPageObject;
import pageObjects.saucedemo.LoginPageObject;
import pageObjects.saucedemo.PageGeneratorManager;
import utilities.DataHelper;

import java.util.List;

public class TC_02_Multi_Environment_Maven_Surefire extends BaseTest {

    WebDriver driver;

    private String username, password;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void setUp(String browserName, String pageUrl) {
//        driver = getBrowserDriver(browserName, pageUrl);
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
