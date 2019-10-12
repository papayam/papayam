package com.palfish.framework.command;

import com.palfish.framework.core.DriverManagerFactory;
import com.palfish.framework.utils.ClickUtil;
import com.palfish.framework.utils.ElementUtil;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;

public class ClickElementCommand implements Command {
    private String arg;

    public ClickElementCommand(String arg) {
        this.arg = arg;
    }

    private MobileElement getMobileElement(String[] str) {
        ElementUtil elementUtil = new ElementUtil();
        By by = elementUtil.getByLocator(str[0], str[1]);
        MobileElement mobileElement = elementUtil.findElement(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(),by,5);
        return mobileElement;
    }

    private MobileElement getMobileElementFromList(String[] str) {
        ElementUtil elementUtil = new ElementUtil();
        By by = elementUtil.getByLocator(str[0], str[1]);
        List<MobileElement> list = elementUtil.findElements(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(),by,5);
        if(Integer.valueOf(str[2]) <= list.size()) {
            MobileElement mobileElement = list.get(Integer.valueOf(str[2]));
            return mobileElement;
        } else {
            Assert.fail("第三个参数超出取值范围，页面该元素有"+list.size()+"个");
        }
        return null;
    }

    private void click(MobileElement mobileElement) {
        if(mobileElement != null) {
            mobileElement.click();
        }
    }

    private void click(MobileElement mobileElement, int x, int y) {
        if(mobileElement != null) {
            int x2 = mobileElement.getCenter().getX()+x;
            int y2 = mobileElement.getCenter().getY()+y;
            new ClickUtil().clickByAbsolute(DriverManagerFactory.getInstance().getDriverManager().getRunningDriver(),x2,y2);
        }
    }

    public void execute() {
        String[] str = arg.split(",");
        if(str.length == 2) {
            MobileElement mobileElement = getMobileElement(str);
            click(mobileElement);
        } else if(str.length == 3) {
            MobileElement mobileElement = getMobileElementFromList(str);
            click(mobileElement);
        } else if(str.length == 4) {
            int x = Integer.valueOf(str[2]);
            int y = Integer.valueOf(str[3]);
            MobileElement mobileElement = getMobileElement(str);
            click(mobileElement,x,y);
        } else if(str.length == 5) {
            int x = Integer.valueOf(str[3]);
            int y = Integer.valueOf(str[4]);
            MobileElement mobileElement = getMobileElementFromList(str);
            click(mobileElement,x,y);
        }
    }
}
