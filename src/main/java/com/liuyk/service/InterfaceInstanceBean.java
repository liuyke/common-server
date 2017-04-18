package com.liuyk.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Profile;

public class InterfaceInstanceBean<T> implements FactoryBean<T> {

	private Class<T> mapperInterface;
	private DefaultListableBeanFactory beanFactory;
	
	public void setMapperInterface(Class<T> mapperInterface) {
		this.mapperInterface = mapperInterface;
	}

	public void setBeanFactory(DefaultListableBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getObject() throws Exception {
		T proxy = (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class<?>[]{mapperInterface}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Profile annotation = method.getAnnotation(Profile.class);
				System.out.println("annotation-->" + Arrays.toString(annotation.value()));
				return Math.addExact((Integer)args[0], (Integer)args[1]);
			}
		});
		return proxy;
	}

	@Override
	public Class<?> getObjectType() {
		return this.mapperInterface;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
