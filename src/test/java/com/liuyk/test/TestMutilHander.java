package com.liuyk.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liuyk.service.InjectService;

import cn.com.kanjian.test.BaseTester;

public class TestMutilHander extends BaseTester {

	@Autowired
	private InjectService injectService;
	
	@Test
	public void testValidateHandler() throws Exception {
		injectService.testHandler();
	}
	
}
