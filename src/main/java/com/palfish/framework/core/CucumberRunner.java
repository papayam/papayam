package com.palfish.framework.core;

import com.palfish.framework.command.QuitDeviceCommand;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CucumberRunner extends AbstractTestNGCucumberTests {
    @BeforeMethod
    public void beforeMethod() {
        DriverManagerFactory.getInstance().addDriverManager();
    }

    @AfterMethod
    public void afterMethod() {
        QuitDeviceCommand command = new QuitDeviceCommand();
        command.execute();
    }
}
