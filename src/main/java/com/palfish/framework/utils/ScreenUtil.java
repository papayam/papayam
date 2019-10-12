package com.palfish.framework.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenUtil {
    private Log logger = Log.getLogger(ScreenUtil.class);
    private String screen_shot_root = System.getProperty("user.dir") + File.separator + "screenshot";

    // 截图全屏
    public String takeScreen(AppiumDriver driver, String fileName) {
        if(fileName == null || "".equalsIgnoreCase(fileName)) {
            fileName = String.valueOf(System.currentTimeMillis());
        }
        String pathname = screen_shot_root + File.separator + fileName + ".png";
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(pathname));
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("截取全屏图片失败");
        }
        logger.debug("takeScreen on " + pathname);
        screenshotToAllure(pathname);
        return pathname;
    }

    // 针对元素进行截图
    public String takeScreenForElement(AppiumDriver driver, MobileElement element, String fileName) {
        if(fileName == null || "".equalsIgnoreCase(fileName)) {
            fileName = String.valueOf(System.currentTimeMillis());
        }
        String pathname = screen_shot_root + File.separator + fileName + ".png";
        // 获得element的位置和大小
        Point location = element.getLocation();
        Dimension size = element.getSize();
        byte[] imageByte = driver.getScreenshotAs(OutputType.BYTES);
        // 创建全屏截图
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new ByteArrayInputStream(imageByte));
            logger.debug("x:"+location.getX()+",y:"+location.getY()+",width:"+size.getWidth()+",height:"+size.getHeight());
            logger.debug("width:"+driver.manage().window().getSize().width+",height:"+driver.manage().window().getSize().height);
            logger.debug("width:"+originalImage.getWidth()+",height:"+originalImage.getHeight());
            // 截取element所在位置的子图。
            double w1 = (double) originalImage.getWidth();
            double w2 = (double) driver.manage().window().getSize().width;
            double x = w1/w2;
            double getX = location.getX()*x;
            double getWidth = size.getWidth()*x;
            double h1 = (double) originalImage.getHeight();
            double h2 = (double) driver.manage().window().getSize().height;
            double y = h1/h2;
            double getY = location.getY()*y;
            double getHeight = size.getHeight()*y;
            logger.debug("getX:"+getX+",getY:"+getY+",getWidth:"+getWidth+",getHeight:"+getHeight);
            BufferedImage croppedImage = originalImage.getSubimage((int)getX, (int)getY, (int)getWidth, (int)getHeight);
            ImageIO.write(croppedImage, "png", new File(pathname));
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("针对元素进行截图失败");
        }
        logger.debug("takeScreenForElement on " + pathname);
        screenshotToAllure(pathname);
        return pathname;
    }

    public void screenshotToAllure(String fileName) {
        Path content = Paths.get(fileName);
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("ScreenHot", is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String takeScreen(AppiumDriver driver) {
        return takeScreen(driver, null);
    }

    public String takeScreenForElement(AppiumDriver driver, MobileElement element) {
        return takeScreenForElement(driver, element, null);
    }

}
