package com.papayam.framework.core;

import com.papayam.framework.utils.Log;
import com.papayam.framework.utils.ScreenUtil;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class TestngListener extends TestListenerAdapter {
	private Log logger = Log.getLogger(TestngListener.class);

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		logger.debug(tr.getName() + " Failure");
		String udid = String.valueOf(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver().getCapabilities().getCapability("udid"));
        new ScreenUtil().takeScreen(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(), udid);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		logger.debug(tr.getName() + " Skipped");
        String udid = String.valueOf(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver().getCapabilities().getCapability("udid"));
        new ScreenUtil().takeScreen(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(), udid);
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
}