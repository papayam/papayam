package com.palfish.framework.command;

import com.palfish.framework.core.DriverManagerFactory;
import com.palfish.framework.utils.ElementUtil;
import com.palfish.framework.utils.Log;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;

public class AssertExistCommand implements Command {
    private static Log logger = Log.getLogger(AssertExistCommand.class);
    private String arg;
    private boolean isElementExist = true;

    public AssertExistCommand(String arg) {
        this.arg = arg;
    }

    public AssertExistCommand(String arg, boolean isElementExist) {
        this.arg = arg;
        this.isElementExist = isElementExist;
    }

    public void execute() {
        String[] str = arg.split(",");
        ElementUtil elementUtil = new ElementUtil();
        if(str.length == 2) {
            By by = elementUtil.getByLocator(str[0], str[1]);
            boolean rs = elementUtil.isElementExist(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(),by,5);
            Assert.assertEquals(rs, isElementExist);
        } else if(str.length == 3) {
            By by = elementUtil.getByLocator(str[0], str[1]);
            List<MobileElement> list = elementUtil.findElements(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(),by,5);
            if(Integer.valueOf(str[2]) <= list.size()) {
                Assert.assertTrue(true);
            } else if(isElementExist) {
                Assert.fail("第三个参数超出取值范围，页面该元素有"+list.size()+"个");
            } else {
                Assert.assertTrue(true);
            }
        }
    }
}
