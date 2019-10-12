package com.palfish.framework.utils;

import com.palfish.framework.core.ShareDataManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class NumberUtil {
	private Log logger = Log.getLogger(NumberUtil.class);

	public String generateTime() {
		SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
		String formatDate = df.format(new Date());
		return formatDate;
	}

	public String generateUsername() {
		StringBuilder str=new StringBuilder();
		Random random = new Random();
		for(int i=0;i<8;i++){
			str.append(random.nextInt(10));
		}
		String phoneNumber = "721"+str.toString();
		ShareDataManager.getInstance().addShareData("phoneNumber",phoneNumber);
		return phoneNumber;
	}

	public String getUsername() { return String.valueOf(ShareDataManager.getInstance().getShareData("phoneNumber")); }

	public String getSms() {
		return String.valueOf(ShareDataManager.getInstance().getShareData("smsCode"));
	}

	public String generatePhoneNumber() {
		String second=String.valueOf(getNum(1,888)+10000).substring(1);
		String thrid=String.valueOf(getNum(1,9100)+10000).substring(1);
		return "721"+second+thrid;
	}

	public String generateLoginName() {
		SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
		String formatDate = df.format(new Date());
		return "test"+formatDate;
	}

	public String generatePassword() {
		String password = "123456";
		return password;
	}

	private int getNum(int start,int end) {
		return (int)(Math.random()*(end-start+1)+start);
	}

	public static void main(String[] args) {
		NumberUtil numberUtil = new NumberUtil();
		String a = numberUtil.generateUsername();
		System.out.println(a);
	}
}
