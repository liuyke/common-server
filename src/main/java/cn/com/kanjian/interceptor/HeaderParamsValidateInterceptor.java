package cn.com.kanjian.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.com.kanjian.base.HttpHeaderContext;

/**
 * 头信息参数校验的拦截器，必须在HttpHeaderInterceptor执行之后执行
 * 用来检验是否出入了“平台”、“版本”、“渠道”和“设备信息”等头信息
 * @author liuyk
 *
 */
public class HeaderParamsValidateInterceptor implements HandlerInterceptor {

	private static final Logger log = Logger.getLogger(HeaderParamsValidateInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		log.debug("-----preHandle------");
		if(HttpHeaderContext.getSystype() == null || HttpHeaderContext.getVersion() == null 
				|| HttpHeaderContext.getChannel() == null || HttpHeaderContext.getDevice() == null) {
			response.setContentType("application/json");
			response.getWriter().print("{\"code\":2, \"restr\":\"header params error\"}");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.debug("-----afterCompletion------");
	}

}
