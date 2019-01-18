package com.github.papayam.command;

import com.github.papayam.core.AppiumDriverFactory;
import com.github.papayam.core.AppiumServiceFactory;
import com.github.papayam.core.DriverManagerFactory;
import com.github.papayam.utils.CapabilitiesUtil;
import com.github.papayam.utils.SleepUtil;
import com.github.papayam.utils.SystemPortUtil;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;

public class OpenDeviceCommand implements Command {
    private String arg;

    public OpenDeviceCommand(String arg) {
        this.arg = arg;
    }

    public void execute() {
        SystemPortUtil serverPort = new SystemPortUtil();
        SystemPortUtil wdaLocalPort = new SystemPortUtil();
        SystemPortUtil bootstrapPort = new SystemPortUtil();
        SystemPortUtil chromeDriverPort = new SystemPortUtil();
        SystemPortUtil systemPort = new SystemPortUtil();
        CapabilitiesUtil capabilitiesUtil = new CapabilitiesUtil(arg,serverPort,wdaLocalPort,bootstrapPort,chromeDriverPort,systemPort);
        AppiumServiceFactory appiumServiceFactory = new AppiumServiceFactory(capabilitiesUtil.getCapabilities());
        appiumServiceFactory.getService().start();
        SleepUtil.sleep(1000);
        AppiumDriverFactory driverFactory = new AppiumDriverFactory(capabilitiesUtil.getCapabilities());
        AppiumDriver driver = driverFactory.getDriver();
        SleepUtil.sleep(1000);
        Assert.assertNotNull(driver,"AppiumDriver不能为空");
        DriverManagerFactory.getInstance().getDriverManager().addDriverHashMap(arg,driver,appiumServiceFactory.getService());
    }
}
