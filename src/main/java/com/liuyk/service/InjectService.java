package com.liuyk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InjectService {

	@Autowired
	private List<IValidateHandler> handlers;
	
	public void testHandler() {
		for (IValidateHandler handler : handlers) {
			handler.handler();
		}
	}
	
}
