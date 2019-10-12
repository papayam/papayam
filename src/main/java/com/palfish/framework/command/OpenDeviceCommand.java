package com.palfish.framework.command;

import com.palfish.framework.core.AppiumDriverFactory;
import com.palfish.framework.core.AppiumServiceFactory;
import com.palfish.framework.core.DriverManagerFactory;
import com.palfish.framework.utils.CapabilitiesUtil;
import com.palfish.framework.utils.SleepUtil;
import com.palfish.framework.utils.SystemPortUtil;
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
