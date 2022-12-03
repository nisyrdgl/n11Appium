package tests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.N11Page;
import utilities.Driver;
import utilities.ReusableMethods1;

public class N11 extends ReusableMethods1 {


    @Test
    public void test01() throws InterruptedException {
        TouchAction touchAction = new TouchAction<>(Driver.getAppiumDriver());
        N11Page n11Page = new N11Page();
        //kullanıcı n11 uygulamasını acar
        Driver.getAppiumDriver();

        //kullanıcı arama kutusuna nutella yazar
        waitToBeClickable(n11Page.searchBox, 5);
        n11Page.searchBox.click();

        n11Page.searchBoxText.sendKeys("Nutella");

        touchAction.tap(PointOption.point(990, 1695)).perform();


        //kullanıcı arama sonuclarında nutella yazdıgını test eder

        String actualText = n11Page.searchResultText.getText();
        waitToBeVisible(n11Page.searchResultText, 7);
        System.out.println(actualText);


        Assert.assertTrue(actualText.contains("Nutella"));

        n11Page.basketAdd.click();
        n11Page.cart.click();
        Assert.assertTrue(n11Page.productInCart.getText().contains("Nutella"));

    }
}
