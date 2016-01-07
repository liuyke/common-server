package cn.com.kanjian.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import cn.com.kanjian.util.LogUtils;

public class GlobalHandlerExceptionResolver extends SimpleMappingExceptionResolver {
	
	private static final Logger log = Logger.getLogger(GlobalHandlerExceptionResolver.class);
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		LogUtils.e(log, "全局异常", ex);
		return super.doResolveException(request, response, handler, ex);
	}
	
}
