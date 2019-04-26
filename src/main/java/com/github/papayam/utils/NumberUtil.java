package com.github.papayam.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NumberUtil {

	public String generateTime() {
		SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
		String formatDate = df.format(new Date());
		return formatDate;
	}
}
