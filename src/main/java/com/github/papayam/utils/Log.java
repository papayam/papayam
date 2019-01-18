package com.github.papayam.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {

	private static Logger log;
	private static Thread thread;
	public static Log getLogger(Class<?> T) {
		thread=Thread.currentThread();
		log=Logger.getLogger(T);
		return new Log();
	}
	static{
		PropertyConfigurator.configure(Log.class.getClassLoader().getResourceAsStream("log4j.properties"));
	}

	public void debug(Object message) {
		log.debug(message);
	}

	public  void debug(Object message, Throwable t) {
		log.debug(message, t);
	}

	public  void info(Object message) {
		log.info(message);
	}

	public  void info(Object message, Throwable t) {
		log.info(message, t);
	}

	public  void warn(Object message) {
		log.warn(message);
	}

	public  void warn(Object message, Throwable t) {
		log.warn(message, t);
	}

	public  void error(Object message) {
		log.error(message);
	}

	public  void error(Object message, Throwable t) {
		log.error(message, t);
	}
}