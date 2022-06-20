package saucedemo;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.saucedemo.InventoryPageObject;
import pageObjects.saucedemo.LoginPageObject;
import pageObjects.saucedemo.PageGeneratorManager;
import utilities.DataHelper;

import java.util.List;

public class TC_04_Refactor_Environment extends BaseTest {

    WebDriver driver;

    private String username, password;

    LoginPageObject loginPage;

    List<DataHelper.User> users;

    InventoryPageObject inventoryPage;

    PageGeneratorManager pageGeneratorManager;

    @Parameters({"browser", "ipAddress", "port", "environment", "os" , "osVersion", "browserVersion"})
    @BeforeClass
    public void setUp(
            @Optional("firefox") String browserName,
            @Optional("localhost") String ipAddress,
            @Optional("4444") String port,
            @Optional("local") String environment,
            @Optional("Windows") String os,
            @Optional("10") String osVersion,
            @Optional("latest") String browserVersion
    ) {
        driver = getBrowserDriver(browserName, environment, ipAddress, port, os, osVersion, browserVersion);
        users = DataHelper.getUsers();

        users.forEach(user -> {
            loginPage = pageGeneratorManager.getLoginPageObject(driver);
            loginPage.enterUsername(driver, user.getUsername());
            loginPage.enterPassword(driver, user.getPassword());
            inventoryPage = loginPage.clickLoginButton(driver);
        });
    }

    @Test
    public void TC_01() {
        inventoryPage.sortBySelectDropdown(driver, "Name (Z to A)");
        verifyTrue(inventoryPage.isProductNameSortedDescending(driver));

        inventoryPage.sortBySelectDropdown(driver, "Name (A to Z)");
        verifyTrue(inventoryPage.isProductNameSortedAscending(driver));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
