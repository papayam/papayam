package com.palfish.framework.core;

import com.palfish.framework.utils.Log;
import com.palfish.framework.utils.ScreenUtil;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class TestngListener extends TestListenerAdapter {
	private Log logger = Log.getLogger(TestngListener.class);

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		logger.debug(tr.getName() + " Failure");
		takeScreen();
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		logger.debug(tr.getName() + " Skipped");
		takeScreen();
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		logger.debug(tr.getName() + " Success");
	}

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		logger.debug(tr.getName() + " Start");
	}

	@Override
	public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
        logger.debug(testContext.getName() + " Finish");
	}

	private void takeScreen() {
		DriverManager driverManager = DriverManagerFactory.getInstance().getDriverManager();
		if(driverManager != null) {
			AppiumDriver driver = driverManager.getRunningDriver();
			if(driver != null) {
				String udid = String.valueOf(driver.getCapabilities().getCapability("udid"));
				new ScreenUtil().takeScreen(driver, udid);
			}
		}
	}
}