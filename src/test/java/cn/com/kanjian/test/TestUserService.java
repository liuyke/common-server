package cn.com.kanjian.test;

import javax.inject.Inject;

import org.junit.Test;

import cn.com.kanjian.service.IUserService;

public class TestUserService  extends BaseTester {
	
	@Inject
	private IUserService userService;

	@Test
	public void test() {
		userService.appUserLogin("1213", "12345");
	}
}
