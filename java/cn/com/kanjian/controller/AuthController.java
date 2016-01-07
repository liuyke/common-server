package cn.com.kanjian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@RequestMapping("/login")
	@ResponseBody
	public String login() {
		return "{login:ree}";
	}
	
}
