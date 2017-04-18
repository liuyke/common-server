package com.liuyk.service;

import org.springframework.stereotype.Service;

@Service
public class MyValidateHandler2 implements IValidateHandler {

	@Override
	public void handler() {
		System.out.println("MyValidateHandler2.handler");
	}

}
