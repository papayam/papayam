package com.palfish.framework.command;

import com.palfish.framework.core.DriverManagerFactory;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.HideKeyboardStrategy;

public class HideKeyboardIOSCommand implements Command {
    private String arg;

    public HideKeyboardIOSCommand(String arg) {
        this.arg = arg;
    }

    public void execute() {
        IOSDriver driver = (IOSDriver)DriverManagerFactory.getInstance().getDriverManager().getRunningDriver();
        driver.hideKeyboard(HideKeyboardStrategy.PRESS_KEY, arg);
    }
}
