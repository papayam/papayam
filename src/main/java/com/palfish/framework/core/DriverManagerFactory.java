package com.palfish.framework.core;

import org.testng.Assert;

import java.util.HashMap;

public class DriverManagerFactory {
    private static DriverManagerFactory instance = new DriverManagerFactory();
    private HashMap<Long,DriverManager> driverManagerHashMap = new HashMap<>();

    private DriverManagerFactory() { }

    public static DriverManagerFactory getInstance() {
        return instance;
    }

    public DriverManagerFactory addDriverManager() {
        DriverManager driverManager = new DriverManager();
        driverManagerHashMap.put(Thread.currentThread().getId(),driverManager);
        return this;
    }

    public DriverManager getDriverManager() {
        DriverManager driverManager = driverManagerHashMap.get(Thread.currentThread().getId());
        Assert.assertNotNull(driverManager,"DriverManager不能为空");
        return driverManager;
    }
}
