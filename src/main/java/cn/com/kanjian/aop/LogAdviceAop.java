package cn.com.kanjian.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import cn.com.kanjian.annotation.LogInfo;

@Aspect
//@Component //配置后生效 
public class LogAdviceAop {
	
	private final static Logger logger = Logger.getLogger(LogAdviceAop.class);
	
	@Pointcut("execution(* cn.com.kanjian.service.*.*(..))")
	public void logInfo() {
		
	}
	
	// 匹配 com.lzt.tserver.service 包下所有类的所有方法作为切入点
	@Around(value="logInfo() && @annotation(annotation) &&args(obj,..)",argNames="annotation,obj")
	public Object processTx(ProceedingJoinPoint jp, LogInfo annotation, Object obj) throws java.lang.Throwable {
		// 执行目标方法，并保存目标方法执行后的返回值
		logger.info("logValue:" + annotation.value() + ", obj:" + obj);
		jp.proceed();
		return obj;
	}

	
}