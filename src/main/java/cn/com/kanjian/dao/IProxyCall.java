package cn.com.kanjian.dao;

import org.springframework.context.annotation.Profile;

@ProxyCall
public interface IProxyCall {

	@Profile("call(a,b)")
	public int call(int a, int b);
	
}