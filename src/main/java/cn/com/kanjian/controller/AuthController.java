package cn.com.kanjian.controller;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.kanjian.jsonmsg.BaseResp;
import cn.com.kanjian.jsonmsg.RegisteReq;
import cn.com.kanjian.jsonmsg.SendSmsCodeReq;
import cn.com.kanjian.service.IUserService;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	private static final Logger log = Logger.getLogger(AuthController.class);
	
	@Inject
	private IUserService userService;
	
	@RequestMapping(value="/sendSmscode", method=RequestMethod.POST)
	@ResponseBody
	public BaseResp sendCheckcode(@RequestBody SendSmsCodeReq req) {
		try {
			userService.sendMsisdnCheckcode(req.getMsisdn(), req.getType());
		} catch (Exception e) {
			log.error("发送短信验证码异常", e);
			return BaseResp.createSysErrorResp(e);
		}
		return BaseResp.createSuccessResp();
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public BaseResp register(@RequestBody RegisteReq req) {
		try {
			userService.registeUser(req.getMsisdn(), req.getNickname(), req.getMsisdn(), req.getCheckcode(), req.getPassword());
		} catch (Exception e) {
			log.error("注册用户异常", e);
			return BaseResp.createSysErrorResp(e);
		}
		return BaseResp.createSuccessResp();
	}
	
}
