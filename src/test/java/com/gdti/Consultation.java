package com.gdti;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Consultation {
    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Consultation";
    protected AndroidDriver<AndroidElement> driver = null;
    private static int latestId = 0;
    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws MalformedURLException {
        dc.setCapability("noReset", "true");
        dc.setCapability("fullReset", "false");
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
//        dc.setCapability(MobileCapabilityType.UDID, "RR8M50AK3YL");
        dc.setCapability(MobileCapabilityType.UDID, "HQHY6HOFQWPRWWHU");
//        dc.setCapability("deviceId", "10.143.20.30:5555");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "SamsungA20");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,"true");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.grabtaxi.passenger");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.grab.pax.newface.presentation.newface.NewFace");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
    }

    @Test
    public void testConsultation() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try{Thread.sleep(10000);} catch(Exception ignore){}
        takeScreenShot();
        driver.findElement(By.xpath("//android.widget.TextView[@text='More']")).click();
        try{Thread.sleep(500);} catch(Exception ignore){}
        takeScreenShot();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Health']")).click();
        try{Thread.sleep(12000);} catch(Exception ignore){}
        takeScreenShot();
        driver.findElement(By.xpath("//android.view.View[contains(@text,'Consultation')]")).click();
        try{Thread.sleep(500);} catch(Exception ignore){}
        takeScreenShot();
        driver.findElement(By.xpath("//android.view.View[contains(@text,'Free')]")).click();
        try{Thread.sleep(1500);} catch(Exception ignore){}
        takeScreenShot();
        driver.findElement(By.xpath("//android.view.View[@text='Confirm']")).click();
        try{Thread.sleep(5000);} catch(Exception ignore){}
        takeScreenShot();
        driver.findElement(By.xpath("//android.widget.Button[@text='End']")).click();
        try{Thread.sleep(2000);} catch(Exception ignore){}
        takeScreenShot();
        driver.findElement(By.xpath("//android.widget.Button[contains(@text,'Yes')]")).click();
        try{Thread.sleep(2000);} catch(Exception ignore){}
        takeScreenShot();
        try{Thread.sleep(1000);} catch(Exception ignore){}
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    public void takeScreenShot() {
        // Set folder name to store screenshots.
        String destDir = "screenshots";
        // Capture screenshot.
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Create folder under project with name "screenshots" provided to destDir.
        new File(destDir).mkdirs();
        try {
            // Copy paste file at destination folder location
            FileUtils.copyFile(scrFile, new File(destDir + "/Automation-" + generateId() + ".png" ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int generateId() {
        return ++latestId;
    }

}
