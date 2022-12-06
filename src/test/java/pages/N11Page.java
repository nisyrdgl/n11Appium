package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.time.Duration;

public class N11Page {
    public N11Page() {
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getAppiumDriver(), Duration.ofSeconds(15)),this);
    }
    @AndroidFindBy(id = "com.dmall.mfandroid:id/tvHomeSearchBar")
    public MobileElement searchBox;
    @AndroidFindBy(id = "com.dmall.mfandroid:id/etSearch")
    public MobileElement searchBoxText;


    @AndroidFindBy(xpath = "(//android.widget.TextView)[1]")
    public MobileElement searchResultText;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Nutella Kakaolu Fındık Kreması 2 x 400 G']")
    public MobileElement productInCart;

    @AndroidFindBy(id = "com.dmall.mfandroid:id/ivAddToBasket")
    public MobileElement basketAdd;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Sepetim, 1 new notification\"]/android.widget.ImageView")
    public MobileElement cart;
    @AndroidFindBy(id = "com.dmall.mfandroid:id/firstItemView")
    public MobileElement deleteProduct;
    @AndroidFindBy(id = "com.dmall.mfandroid:id/deleteTv")
    public MobileElement deleteButton;
    @AndroidFindBy(id = "com.dmall.mfandroid:id/emptyBasketTitleTv")
    public MobileElement emptyBasket;
}
