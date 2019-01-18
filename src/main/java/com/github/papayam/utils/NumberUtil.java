package com.github.papayam.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NumberUtil {
	private Log logger = Log.getLogger(NumberUtil.class);

	public String generateTime() {
		SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
		String formatDate = df.format(new Date());
		return formatDate;
	}

	public String generateUsername() {
		SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
		String formatDate = df.format(new Date());
		return "721"+formatDate;
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
}
