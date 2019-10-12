package com.palfish.framework.command;

import com.palfish.framework.core.DriverManagerFactory;
import com.palfish.framework.utils.ElementUtil;
import com.palfish.framework.utils.ScreenUtil;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.Assert;

public class TakeScreenCommand implements Command {
    private String arg;

    public TakeScreenCommand(String arg) {
        this.arg = arg;
    }

    public void execute() {
        String[] str = arg.split(",");
        ElementUtil elementUtil = new ElementUtil();
        ScreenUtil screenUtil = new ScreenUtil();
        if(str.length == 1) {
            screenUtil.takeScreen(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(),str[0]);
        } else if(str.length == 2) {
            By by = elementUtil.getByLocator(str[0], str[1]);
            MobileElement mobileElement = elementUtil.findElement(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(),by,5);
            Assert.assertNotNull(mobileElement);
            screenUtil.takeScreenForElement(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(),mobileElement,null);
        } else if(str.length == 3) {
            By by = elementUtil.getByLocator(str[0], str[1]);
            MobileElement mobileElement = elementUtil.findElement(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(),by,5);
            Assert.assertNotNull(mobileElement);
            screenUtil.takeScreenForElement(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(),mobileElement,str[2]);
        }
    }
}
