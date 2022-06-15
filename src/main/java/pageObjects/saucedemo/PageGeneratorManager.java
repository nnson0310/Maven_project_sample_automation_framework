package pageObjects.saucedemo;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

    public static LoginPageObject getLoginPageObject(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static InventoryPageObject getInventoryPageObject(WebDriver driver) {
        return new InventoryPageObject(driver);
    }

}
