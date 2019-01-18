package com.github.papayam.command;

import com.github.papayam.core.DriverManagerFactory;
import com.github.papayam.utils.ElementUtil;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;

public class ClearElementCommand implements Command {
    private String arg;

    public ClearElementCommand(String arg) {
        this.arg = arg;
    }

    public void execute() {
        String[] str = arg.split(",");
        ElementUtil elementUtil = new ElementUtil();
        if(str.length == 2) {
            By by = elementUtil.getByLocator(str[0], str[1]);
            MobileElement mobileElement = elementUtil.findElement(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(),by,5);
            if(mobileElement != null) {
                mobileElement.clear();
            }
        } else if(str.length == 3) {
            By by = elementUtil.getByLocator(str[0], str[1]);
            List<MobileElement> list = elementUtil.findElements(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(),by,5);
            if(Integer.valueOf(str[2]) <= list.size()) {
                MobileElement mobileElement = list.get(Integer.valueOf(str[2]));
                if(mobileElement != null) {
                    mobileElement.clear();
                }
            } else {
                Assert.fail("第三个参数超出取值范围，页面该元素有"+list.size()+"个");
            }
        }
    }
}
