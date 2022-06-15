package pageObjects.saucedemo;

import commons.BasePage;
import interfaces.InventoryPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryPageObject extends BasePage {

    WebDriver driver;

    public InventoryPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void sortBySelectDropdown(WebDriver driver, String sortName) {
        waitForElementClickable(driver, InventoryPageUI.SORT_SELECT_DROPDOWN);
        selectItemInDropDown(driver, InventoryPageUI.SORT_SELECT_DROPDOWN, sortName);
    }

    public boolean isProductNameSortedDescending(WebDriver driver) {

        List<WebElement> productItems = getElements(driver, InventoryPageUI.PRODUCT_ITEM_NAME);

        List<String> productNames = new ArrayList<String>();

        for(WebElement productItem:  productItems) {
            productNames.add(productItem.getText());
        }

        System.out.println("Items before sort: " + productNames);
        List<String> productNamesCopy = new ArrayList<String>(productNames);
        Collections.sort(productNamesCopy);
        Collections.reverse(productNamesCopy);

        System.out.println("Items after sort: " + productNamesCopy);
        return productNames.equals(productNamesCopy);
    }

    public boolean isProductNameSortedAscending(WebDriver driver) {
        List<WebElement> productItems = getElements(driver, InventoryPageUI.PRODUCT_ITEM_NAME);

        List<String> productNames = new ArrayList<String>();

        for(WebElement productItem:  productItems) {
            productNames.add(productItem.getText());
        }

        System.out.println("Items before sort: " + productNames);
        List<String> productNamesCopy = new ArrayList<String>(productNames);
        Collections.sort(productNamesCopy);

        System.out.println("Items after sort: " + productNamesCopy);
        return productNames.equals(productNamesCopy);
    }


    public boolean isProductPriceSortedDescending(WebDriver driver) {
        List<WebElement> productItems = getElements(driver, InventoryPageUI.PRODUCT_ITEM_PRICE);

        List<Float> productPrices = new ArrayList<Float>();

        for(WebElement productItem:  productItems) {
            productPrices.add(Float.parseFloat(productItem.getText().replace("$", "")));
        }

        System.out.println("Items before sort: " + productPrices);
        List<Float> productPricesCopy = new ArrayList<Float>(productPrices);
        Collections.sort(productPricesCopy);
        Collections.reverse(productPricesCopy);

        System.out.println("Items after sort: " + productPricesCopy);
        return productPrices.equals(productPricesCopy);
    }

    public boolean isProductPriceSortedAscending(WebDriver driver) {
        List<WebElement> productItems = getElements(driver, InventoryPageUI.PRODUCT_ITEM_PRICE);

        List<Float> productPrices = new ArrayList<Float>();

        for(WebElement productItem:  productItems) {
            productPrices.add(Float.parseFloat(productItem.getText().replace("$", "")));
        }

        System.out.println("Items before sort: " + productPrices);
        List<Float> productPricesCopy = new ArrayList<Float>(productPrices);
        Collections.sort(productPricesCopy);

        System.out.println("Items after sort: " + productPricesCopy);
        return productPrices.equals(productPricesCopy);
    }
}
