package com.papayam.framework.command;

import com.papayam.framework.core.DriverManagerFactory;
import com.papayam.framework.utils.SleepUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.util.HashMap;
import java.util.Map;

public class QuitDeviceCommand implements Command {
    private String arg;

    public QuitDeviceCommand() { }

    public QuitDeviceCommand(String arg) {
        this.arg = arg;
    }

    public void execute() {
        HashMap<String,AppiumDriver> driverHashMap = DriverManagerFactory.getInstance().getDriverManager().getDriverHashMap();
        if(arg == null || "".equals(arg)) {
            for (Map.Entry<String, AppiumDriver> entry : driverHashMap.entrySet()) {
                entry.getValue().quit();
            }
        } else {
            driverHashMap.get(arg).quit();
        }
        SleepUtil.sleep(1000);
        HashMap<String,AppiumDriverLocalService> serviceHashMap = DriverManagerFactory.getInstance().getDriverManager().getServiceHashMap();
        if(arg == null || "".equals(arg)) {
            for (Map.Entry<String, AppiumDriverLocalService> entry : serviceHashMap.entrySet()) {
                entry.getValue().stop();
            }
        } else {
            serviceHashMap.get(arg).stop();
        }
        SleepUtil.sleep(1000);
    }
}
