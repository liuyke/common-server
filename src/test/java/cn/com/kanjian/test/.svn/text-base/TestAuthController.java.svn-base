package cn.com.kanjian.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.com.kanjian.base.Dictionary;
import cn.com.kanjian.jsonmsg.BaseResp;
import cn.com.kanjian.jsonmsg.FindPasswordReq;
import cn.com.kanjian.jsonmsg.RegisteReq;
import cn.com.kanjian.jsonmsg.SendSmsCodeReq;
import cn.com.kanjian.util.HttpClientUtils;
import cn.com.kanjian.util.JsonUtil;

public class TestAuthController {

	private static final String url = "http://192.168.100.23/common-server/auth/%s";
	
	@Test
	public void testSendSmscode() throws Exception {
		String json = JsonUtil.obj2json(new SendSmsCodeReq("1380000000", Dictionary.Checkcode.TYPE_FIND_PWD));
		String httpPost = HttpClientUtils.httpPostJson(String.format(url, "sendSmscode"), json, headers());
		System.out.println(httpPost);
		System.out.println(JsonUtil.json2obj(httpPost, BaseResp.class));
	}
	
	@Test
	public void testRegister() throws Exception {
		String json = JsonUtil.obj2json(new RegisteReq("我是junit程序注册的", "1380000000", "593383", "111111"));
		System.out.println(json);
		String httpPost = HttpClientUtils.httpPostJson(String.format(url, "register"), json, headers());
		System.out.println(httpPost);
		System.out.println(JsonUtil.json2obj(httpPost, BaseResp.class));
		
	}
	
	public static Map<String, String> headers() throws Exception {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("version", "1.0");
		headers.put("systype", Dictionary.SYSTYPE_ANDROID);
		headers.put("channel", "junit");
		headers.put("device", "device-id-xxxxxx");
		return headers;
	}
	
	@Test
	public void testFindPwd() throws Exception {
		String json = JsonUtil.obj2json(new FindPasswordReq("1380000000", "649563", "123456"));
		System.out.println(json);
		String httpPost = HttpClientUtils.httpPostJson(String.format(url, "findPassword"), json, headers());
		System.out.println(httpPost);
		System.out.println(JsonUtil.json2obj(httpPost, BaseResp.class));
	}
	
	
}
