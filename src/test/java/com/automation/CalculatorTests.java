package com.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.aspectj.weaver.ast.And;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class CalculatorTests {
    /**
     * //
     * {
     * "platformName": "Android",
     * "platformVersion": "7.0",
     * "deviceName": "Pixel_2",
     * "automationName": "UiAutomator2",
     * "appPackage": "com.android.calculator2",
     * "appActivity": "com.android.calculator2.Calculator"
     * }
     *
     * @throws MalformedURLException
     */
    AppiumDriver<AndroidElement> driver;

    @Before
    public void setup() throws MalformedURLException {
        //list of all capabilities: http://appium.io/docs/en/writing-running-appium/caps/index.html
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //which framework to use for automation
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        //platform of device under test
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        //specify android version
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
        //specify device name
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
        //app or appActivity and appPackage name
        // option 1: new apps
        // option 2: preinstalled apps, e.g. calculator
        //appActivity - path to the Main class that runs program (com.google.calculator.Main)
        //appPackage - path that leads to the main class (com.google.calculator)
        //these are the capabilities for Calculator app
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.calculator2");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.calculator2.Calculator");
        //appium driver --> appium server --> device
        //specify address of appium server
        //if appium server runs on the same computer, address is always localhost
        //sometimes, you will see 0.0.0.0 or 127.0.0.1 instead of localhost
        URL url = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver<>(url, desiredCapabilities);
        //WebDriver --> RemoteWebDriver --> DefaultGenericMobileDriver --> AppiumDriver --> AndroidDriver
        //Question: what's the relationship between ChromeDriver and AppiumDriver?
        //- they are cousins. both of them are children of WebDriver.
    }

    @Test
    public void firstTest() {
        //find element of button 2
        AndroidElement btn2 = driver.findElement(By.id("com.android.calculator2:id/digit_2"));
        AndroidElement plus = driver.findElement(MobileBy.AccessibilityId("plus"));
        AndroidElement equals = driver.findElementByAccessibilityId("equals");
        AndroidElement result = driver.findElementById("com.android.calculator2:id/result");

        // what if we need to wait for visibility, clickable
        WebDriverWait wait = new WebDriverWait(driver,10);
        // wait until we can click button 2
        wait.until(ExpectedConditions.elementToBeClickable(btn2));

        // how we can tap instead of click
        TouchAction touchAction = new TouchAction(driver);
    //    touchAction.tap(new TapOptions().withElement(new ElementOption().withElement(btn2))).perform();
     //   btn2.click();

        btn2.click();// 2
        plus.click();// +
        btn2.click();// 2
        equals.click();// =

        String actualResult = result.getText();
        String expectedResult = "4";

        Assert.assertEquals(expectedResult, actualResult);
    }

    @After
    public void tearDown() {
        driver.closeApp();
    }
}
