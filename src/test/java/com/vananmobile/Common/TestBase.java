package com.vananmobile.Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileBrowserType;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.remote.DesiredCapabilities;


public class TestBase {

    public static File file;
    public static FileInputStream fileInput;
    public FileOutputStream fileOutput;

    public static HSSFWorkbook workbook;
    public static HSSFSheet sheet;
    public static HSSFCell cell;

    public DesiredCapabilities capabilities;
    public WebDriver driver;

    /*private String platform;
    private String platformVersion;
    private String deviceName;
    private String bName;
    private String appiumUrl;*/

    private String  platform = "Android";
    private String  platformVersion = "7.0";
    private String deviceName = "33001f8921b473eb";
    private String  bName = "chrome";
    private String  appiumUrl = "http://0.0.0.0:4724/wd/hub";

    private void getConfiguration() {

        /*platform = System.getProperty("platformName");
        platformVersion = System.getProperty("platformVersion");
        deviceName = System.getProperty("deviceName");
        bName = System.getProperty("browserName");*/

    }

    public void setCapabilities() {
        getConfiguration();
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("deviceName", deviceName);
        setBrowser(bName);
        initDriver();
        System.out.println("Done");
    }

    private void initDriver() {
        try {
            if (platform.equals("Android")) {
                driver = new AndroidDriver(new URL("http://0.0.0.0:4724/wd/hub"), capabilities);
            } else if (platform.equals("ios")) {
                driver = new IOSDriver(new URL("http://0.0.0.0:4724/wd/hub"), capabilities);
            }
            driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        } catch (MalformedURLException ex) {
            System.out.println("Driver initialization problem : " + ex);
        }
    }

    private void setBrowser(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            capabilities.setCapability("browserName", MobileBrowserType.CHROME);
        } else if (browserName.equalsIgnoreCase("safari")) {
            capabilities.setCapability("browserName", MobileBrowserType.SAFARI);
        } else if (browserName.equalsIgnoreCase("chromium")) {
            capabilities.setCapability("browserName", MobileBrowserType.CHROMIUM);
        }
    }
}
