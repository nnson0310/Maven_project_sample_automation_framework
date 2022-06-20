package saucedemo;

import commons.BaseTest;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.saucedemo.InventoryPageObject;
import pageObjects.saucedemo.LoginPageObject;
import pageObjects.saucedemo.PageGeneratorManager;
import utilities.DataHelper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class TC_03_Run_On_BrowserStack extends BaseTest {

    WebDriver driver;

    private String username, password;

    LoginPageObject loginPage;

    List<DataHelper.User> users;

    InventoryPageObject inventoryPage;

    PageGeneratorManager pageGeneratorManager;

    @Parameters({"url", "browser", "os", "osVersion" , "browserVersion"})
    @BeforeClass
    public void setUp(String pageUrl, String browserName, String platform, String platformVersion, String browserVersion) {
//        driver = getBrowserDriverByBrowserStack(pageUrl, browserName, platform, platformVersion, browserVersion);
//        users = DataHelper.getUsers();
//
//        users.forEach(user -> {
//            loginPage = pageGeneratorManager.getLoginPageObject(driver);
//            loginPage.enterUsername(driver, user.getUsername());
//            loginPage.enterPassword(driver, user.getPassword());
//            inventoryPage = loginPage.clickLoginButton(driver);
//        });
    }

    @Test
    public void TC_01() {
        inventoryPage.sortBySelectDropdown(driver, "Name (Z to A)");
        verifyTrue(inventoryPage.isProductNameSortedDescending(driver));

        inventoryPage.sortBySelectDropdown(driver, "Name (A to Z)");
        verifyTrue(inventoryPage.isProductNameSortedAscending(driver));

    }

    @Test
    public void TC_02() {
        inventoryPage.sortBySelectDropdown(driver, "Price (high to low)");
        verifyTrue(inventoryPage.isProductPriceSortedDescending(driver));

        inventoryPage.sortBySelectDropdown(driver, "Price (low to high)");
        verifyTrue(inventoryPage.isProductPriceSortedAscending(driver));
    }

    @Test
    public void TC_03() {
        inventoryPage.sortBySelectDropdown(driver, "Price (low to high)");
        verifyTrue(inventoryPage.isProductPriceSortedAscending(driver));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
