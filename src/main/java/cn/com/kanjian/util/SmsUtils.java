package cn.com.kanjian.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SmsUtils {

	public static boolean sendCheckCode(String msisdn, String checkCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", Constants.SMS_USERNAME);// 此处填写用户账号
		map.put("scode", Constants.SMS_PWD);// 此处填写用户密码
		map.put("mobile", msisdn);// 此处填写发送号码
		map.put("tempid", "MB-2013102300");// 此处填写模板短信编号
		map.put("content", "@1@=" + checkCode);// 此处填写模板短信内容
		try {
			String temp = HttpClientUtils.httpPost(Constants.SMS_URL, map);
			return "0#1#1".equals(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
