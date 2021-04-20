package com.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class EtsyTests {

    private AppiumDriver<AndroidElement> driver;

    @Before
    public void setup() throws MalformedURLException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "7.0");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "https://cybertek-appium.s3.amazonaws.com/etsy.apk");
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20000);


        URL url = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver<>(url, desiredCapabilities);
    }

    @Test
    public void test() throws InterruptedException {
        AndroidElement getStarted = driver.findElementById("com.etsy.android:id/btn_link");
        Thread.sleep(3000);
        getStarted.click(); //click get started
        Thread.sleep(3000);
        //enter email
        driver.findElementById("com.etsy.android:id/edit_username").sendKeys("areatha@uspeakw.com");
        //enter password
        driver.findElementById("com.etsy.android:id/edit_password").sendKeys("Cybertek2020");
        //click sign in
        Thread.sleep(3000);
        //
        //com.etsy.android:id/search_plate
        //com.etsy.android:id/search_src_text
        driver.findElementById("com.etsy.android:id/button_signin").click();
        //click search
        Thread.sleep(3000);
        driver.findElementByAccessibilityId("Show Navigation Drawer").click();
        //enter item description
        Thread.sleep(3000);
        //text to enter
        String str = "weshirtdesign";
        //search input box
        AndroidElement e = driver.findElementById("com.etsy.android:id/search_src_text");
        //we creating actions class object
        Actions actions = new Actions(driver);
        //if try to use senKeys() from element, we are getting StaleElement exception
        //entering character by character
        for (int i = 0; i < str.length(); i++) {
            actions.sendKeys(e, (str.substring(i, i + 1))).perform();
        }
        driver.findElementById("com.etsy.android:id/shop_name").click();
        Thread.sleep(5000);
    }

    @After
    public void tearDown() {
        driver.closeApp();
    }
}
