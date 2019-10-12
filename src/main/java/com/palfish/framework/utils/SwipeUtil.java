package com.palfish.framework.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.time.Duration;

public class SwipeUtil {
	private Log logger=Log.getLogger(SwipeUtil.class);
	private AppiumDriver driver;
	private int appWidth;
	private int appHeight;
	/**
	 * 构造方法，对三个变量进行初始化
	 * @param driver
	 */
	public SwipeUtil(AppiumDriver driver){
		this.driver=driver;
		this.appWidth=driver.manage().window().getSize().width;
		this.appHeight=driver.manage().window().getSize().height;
	}
	/**
	 * 向左滑动
	 * @param duration 滑动时间，单位是毫秒
	 * @throws RuntimeException
	 */
	@SuppressWarnings("deprecation")
	private void swipeToLeft(int duration){
		int startx=appWidth*9/10;
		int endx=appWidth*1/10;
		int y=appHeight*6/10;
		try {
			this.swipe(startx,y,endx,y,duration);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("swipeToLeft is failure -> "+e.getMessage());
			throw new RuntimeException("swipeToLeft is failure -> "+e.getMessage());
		}
	}
	/**
	 * 向右滑动
	 * @param duration 滑动时间，单位毫秒
	 * @throws RuntimeException
	 */
	@SuppressWarnings("deprecation")
	private void swipeToRight(int duration){
		int startx=appWidth*3/10;
		int endx=appWidth*5/10;
		int y=appHeight*5/10;
		try {
			this.swipe(startx,y,endx,y,duration);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("swipeToRight is failure -> "+e.getMessage());
			throw new RuntimeException("swipeToRight is failure -> "+e.getMessage());
		}
	}
	/**
	 * 向上滑动
	 * @param duration 持续时间，单位毫秒
	 * @throws RuntimeException
	 */
	@SuppressWarnings("deprecation")
	private void swipeToUp(int duration){
		int starty=appHeight*9/10;
		int endy=appHeight*3/10;
		int x=appWidth*5/10;
		try {
			this.swipe(x,starty,x,endy,duration);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("swipeToUp is failure -> "+e.getMessage());
			throw new RuntimeException("swipeToUp is failure -> "+e.getMessage());
			
		}
	}
	/**
	 * 向下滑动
	 * @param duration 持续时间，单位毫秒
	 * @throws RuntimeException
	 */
	@SuppressWarnings("deprecation")
	private void swipeToDown(int duration){
		int starty=appHeight*3/10;
		int endy=appHeight*9/10;
		int x=appWidth*4/10;
		try {
			this.swipe(x,starty,x,endy,duration);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("swipeToDown is failure -> "+e.getMessage());
			throw new RuntimeException("swipeToDown is failure -> "+e.getMessage());
		}
	}
	/**
	 * 滑动方法，通过参数实现各方向滑动
	 * @param direction 方向参数，值为"up"、"down"、"left"、"right"
	 * @param duration 滑动时间，单位毫秒
	 * @throws RuntimeException
	 */
	public void swipe(String direction,int duration){
		String direc=direction.toLowerCase();
		switch(direc){
		case "up":
			this.swipeToUp(duration);
			break;
		case "down":
		    this.swipeToDown(duration);
		    break;
		case "left":
			this.swipeToLeft(duration);
			break;
		case "right":
			this.swipeToRight(duration);
			break;
		default:
			System.out.println("方向参数错误");
			logger.error("direction of swipe,direction must is up or down or left or right");
			throw new RuntimeException("direction of swipe,direction must is up or down or left or right");
		}
	}
	
	public void swipe(int startx,int starty,int endx,int endy,int duration){
		Duration dur=Duration.ofMillis(duration);
		TouchAction ta=new TouchAction(driver);
		ta.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(dur)).moveTo(PointOption.point(endx, endy)).release().perform();
	}

	public void swipe(MobileElement element,String direction, int duration) {
		Point start=element.getLocation();
		int startX=start.getX();
		int startY=start.getY();

		Dimension size=element.getSize();
		int width=size.getWidth();
		int height=size.getHeight();

		int endX=startX+width;
		int endY=startY+height;

		int deviationX = width*4/10;
		int deviationY = height*4/10;

		if("x".equalsIgnoreCase(direction)) {
            int y = startY+deviationY;
            while(y < endY-deviationY) {
                this.swipe(startX+deviationX, y, endX-deviationX, y, duration);
                y += 5;
				SleepUtil.sleep(500);
            }
        } else {
            int x = startX+deviationX;
            while(x < endX-deviationX) {
                this.swipe(x, startY+deviationY, x, endY-deviationY, duration);
                x += 5;
				SleepUtil.sleep(500);
            }
        }
	}

	public void swipeOnElement(AndroidElement element,String direction){
		swipeOnElement(element, direction, 10, 10);
		
	}
	public void swipeOnElement(AndroidElement element,String direction,int offsetfrom,int offsetend){
		swipeOnElement(element, direction, offsetfrom, offsetend,1000);
	}
	
	/**
	 * 传持续时间、元素对象、滑动方向
	 */
	public void swipeOnElement(AndroidElement element,String direction,int duration){
		
		swipeOnElement(element, direction, 10, 10, duration);
	}
	/**
	 * 基于元素上的滑动
	 * @param element
	 * @param direction
	 * @param offsetfrom
	 * @param offsetend
	 * @param duration
	 */
	public void swipeOnElement(MobileElement element,String direction,int offsetfrom,int offsetend,int duration){
		//获取元素起始点坐标
		Point p=element.getLocation();
		int startx=p.getX();
		int starty=p.getY();
		
		//获取元素的宽和高
		Dimension dim=element.getSize();
		int width=dim.getWidth();
		int height=dim.getHeight();
		
		//获取元素的中心点坐标
		Point centerp=element.getCenter();
		int centerx=centerp.getX();
		int centery=centerp.getY();
		
		//计算出元素的结束点坐标
		int endx=startx+width;
		int endy=starty+height;
		switch (direction.toLowerCase()) {
		case "up":
			this.swipe(centerx, endy-offsetfrom, centerx, starty+offsetend, duration);
			break;
		case "down":
			this.swipe(centerx, starty+offsetfrom, centerx,endy-offsetend , duration);
			break;
		case "left":
			this.swipe(endx-offsetfrom, centery, startx+offsetend,centery, duration);
			break;
		case "right":
			this.swipe(startx+offsetfrom, centery,endx-offsetend,centery, duration);
			break;
		default:
			new RuntimeException("方向参数有误");
			break;
		}
		
	}

	
}
