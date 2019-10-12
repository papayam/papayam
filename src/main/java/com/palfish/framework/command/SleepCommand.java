package com.palfish.framework.command;

import com.palfish.framework.utils.SleepUtil;

public class SleepCommand implements Command {
    private String arg;

    public SleepCommand(String arg) {
        this.arg = arg;
    }

    public void execute() {
        SleepUtil.sleep(Long.valueOf(arg)*1000);
    }
}
