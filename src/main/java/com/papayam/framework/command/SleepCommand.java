package com.papayam.framework.command;

import com.papayam.framework.utils.SleepUtil;

public class SleepCommand implements Command {
    private String arg;

    public SleepCommand(String arg) {
        this.arg = arg;
    }

    public void execute() {
        SleepUtil.sleep(Long.valueOf(arg)*1000);
    }
}
