package com.liuyk.service;

import org.springframework.stereotype.Service;

@Service
public class MyValidateHandler1 implements IValidateHandler {

	@Override
	public void handler() {
		System.out.println("MyValidateHandler1.handler");
	}

}
