package com.github.papayam.command;

import com.github.papayam.core.DriverManagerFactory;
import com.github.papayam.utils.ClickUtil;

public class ClickXYCommand implements Command {
    private String arg;

    public ClickXYCommand(String arg) {
        this.arg = arg;
    }

    public void execute() {
        String[] str = arg.split(",");
        if(str.length == 2) {
            new ClickUtil().clickByAbsolute(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(),Integer.valueOf(str[0]),Integer.valueOf(str[1]));
        }
    }
}
