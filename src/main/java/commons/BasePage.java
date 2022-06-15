package commons;

import interfaces.BasePageUI;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {

    private int explicitWaitTimeout = GlobalConstants.LONG_TIME_OUT;

    public static BasePage getBasePage() {
        return new BasePage();
    }

    private WebDriverWait explicitWait;

    private JavascriptExecutor jsExecutor;

    private Actions action;

    private void overrideGlobalTimeout(WebDriver driver, long seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    protected void sleepInSecond(long seconds) {
        try {
            Thread.sleep(seconds *  GlobalConstants.THREAD_SLEEP_MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for(Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
    }

    protected void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    protected String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    protected String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    protected String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    protected void redirectBack(WebDriver driver) {
        driver.navigate().back();
    }

    protected void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected void redirectToPage(WebDriver driver, String url) {
        driver.navigate().to(url);
    }

    protected void redirectForward(WebDriver driver) {
        driver.navigate().forward();
    }

    protected Alert waitForAlertPresent(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);

        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    protected void acceptAlert(WebDriver driver) {
        waitForAlertPresent(driver).accept();
    }

    protected void cancelAlert(WebDriver driver) {
        waitForAlertPresent(driver).dismiss();
    }

    protected void getAlertText(WebDriver driver) {
        waitForAlertPresent(driver).getText();
    }

    protected void sendKeyToAlert(WebDriver driver, String str) {

        waitForAlertPresent(driver).sendKeys(str);
    }

    protected void switchWindowById(WebDriver driver, String currentWindowId) {
        Set<String> allWindowIds = driver.getWindowHandles();

        for (String id : allWindowIds) {
            if (!id.equals(currentWindowId)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    protected void switchWindowByTitle(WebDriver driver, String tabTitle) {
        Set<String> allWindowIds = driver.getWindowHandles();

        for (String id : allWindowIds) {
            driver.switchTo().window(id);
            if (driver.getTitle().equals(tabTitle)) {
                break;
            }
        }
    }

    protected void closeAllExceptParentWindow(WebDriver driver, String parentWindowId) {
        Set<String> windowId = driver.getWindowHandles();

        for (String id : windowId) {
            if (!id.equals(parentWindowId)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentWindowId);
        }
    }

    private String getLocatorValue(String locator) {
        return locator.substring(locator.indexOf("=") + 1);
    }

    private By getByLocator(String locator) {
        By by = null;

        if (locator.startsWith("id=") || locator.startsWith("ID=") || locator.startsWith("Id=")) {
            by = By.id(getLocatorValue(locator));
        }
        else if (locator.startsWith("css=") || locator.startsWith("CSS=") || locator.startsWith("Css=")) {
            by = By.cssSelector(getLocatorValue(locator));
        }
        else if (locator.startsWith("class=") || locator.startsWith("CLASS=") || locator.startsWith("Class=")) {
            by = By.className(getLocatorValue(locator));
        }
        else if (locator.startsWith("name=") || locator.startsWith("NAME=") || locator.startsWith("Name=")) {
            by = By.name(getLocatorValue(locator));
        }
        else if (locator.startsWith("xpath=") || locator.startsWith("XPATH=") || locator.startsWith("Xpath=")) {
            by = By.xpath(getLocatorValue(locator));
        }
        else {
            throw new RuntimeException("Locator type is invalid");
        }
        return by;
    }

    private String getDynamicXpath(String locator, String... dynamicValues) {
        if (locator.startsWith("Xpath") || locator.startsWith("xpath=") || locator.startsWith("XPATH=")) {
            locator = String.format(locator, (Object[]) dynamicValues);
        }
        return locator;
    }

    protected WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    protected List<WebElement> getElements(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    protected void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    protected void clickToElement(WebDriver driver, String locator, String... dynamicValues) {
        getElement(driver, getDynamicXpath(locator, dynamicValues)).click();
    }

    protected void sendKeyToElement(WebDriver driver, String locator, String value) {
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(value);
    }

    protected void sendKeyToElement(WebDriver driver, String locator, String value, String... dynamicValues) {
        getElement(driver, getDynamicXpath(locator, dynamicValues)).clear();
        getElement(driver, getDynamicXpath(locator, dynamicValues)).sendKeys(value);
    }

    protected void clearInputValueByKeyboard(WebDriver driver, String locator) {
        getElement(driver, locator).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }

    protected void clearInputValueByKeyboard(WebDriver driver, String locator, String... dynamicValues) {
        getElement(driver, getDynamicXpath(locator, dynamicValues)).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }

    protected void pressEnterButton(WebDriver driver) {
        action = new Actions(driver);

        action.sendKeys(Keys.ENTER);
    }

    protected void selectItemInDropDown(WebDriver driver, String locator, String text) {
        Select select = new Select(getElement(driver, locator));
        select.selectByVisibleText(text);
    }

    protected void selectItemInDropDown(WebDriver driver, String locator, String text, String... dynamicValues) {
        Select select = new Select(getElement(driver, getDynamicXpath(locator, dynamicValues)));
        select.selectByVisibleText(text);
    }

    protected void getSelectedItemInDropDown(WebDriver driver, String locator) {
        Select select = new Select(getElement(driver, locator));

        select.getFirstSelectedOption();
    }

    protected boolean isDropdownMultiple(WebDriver driver, String locator) {
        Select select = new Select(getElement(driver, locator));

        return select.isMultiple();
    }

    protected void selectItemInCustomDropDown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
        sleepInSecond(1);

        explicitWait =  new WebDriverWait(driver, explicitWaitTimeout);
        List<WebElement> elements = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));

        for(WebElement element: elements) {
            if (element.getText().trim().equals(expectedItem)) {
                jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
                sleepInSecond(1);

                element.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    protected String getAttributeValue(WebDriver driver, String locator, String attributeName) {
        return getElement(driver, locator).getAttribute(attributeName);
    }

    protected String getElementText(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    protected String getElementText(WebDriver driver, String locator, String... dynamicValues) {
        return getElement(driver, getDynamicXpath(locator, dynamicValues)).getText();
    }


    protected String getCssValue(WebDriver driver, String locator, String cssSelector) {
        return getElement(driver, locator).getCssValue(cssSelector);
    }

    protected String getHexColorFromRgbColor(String rgbaColor) {
        return Color.fromString(rgbaColor).asHex();
    }

    protected int getElementSize(WebDriver driver, String locator) {
        return getElements(driver, locator).size();
    }

    protected int getElementSize(WebDriver driver, String locator, String... dynamicValues) {
        return getElements(driver, getDynamicXpath(locator, dynamicValues)).size();
    }

    protected void uncheckCheckboxOrRadio(WebDriver driver, String locator) {
        List<WebElement> elements = getElements(driver, locator);

        for (WebElement element:  elements) {
            if (element.isSelected()) {
                element.click();
                break;
            }
        }
    }

    protected void uncheckCheckboxOrRadio(WebDriver driver, String locator, String... dynamicValues) {
        List<WebElement> elements = getElements(driver, getDynamicXpath(locator, dynamicValues));

        for (WebElement element:  elements) {
            if (element.isSelected()) {
                element.click();
                break;
            }
        }
    }

    protected void checkCheckboxOrRadio(WebDriver driver, String locator) {
        List<WebElement> elements = getElements(driver, locator);

        for (WebElement element:  elements) {
            if (!element.isSelected()) {
                element.click();
                break;
            }
        }
    }

    protected void checkCheckboxOrRadio(WebDriver driver, String locator, String... dynamicValues) {
        List<WebElement> elements = getElements(driver, getDynamicXpath(locator, dynamicValues));

        for (WebElement element:  elements) {
            if (!element.isSelected()) {
                element.click();
                break;
            }
        }
    }

    protected boolean isElementDisplayed(WebDriver driver, String locator) {
        return getElement(driver, locator).isDisplayed();
    }

    protected boolean isElementDisplayed(WebDriver driver, String locator, String... dynamicValues) {
        return getElement(driver, getDynamicXpath(locator, dynamicValues)).isDisplayed();
    }

    protected boolean isElementUndisplayed(WebDriver driver, String locator) {
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIME_OUT);
        List<WebElement> elements = getElements(driver, locator);
        overrideGlobalTimeout(driver, GlobalConstants.LONG_TIME_OUT);
        int numberOfElements = elements.size();

        if (numberOfElements == 0) {
            return true;
        }
        else if (numberOfElements > 0 && !elements.get(0).isDisplayed()) {
            return true;
        }
        else {
            return false;
        }
    }

    protected boolean isElementEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    protected boolean isElementEnabled(WebDriver driver, String locator, String... dynamicValues) {
        return getElement(driver, getDynamicXpath(locator, dynamicValues)).isEnabled();
    }

    protected boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    protected boolean isElementSelected(WebDriver driver, String locator, String... dynamicValues) {
        return getElement(driver, getDynamicXpath(locator, dynamicValues)).isSelected();
    }

    protected void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getElement(driver, locator));
    }

    protected void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    protected void hoverToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.moveToElement(getElement(driver, locator)).perform();
    }

    protected String getInnerText(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    protected void scrollToBottomPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void highlightElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected void clickToElementByJS(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
    }

    protected void scrollToElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);
        jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    protected String getElementValidationMessage(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    protected boolean isImageLoaded(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
        return status;
    }

    protected void waitForElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    protected void waitForElementVisible(WebDriver driver, String locator, String... dynamicValues) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locator, dynamicValues))));
    }

    protected void waitForAllElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);

        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    protected void waitForAllElementVisible(WebDriver driver, String locator, String... dynamicValues) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);

        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locator, dynamicValues))));
    }

    protected void waitForElementClickable(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);

        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    protected void waitForElementClickable(WebDriver driver, String locator, String... dynamicValues) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);

        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locator, dynamicValues))));
    }

    protected void waitForElementInvisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIME_OUT);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
        overrideGlobalTimeout(driver, GlobalConstants.LONG_TIME_OUT);
    }

    protected void waitForElementInvisible(WebDriver driver, String locator, String... dynamicValues) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIME_OUT);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locator, dynamicValues))));
        overrideGlobalTimeout(driver, GlobalConstants.LONG_TIME_OUT);
    }

    protected void waitForAllElementInvisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);

        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getElements(driver, locator)));
    }

    protected void waitForAllElementInvisible(WebDriver driver, String locator, String... dynamicValues) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);

        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getElements(driver, getDynamicXpath(locator, dynamicValues))));
    }

    public void openPageByDynamicLocator(WebDriver driver, String... dynamicValues) {
        waitForElementClickable(driver, BasePageUI.USER_DYNAMIC_NAVIGATION_LINK, dynamicValues);
        clickToElement(driver, BasePageUI.USER_DYNAMIC_NAVIGATION_LINK, dynamicValues);
    }

    /**
     * Enter value into dynamic input which is identified by name attribute
     * @param nameAttribute value of name attribute
     * @param value value of input field
     */
    public void enterToInputByNameAttribute(WebDriver driver, String nameAttribute, String value) {
        waitForAllElementVisible(driver, BasePageUI.DYNAMIC_INPUT_TEXTBOX, nameAttribute);
        sendKeyToElement(driver, BasePageUI.DYNAMIC_INPUT_TEXTBOX, value, nameAttribute);
    }

    /**
     * Enter value into dynamic input which is identified by name attribute
     * @param id value of id attribute
     */
    public void checkToRadioCheckboxById(WebDriver driver, String id) {
        waitForElementClickable(driver, BasePageUI.DYNAMIC_RADIO_CHECKBOX, id);
        checkCheckboxOrRadio(driver, BasePageUI.DYNAMIC_RADIO_CHECKBOX, id);
    }

    /**
     * Select value of select dropdown list by name attribute
     *
     * @param driver: instance of Webdriver
     * @param nameAttribute: value of name attribute
     * @param selectValue: the value which will be selected
     */
    public void clickToSelectByNameAttribute(WebDriver driver, String nameAttribute, String selectValue) {
        waitForElementClickable(driver, BasePageUI.DYNAMIC_SELECT, nameAttribute);
        selectItemInDropDown(driver, BasePageUI.DYNAMIC_SELECT, selectValue, nameAttribute);
    }

    /**
     * Click to button by dynamic id
     *
     * @param driver: instance of Webdriver
     * @param id: value of id attribute
     */
    public void clickToButtonById(WebDriver driver, String id) {
        waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_ID, id);
        clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_ID, id);
    }

}

