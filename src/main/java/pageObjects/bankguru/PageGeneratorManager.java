package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

    public static LoginPageObject getLoginPageObject(WebDriver driver) {
        return new LoginPageObject(driver);
    }

}
