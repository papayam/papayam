package com.github.papayam.core;

import com.github.papayam.utils.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;


public class AppiumDriverFactory {

	private Log logger = Log.getLogger(AppiumDriverFactory.class);
	private DesiredCapabilities desiredCapabilities;
	private AppiumDriver driver;
	private String serverPort;
	public String platformName;

	public AppiumDriver getDriver() {
		return driver;
	}

	public AppiumDriverFactory(DesiredCapabilities capabilities) {
		this.desiredCapabilities = capabilities;
		this.platformName = String.valueOf(desiredCapabilities.getCapability("platformName"));
		this.serverPort = String.valueOf(desiredCapabilities.getCapability("serverPort"));
		this.driver = initDriver();
	}

	private AppiumDriver initDriver() {
		try {
			if (MobilePlatform.ANDROID.equalsIgnoreCase(platformName)) {
				return initAndroidDriver();
			} else if (MobilePlatform.IOS.equalsIgnoreCase(platformName)) {
				return initIosDriver();
			} else {
				logger.error("paltform is error, platform must is android or ios");
				throw new Exception("paltform is error, platform must is android or ios");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	private AppiumDriver<AndroidElement> initAndroidDriver() throws Exception {
		AppiumDriver driver = new AndroidDriver<AndroidElement>(
				new URL( "http://127.0.0.1:" + this.serverPort + "/wd/hub"), this.desiredCapabilities);
		return driver;
	}

	private AppiumDriver<IOSElement> initIosDriver() throws Exception {
		AppiumDriver driver = new IOSDriver<IOSElement>(
				new URL( "http://127.0.0.1:" + this.serverPort + "/wd/hub"), this.desiredCapabilities);
		return driver;
	}
}
