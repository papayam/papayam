package com.palfish.framework.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class ProUtil {
	private InputStream inputStream;
	private Properties prop;
	private Log logger = Log.getLogger(ProUtil.class);

	/**
	 * 构造方法
	 * 
	 * @param propName
	 * @throws Exception 
	 */
	public ProUtil(String propName) {
		this.inputStream = ProUtil.class.getClassLoader().getResourceAsStream("devices/"+propName+".properties");
		this.prop = readProperties();
	}

	/**
	 * 读取资源文件,并处理中文乱码
	 * 
	 * @return
	 * @throws Exception 
	 */
	private Properties readProperties(){
		Properties properties = new Properties();
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			properties.load(bf);
			inputStream.close(); // 关闭流
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * 获取某项文本内容
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String getKey(String key){
		if (prop.containsKey(key)) {
			return prop.getProperty(key);
		} else {
			logger.error("get key: " + key + " is not exist");
			throw new RuntimeException("get key: " + key + " is not exist");
		}
	}

	public HashMap<String, String> getAllKeyValue() {
		HashMap<String, String> keyValus = new HashMap<String, String>();
		Iterator it = prop.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			keyValus.put(key.toString(), value.toString());
			logger.debug("get caps " + key.toString() + ":" + value.toString());
		}
		return keyValus;
	}

}
