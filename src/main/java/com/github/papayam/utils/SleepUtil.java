package com.github.papayam.utils;

public class SleepUtil {

	public static void sleep(long milliSecond) {
		try {
			Thread.sleep(milliSecond);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
