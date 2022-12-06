package tests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.N11Page;
import utilities.Driver;
import utilities.Log;
import utilities.ReusableMethods;


public class N11 extends ReusableMethods {

    /*
    1-user opens the app
    2-user searches for nutella
    3-user verifies that nutella in the search resault
    4-user adds the first product to the cart
    5-user clicks the my cart button
    6-user verifies that the product is nutella
    7-user deletes the product from the cart
    8-user verifies that the cart is empty
    9-user cloess the app
     */
    @Test
    public void test01() throws InterruptedException {

        TouchAction touchAction = new TouchAction<>(Driver.getAppiumDriver());
        N11Page n11Page = new N11Page();

        Driver.getAppiumDriver();
        Log.startTestCase("user opens the app");


        waitToBeClickable(n11Page.searchBox, 5);
        n11Page.searchBox.click();

        n11Page.searchBoxText.sendKeys("Nutella");

        touchAction.tap(PointOption.point(990, 1695)).perform();
        Log.info("user searches for nutella");


        waitToBeVisible(n11Page.searchResultText, 10);
        String actualText = n11Page.searchResultText.getText();

        System.out.println(actualText);
        Assert.assertTrue(actualText.contains("Nutella"));
        Log.info("user verifies that nutella in the search resault");


        n11Page.basketAdd.click();
        Log.info("user adds the first product to the cart");


        n11Page.cart.click();
        Log.info("user clicks the my cart button");

        scrollDownTo();

        Assert.assertTrue(n11Page.productInCart.getText().contains("Nutella"));
        Log.info("user verifies that the product is nutella");

        n11Page.deleteProduct.click();
        n11Page.deleteButton.click();
        Log.info("user deletes the product from the cart");


        Assert.assertTrue(n11Page.emptyBasket.isDisplayed());
        Log.info("user verifies that the cart is empty");
        Driver.quitAppiumDriver();
        Log.endTestCase("user cloess the app");


    }
}
