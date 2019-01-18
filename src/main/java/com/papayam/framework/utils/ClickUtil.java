package com.papayam.framework.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

public class ClickUtil {
    private Log logger = Log.getLogger(ClickUtil.class);

    public void clickByAbsolute(AppiumDriver driver, int x, int y) {
        try {
            TouchAction ta = new TouchAction(driver);
            ta.tap(PointOption.point(x, y)).release().perform();
            logger.debug("clickByCoordinate Successed on " + x + "," + y);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void longPressByAbsolute(AppiumDriver driver, int x, int y, int duration) {
        try {
            TouchAction ta = new TouchAction(driver);
            Duration dur=Duration.ofMillis(duration);
            LongPressOptions longPressOptions = LongPressOptions.longPressOptions().withPosition(PointOption.point(x, y)).withDuration(dur);
            ta.longPress(longPressOptions).release().perform();
            logger.info("longPress Successed on " + x + "," + y);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
