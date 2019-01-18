package com.github.papayam.command;

import com.github.papayam.utils.SleepUtil;

public class SleepCommand implements Command {
    private String arg;

    public SleepCommand(String arg) {
        this.arg = arg;
    }

    public void execute() {
        SleepUtil.sleep(Long.valueOf(arg)*1000);
    }
}
