package com.papayam.framework.command;

import com.papayam.framework.core.DriverManagerFactory;

public class ChangeDeviceCommand implements Command {
    private String arg;

    public ChangeDeviceCommand(String arg) {
        this.arg = arg;
    }

    public void execute() {
        DriverManagerFactory.getInstance().getDriverManager().changeRunningDriver(arg);
    }
}
