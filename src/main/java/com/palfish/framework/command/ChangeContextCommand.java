package com.palfish.framework.command;

import com.palfish.framework.core.DriverManagerFactory;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;

import java.util.Set;

public class ChangeContextCommand implements Command {
    private String arg;

    public ChangeContextCommand(String arg) {
        this.arg = arg;
    }

    public void execute() {
        AppiumDriver driver = DriverManagerFactory.getInstance().getDriverManager().getRunningDriver();
        Set<String> contextNames = driver.getContextHandles();
        arg = arg.toUpperCase();
        for(String contextName : contextNames){
            if (contextName.contains(arg)) {
                driver.context(contextName);
                return;
            }
        }
        Assert.fail("切换context失败，context不存在");
    }
}
