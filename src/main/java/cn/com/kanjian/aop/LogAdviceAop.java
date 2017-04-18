package cn.com.kanjian.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import cn.com.kanjian.annotation.LogInfo;
import cn.com.kanjian.service.ClassMapService;

@Aspect
@Component // 配置后生效
public class LogAdviceAop {

	private final static Logger logger = Logger.getLogger(LogAdviceAop.class);
	
	@Inject
	private ClassMapService classMapService;

	@Pointcut("execution(* cn.com.kanjian.service.*.*(..))")
	public void logInfo() {

	}

	// 匹配 com.lzt.tserver.service 包下所有类的所有方法作为切入点
	@Around(value = "logInfo() && @annotation(annotation) &&args(..)", argNames = "annotation")
	public Object processTx(ProceedingJoinPoint jp, LogInfo annotation) throws java.lang.Throwable {
		// 执行目标方法，并保存目标方法执行后的返回值
		Object[] args = jp.getArgs();
		Class<?>[] argsCls = new Class[args.length];
		for (int i=0;i< args.length; i++) {
			argsCls[i] = args[i].getClass();
		}
		Object resul = jp.proceed();
		logger.info("args=" + Arrays.toString(args));
//		Object result = jp.proceed();
//		logger.info("result=" + result);
		Object target = jp.getTarget();
		Signature signature = jp.getSignature();
		String name = signature.getName();
		logger.info("signature=" + name + ",declaringTypeName=" + signature.getDeclaringTypeName() + ",class=" + signature.getDeclaringType());
//		Object targetBean = classMapService.getBean(signature.getDeclaringTypeName());
//		Method method = targetBean.getClass().getMethod(name, argsCls);
//		Object invoke = method.invoke(targetBean, args);
//		System.out.println("invoke---" + invoke);
		return resul;
	}
	
	@AfterThrowing(pointcut = "logInfo()",throwing="e")  
    public void doException(JoinPoint jp,Exception e){  
		jp.getSignature().getName();
		jp.getArgs();
		System.out.println("doException---" + e.getMessage());
    }

}