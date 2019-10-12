package com.palfish.framework.command;

import com.palfish.framework.core.DriverManagerFactory;
import com.palfish.framework.utils.ElementUtil;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;

public class AssertSizeCommand implements Command {
    private String arg;

    public AssertSizeCommand(String arg) {
        this.arg = arg;
    }

    public void execute() {
        String[] str = arg.split(",");
        ElementUtil elementUtil = new ElementUtil();
        if(str.length == 3) {
            By by = elementUtil.getByLocator(str[0], str[1]);
            List<MobileElement> list = elementUtil.findElements(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(),by,5);
            Assert.assertEquals(String.valueOf(list.size()),str[2]);
        }
    }
}
