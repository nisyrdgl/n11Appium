package utilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

import java.util.List;

public class ReusableMethods {
    public static void clickOnPage(String pageName) {
        wait(3);
        List<MobileElement> pages = Driver.getAppiumDriver().findElementsByClassName("android.widget.TextView");
        for (MobileElement page : pages) { //ui da goruluyorsa
            if (page.getText().equals(pageName)) {
                page.click();
                wait(3);
                break;
            } else {
                scrollWithUiScrollable(pageName);
                break;
            }


        }
    }

    public static void scrollWithUiScrollable(String Text) {
        AndroidDriver<MobileElement> driver = (AndroidDriver) Driver.getAppiumDriver();
        //elementi buluncaya kadar scoll yap
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + Text + "\"))");

        //bulunca tikla
        driver.findElementByXPath("//*[@text='" + Text + "']").click();

    }

    public static void longPressOElement(MobileElement mobileElement) {
        TouchAction touchAction = new TouchAction<>(Driver.getAppiumDriver());
        touchAction.longPress(LongPressOptions.longPressOptions()
                        .withElement(ElementOption.element(mobileElement)))
                .release()
                .perform();

    }

    public static void wait(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}