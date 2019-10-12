package com.palfish.framework.command;

import com.palfish.framework.core.DriverManagerFactory;

public class ChangeDeviceCommand implements Command {
    private String arg;

    public ChangeDeviceCommand(String arg) {
        this.arg = arg;
    }

    public void execute() {
        DriverManagerFactory.getInstance().getDriverManager().changeRunningDriver(arg);
    }
}
