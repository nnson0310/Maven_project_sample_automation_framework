package pageObjects.saucedemo;

import commons.BasePage;
import interfaces.LoginPageUI;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePage {

    WebDriver driver;

    PageGeneratorManager pageGeneratorManager;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(WebDriver driver, String username) {
        waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, username);
    }

    public void enterPassword(WebDriver driver, String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public InventoryPageObject clickLoginButton(WebDriver driver) {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return pageGeneratorManager.getInventoryPageObject(driver);
    }
}
