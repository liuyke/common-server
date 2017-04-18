package cn.com.kanjian.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultipleDataSourceAspect {

	private static final Logger LOG = LoggerFactory.getLogger(MultipleDataSourceAspect.class);

	public Object doAround(ProceedingJoinPoint jp) throws Throwable {

		if (LOG.isDebugEnabled()) {
			LOG.debug("MultipleDataSourceAspectAdvice invoked!");
		}

		Signature signature = jp.getSignature();

		Object jpVal = jp.proceed();

		return jpVal;
	}

	public String getDataSourceKey(Signature signature) {
		if (signature == null)
			return null;

		if (signature instanceof MethodSignature) {

			// 检测方法级注解
			MethodSignature methodSignature = (MethodSignature) signature;
			Method method = methodSignature.getMethod();
			// 类级注解
			Class declaringClazz = method.getDeclaringClass();
			// 包级注解,为了配置方便，包注解和类以及方法注解方式不同
			Package pkg = declaringClazz.getPackage();
		}
		return null;
	}

}
