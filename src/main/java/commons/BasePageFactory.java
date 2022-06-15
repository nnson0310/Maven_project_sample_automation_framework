package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class BasePageFactory {

    private int explicitWaitTimeout = 20;

    public static BasePage getBasePage() {
        return new BasePage();
    }

    private WebDriverWait explicitWait;

    private JavascriptExecutor jsExecutor;

    private Actions action;

    private void sleepInSecond(int seconds) {
        try {
            Thread.sleep(seconds *  1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    private By getByXpath(String xpathLocator) {
        return By.xpath(xpathLocator);
    }

    private WebElement getElement(WebDriver driver, String xpathLocator) {
        return driver.findElement(getByXpath(xpathLocator));
    }

    private List<WebElement> getElements(WebDriver driver, String xpathLocator) {
        return driver.findElements(getByXpath(xpathLocator));
    }

    protected void clickToElement(WebDriver driver, WebElement element) {
        element.click();
    }

    protected void sendKeyToElement(WebDriver driver, WebElement element, String str) {
        element.sendKeys(str);
    }

    protected void selectItemInDropDown(WebDriver driver, WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    protected void getSelectedItemInDropDown(WebDriver driver, WebElement element) {
        Select select = new Select(element);

        select.getFirstSelectedOption();
    }

    protected boolean isDropdownMultiple(WebDriver driver, WebElement element) {
        Select select = new Select(element);

        return select.isMultiple();
    }

    protected void selectItemInCustomDropDown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
        sleepInSecond(1);

        explicitWait =  new WebDriverWait(driver, explicitWaitTimeout);
        List<WebElement> elements = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

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

    protected String getAttributeValue(WebDriver driver, String xpathLocator, String attributeName) {
        return getElement(driver, xpathLocator).getAttribute(attributeName);
    }

    protected String getTextElement(WebDriver driver, WebElement element) {
        return element.getText();
    }

    protected String getCssValue(WebDriver driver, String xpathLocator, String cssSelector) {
        return getElement(driver, xpathLocator).getCssValue(cssSelector);
    }

    protected String getHexColorFromRgbColor(String rgbaColor) {
        return Color.fromString(rgbaColor).asHex();
    }

    protected int getElementSize(WebDriver driver, String xpathLocator) {
        return getElements(driver, xpathLocator).size();
    }

    protected void checkCheckboxOrRadio(WebDriver driver, String xpathLocator) {
        List<WebElement> elements = getElements(driver, xpathLocator);

        for (WebElement element:  elements) {
            if (element.isSelected()) {
                element.click();
                break;
            }
        }
    }

    protected void uncheckCheckboxOrRadio(WebDriver driver, String xpathLocator) {
        List<WebElement> elements = getElements(driver, xpathLocator);

        for (WebElement element:  elements) {
            if (!element.isSelected()) {
                element.click();
                break;
            }
        }
    }

    protected boolean isElementDisplayed(WebDriver driver, WebElement element) {
        return element.isDisplayed();
    }

    protected boolean isElementEnabled(WebDriver driver, String xpathLocator) {
        return getElement(driver, xpathLocator).isEnabled();
    }

    protected boolean isElementSelected(WebDriver driver, String xpathLocator) {
        return getElement(driver, xpathLocator).isSelected();
    }

    protected void switchToFrame(WebDriver driver, String xpathLocator) {
        driver.switchTo().frame(getElement(driver, xpathLocator));
    }

    protected void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    protected void hoverToElement(WebDriver driver, String xpathLocator) {
        action = new Actions(driver);
        action.moveToElement(getElement(driver, xpathLocator)).perform();
    }

    protected String getInnerText(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    protected void scrollToBottomPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void highlightElement(WebDriver driver, String xpathLocator) {
        jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElement(driver, xpathLocator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected void clickToElementByJS(WebDriver driver, String xpathLocator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, xpathLocator));
    }

    protected void scrollToElement(WebDriver driver, String xpathLocator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, xpathLocator));
    }

    protected void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, xpathLocator));
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

    protected String getElementValidationMessage(WebDriver driver, String xpathLocator) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, xpathLocator));
    }

    protected boolean isImageLoaded(WebDriver driver, String xpathLocator) {
        jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, xpathLocator));
        return status;
    }

    protected void waitForElementVisible(WebDriver driver, WebElement element) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);

        explicitWait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForAllElementVisible(WebDriver driver, List<WebElement> elements) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);

        explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    protected void waitForElementClickable(WebDriver driver, WebElement element) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);

        explicitWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementInvisible(WebDriver driver, WebElement element) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);

        explicitWait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void waitForAllElementInvisible(WebDriver driver, List<WebElement> elements) {
        explicitWait = new WebDriverWait(driver, explicitWaitTimeout);

        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
    }
}
