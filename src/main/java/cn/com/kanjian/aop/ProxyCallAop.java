package cn.com.kanjian.aop;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


import cn.com.kanjian.dao.ProxyCall;

@Component
public class ProxyCallAop implements BeanFactoryAware, ApplicationContextAware {

	private DefaultListableBeanFactory beanFactory;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		 Map<String, Object> serviceBeanMap = context.getBeansWithAnnotation(ProxyCall.class);
		System.out.println("proxyCallBeans--->" + serviceBeanMap.size());
		if (serviceBeanMap != null && !serviceBeanMap.isEmpty()) {
			for (Entry<String, Object> e : serviceBeanMap.entrySet()) {
			}
		}
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (DefaultListableBeanFactory) beanFactory;
	}

}
