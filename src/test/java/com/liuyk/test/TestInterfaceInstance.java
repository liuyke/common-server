package com.liuyk.test;

import javax.inject.Inject;

import org.junit.Test;

import cn.com.kanjian.dao.IProxyCall;
import cn.com.kanjian.test.BaseTester;

public class TestInterfaceInstance extends BaseTester {

	@Inject
	private IProxyCall proxyCall;
	
	@Test
	public void testProxyAdd() throws Exception {
		int call = proxyCall.call(15, 6);
		System.out.println("call-->" + call);
	}
	
}
