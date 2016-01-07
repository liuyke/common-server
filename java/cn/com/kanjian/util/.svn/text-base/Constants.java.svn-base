package cn.com.kanjian.util;

import java.io.IOException;
import java.util.Properties;

public final class Constants {

	/**
	 * 每种类型验证码，每天最多发送次数
	 */
	public static final int CHECKCODE_MAX_COUNT = 5;

	/**
	 * 验证码有效分钟数
	 */
	public static final int CHECKCODE_TIME = 30;
	
	/**
	 * 发送短信平台的密码
	 */
	public static String SMS_PWD = null;
	/**
	 * 发送短信平台的用户名
	 */
	public static String SMS_USERNAME = null;
	/**
	 * 发送短信平台的URL	 */
	public static String SMS_URL = null;
	
	/**
	 * 是否是开发模式
	 */
	public static boolean DEVELOP_MODE = false;
	
	static {
		Properties prop = new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
			SMS_URL = prop.getProperty("SMS_URL").trim();
			SMS_USERNAME = prop.getProperty("SMS_USERNAME").trim();
			SMS_PWD = prop.getProperty("SMS_PWD").trim();
			String develop_mode = prop.getProperty("develop_mode").trim();
			DEVELOP_MODE = Boolean.parseBoolean(develop_mode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
