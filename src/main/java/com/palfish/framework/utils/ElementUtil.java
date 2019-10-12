package com.palfish.framework.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;

public class ElementUtil {
    private Log logger = Log.getLogger(ElementUtil.class);

    public By getByLocator(String type, String value) {
        logger.info("Get element locator type by "+type+" with value : "+value );
        By by = null;
        switch (type) {
            case "id":
                by = By.id(value);
                break;
            case "xpath":
                by = By.xpath(value);
                break;
            case "accessibility":
                by = MobileBy.AccessibilityId(value);
                break;
            case "uiautomator":
                by = MobileBy.AndroidUIAutomator(value);
                break;
            case "classname":
                by = By.className(value);
                break;
            case "linktext":
                by = By.linkText(value);
                break;
            case "partiallinktext":
                by = By.partialLinkText(value);
                break;
            case "cssselector":
                by = By.cssSelector(value);
                break;
            case "iosnspredicate":
                by = MobileBy.iOSNsPredicateString(value);
                break;
        }
        return by;
    }

    /**
     * 查找元素,存在则返回该元素对象，不存在则报错
     *
     * @param driver
     * @return
     */
    private MobileElement findElement(AppiumDriver driver, By by) {
        try {
            MobileElement element = (MobileElement) driver.findElement(by);
            logger.debug("element found-->");
            return element;
        } catch (Exception e) {
            // TODO: handle exception
            logger.debug("element not found-->");
            logger.debug(e.getMessage());
            return null;
        }
    }

    /**
     * 在指定超时时间内查找元素是否存在，如存在则立即返回MobileElement对象，如不存在则在超时后返回null
     *
     * @param driver
     * @param timeout
     *            单位是秒
     * @return
     */
    public MobileElement findElement(AppiumDriver driver, By by, int timeout) {
        MobileElement element = null;
        for(int i = 0; i < timeout*2; i++) {
            element = findElement(driver, by);
            if(element == null) {
                logger.debug("wait 500ms...");
                SleepUtil.sleep(500);
            } else {
                break;
            }
        }
        return element;
    }

    private List<MobileElement> findElements(AppiumDriver driver, By by) {
        try {
            List<MobileElement> elements = driver.findElements(by);
            logger.info("elements found-->");
            return elements;
        } catch (Exception e) {
            // TODO: handle exception
            logger.debug("elements not found-->");
            logger.debug(e.getMessage());
            return null;
        }
    }

    public List<MobileElement> findElements(AppiumDriver driver, By by, int timeout) {
        List<MobileElement> list = null;
        for(int i = 0; i < timeout*2; i++) {
            list = findElements(driver, by);
            if(list == null || list.size() < 1) {
                logger.debug("wait 500ms...");
                SleepUtil.sleep(500);
            } else {
                break;
            }
        }
        return list;
    }

    public boolean isElementExist(AppiumDriver driver, By by, int timeout) {
        MobileElement mobileElement = findElement(driver, by, timeout);
        if(mobileElement != null) {
            return true;
        } else {
            return false;
        }
    }
}
