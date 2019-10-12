package com.palfish.framework.core;

import com.palfish.framework.utils.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.util.HashMap;

public class DriverManager {
    private Log logger = Log.getLogger(DriverManager.class);
    private HashMap<String,AppiumDriver> driverHashMap = new HashMap<>();
    private HashMap<String,AppiumDriverLocalService> serviceHashMap = new HashMap<>();
    private AppiumDriver runningDriver;
    private AppiumDriverLocalService runningService;

    public AppiumDriver getRunningDriver() {
        return runningDriver;
    }

    public AppiumDriverLocalService getRunningService() {
        return runningService;
    }

    public HashMap<String,AppiumDriver> getDriverHashMap() { return driverHashMap; }

    public HashMap<String,AppiumDriverLocalService> getServiceHashMap() {
        return serviceHashMap;
    }

    public DriverManager changeRunningDriver(String deviceName) {
        logger.debug("changeRunningDriver:"+deviceName);
        runningDriver = driverHashMap.get(deviceName);
        runningService = serviceHashMap.get(deviceName);
        return this;
    }

    public DriverManager addDriverHashMap(String deviceName, AppiumDriver driver, AppiumDriverLocalService service) {
        driverHashMap.put(deviceName, driver);
        serviceHashMap.put(deviceName, service);
        runningDriver = driver;
        runningService = service;
        return this;
    }
}
