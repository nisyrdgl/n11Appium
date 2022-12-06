package utilities;


import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class ReusableMethods {

         public static void tapOnElementWithText(String text) {
            List<MobileElement> mobileElementList = Driver.getAppiumDriver().findElementsByClassName("android.widget.TextView");
            for (MobileElement page: mobileElementList) {
                if (page.getText().equals(text)){
                    page.click();
                }else{
                    scrollWithUiScrollable(text);
                }
                break;
            }
        }

//ikinci alternatif bir method
        public static void clickOnElementWithText(String elementText) throws InterruptedException {
            Thread.sleep(4000);
            List<MobileElement> mobileElementList = Driver.getAppiumDriver().findElementsByXPath("//android.widget.TextView[@text='"+elementText+"']");
            if (mobileElementList.size()>0){
                mobileElementList.get(0).click();
            }else scrollWithUiScrollable(elementText);
        }


    public static boolean isElementPresent(String text) {//element mevcut mu
        boolean elementFound = false;
        List<MobileElement> mobileElementList = Driver.getAppiumDriver().findElementsByXPath("//android.widget.TextView[@text='" + text + "']");
        for (MobileElement el : mobileElementList) {
            if (el.getText().equals(text)) {
                waitToBeVisible(el, 10);
                if (el.isDisplayed()) {
                    elementFound = true;
                }
            }
        }
        return elementFound;
    }

    public static void wait(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void tapOn(MobileElement element) {
        waitToBeClickable(element, 10);
        element.click();
    }

    public static void enterText(MobileElement element, String text) {
        waitToBeClickable(element, 10);
        element.sendKeys(text);
    }

    public static void enterText(MobileElement element, String text, boolean needClear) {
        waitToBeClickable(element, 10);
        if (needClear) {
            element.clear();
        }
        element.sendKeys(text);
    }

    public static boolean isElementPresent(MobileElement mobileElement) {
        boolean elementFound = false;
        waitToBeVisible(mobileElement, 10);
        if (mobileElement.isDisplayed()) {
            elementFound = true;
        }
        return elementFound;
    }

    public static void waitToBeVisible(MobileElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getAppiumDriver(), timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitToBeClickable(MobileElement element, int timeout) {//tıklanır oluncaya kadar bekle
        WebDriverWait wait = new WebDriverWait(Driver.getAppiumDriver(), timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void scrollWithUiScrollable(String elementText) {
        AndroidDriver<MobileElement> driver = (AndroidDriver) Driver.getAppiumDriver();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+elementText+"\"))");
        tapOn(driver.findElementByXPath("//android.widget.TextView[@text='" + elementText + "']"));
    }

    public static void scrollDownTo() {
        Dimension dimension =Driver.getAppiumDriver().manage().window().getSize(); // cihazin olcusunu yani ekran olculerini aldik
        // baslangic olculeri
        int start_x = (int) (dimension.width * 0.5);
        int start_y = (int) (dimension.height * 0.8);
        // bitis noktasi
        int end_x = (int) (dimension.width*0.5);
        int end_y = (int) (dimension.height*0.2);
        TouchAction touchAction = new TouchAction<>(Driver.getAppiumDriver());

        touchAction.press(PointOption.point(start_x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(end_x,end_y)).release().perform();



    }

    public static void scrollUpToBeVisible(MobileElement element) {
        AndroidDriver<MobileElement> driver = (AndroidDriver) Driver.getAppiumDriver();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView()(text(\""+element+"\"))");
    }

    public static void swipeFromElementToElement(MobileElement el1, MobileElement el2) {

    }
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



    public static void longPressOElement(MobileElement mobileElement) {
        TouchAction touchAction = new TouchAction<>(Driver.getAppiumDriver());
        touchAction.longPress(LongPressOptions.longPressOptions()
                        .withElement(ElementOption.element(mobileElement)))
                .release()
                .perform();

    }

//attribute check


}
