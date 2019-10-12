package com.palfish.framework.command;

import com.palfish.framework.core.DriverManagerFactory;
import com.palfish.framework.utils.SwipeUtil;

public class SwipeCommand implements Command {
    private String arg;

    public SwipeCommand(String arg) {
        this.arg = arg;
    }

    public void execute() {
        String[] str = arg.split(",");
        if(str.length == 2) {
            SwipeUtil swipeUtil = new SwipeUtil(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver());
            swipeUtil.swipe(str[0],Integer.valueOf(str[1]));
        } else if(str.length == 5) {
            SwipeUtil swipeUtil = new SwipeUtil(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver());
            swipeUtil.swipe(Integer.valueOf(str[0]),Integer.valueOf(str[1]),Integer.valueOf(str[2]),Integer.valueOf(str[3]),Integer.valueOf(str[4]));
        }
    }
}
