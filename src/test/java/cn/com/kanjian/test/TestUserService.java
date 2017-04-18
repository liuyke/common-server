package cn.com.kanjian.test;

import javax.inject.Inject;

import org.junit.Test;

import cn.com.kanjian.service.ClassMapService;
import cn.com.kanjian.service.IUserService;

public class TestUserService  extends BaseTester {
	
	@Inject
	private IUserService userService;

//	@Inject
	private ClassMapService classMapService;
	
	@Test
	public void test() {
		userService.appUserLogin("1213", "12345");
	}
	
	@Test
	public void testInjecMap() {
		Object bean = classMapService.getBean("cn.com.kanjian.service.IUserService");
		System.out.println("bean=" + bean);
	}
	
}
