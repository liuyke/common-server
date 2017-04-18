package cn.com.kanjian.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.kanjian.exception.ServiceException;
import cn.com.kanjian.jsonmsg.BaseResp;
import cn.com.kanjian.jsonmsg.FindPasswordReq;
import cn.com.kanjian.jsonmsg.RegisteReq;
import cn.com.kanjian.jsonmsg.SendSmsCodeReq;
import cn.com.kanjian.service.IUserService;
import cn.com.kanjian.util.JsonUtil;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@Inject
	private IUserService userService;
	
	@RequestMapping(value="/sendSmscode", method=RequestMethod.POST)
	@ResponseBody
	public BaseResp sendCheckcode(@RequestBody SendSmsCodeReq req) {
		System.out.println("sendSmscode--req->" + JsonUtil.obj2json(req));
//		try {
//			userService.sendMsisdnCheckcode(req.getMsisdn(), req.getType());
//		} catch (Exception e) {
//			return BaseResp.createSysErrorResp(e);
//		}
		return BaseResp.createSuccessResp();
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public BaseResp register(@RequestBody RegisteReq req) {
		try {
			userService.registeUser(req.getMsisdn(), req.getNickname(), req.getMsisdn(), req.getCheckcode(), req.getPassword());
		} catch (ServiceException e) {
			return BaseResp.createSysErrorResp(e);
		}
		return BaseResp.createSuccessResp();
	}
	
	@RequestMapping(value="/findPassword", method=RequestMethod.POST)
	@ResponseBody
	public BaseResp findPassword(@RequestBody FindPasswordReq req) {
		try {
			userService.findPassword(req.getMsisdn(), req.getPassword(), req.getCheckcode());
		} catch (ServiceException e) {
			return BaseResp.createSysErrorResp(e);
		}
		return BaseResp.createSuccessResp();
	}
	
}
