package cn.com.kanjian.util;

import org.apache.log4j.Logger;


public class LogUtils {
	
	private static final int LEVEL = 0;

	private static final int DEBUG = 1;

	private static final int INFO = 2;

	private static final int WARN = 3;

	private static final int ERROR = 4;
	
	private static final boolean LOGJSON = true;
	
	public static void d(Logger log, String msg) {
		if(LEVEL < DEBUG) {
			log.debug(msg);
		}
	}
	
	public static void i(Logger log, String msg) {
		if(LEVEL < INFO) {
			log.info(msg);
		}
	}
	
	public static void w(Logger log, String msg) {
		if(LEVEL < WARN) {
			log.warn(msg);
		}
	}
	
	public static void e(Logger log, String msg, Throwable t) {
		if(LEVEL < ERROR) {
			log.error(msg, t);
		}
	}

	public static void e(Logger log, String msg) {
		e(log, msg, null);
	}
	
	public static void logJson(Logger log, Object  obj) {
		if(LOGJSON) {
			i(log, JsonUtil.obj2json(obj));
		}
	}
}
