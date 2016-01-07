package cn.com.kanjian.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.com.kanjian.base.HttpHeaderContext;
import cn.com.kanjian.util.LogUtils;

/**
 * 获取Http Header中的信息
 * @author liuyk
 *
 */
public class HttpHeaderInterceptor implements HandlerInterceptor {
	
	private static final Logger log = Logger.getLogger(HttpHeaderInterceptor.class); 
	
	@Override
	public void afterCompletion(HttpServletRequest request,	HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		HttpHeaderContext.removeVersion();
		HttpHeaderContext.removeSystype();
		HttpHeaderContext.removeChannel();
		HttpHeaderContext.removeDevice();
		HttpHeaderContext.removeUserid();
		HttpHeaderContext.removeToken();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object response, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String systype = request.getHeader("systype");//系统类型，0表示Android，1表示ios
		HttpHeaderContext.setSystype(systype);
		String version = request.getHeader("version");//版本号
		HttpHeaderContext.setVersion(version);
		String channel = request.getHeader("channel");
		HttpHeaderContext.setChannel(channel);
		String device = request.getHeader("device");
		HttpHeaderContext.setDevice(device);
		String token = request.getHeader("token");
		HttpHeaderContext.setToken(token);
		
		String userid = request.getHeader("userid");
		try {
			HttpHeaderContext.setUserid(Long.valueOf(userid));
		} catch (Exception e) {
			//do nothing
		}
		String logStr = String.format("systype:%s, userid:%d, version:%s, channel:%s, token:%s, device:%s ", systype, userid, version, channel, token, device);
		LogUtils.d(log, logStr);
		return true;
	}

}
