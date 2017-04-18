package cn.com.kanjian.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.kanjian.annotation.LogInfo;
import cn.com.kanjian.base.Dictionary;
import cn.com.kanjian.base.HttpHeaderContext;
import cn.com.kanjian.exception.ServiceException;
import cn.com.kanjian.mapper.CheckcodeMapper;
import cn.com.kanjian.mapper.DateMapper;
import cn.com.kanjian.mapper.UserLogMapper;
import cn.com.kanjian.mapper.UserMapper;
import cn.com.kanjian.model.Checkcode;
import cn.com.kanjian.model.User;
import cn.com.kanjian.model.UserLog;
import cn.com.kanjian.util.Constants;
import cn.com.kanjian.util.Md5Util;
import cn.com.kanjian.util.SaltUtil;
import cn.com.kanjian.util.SmsUtils;

@Service
@Transactional
public class UserService implements IUserService, Serializable {
	
	private static final Logger log = Logger.getLogger(UserService.class);
	
	@Inject
	private UserMapper userMapper;
	@Inject
	private UserLogMapper userLogMapper;
	@Inject
	private CheckcodeMapper checkcodeMapper;
	@Inject
	private DateMapper dateMapper;
	
	private void throwError(String msg) {
		throw new ServiceException(msg);
	}

	@LogInfo("用户登录")
	@Override
	public User appUserLogin(String username, String password) {
//		User user = userMapper.loadByUsername(username);
		User user = new User();
		System.out.println("user=" + user);
		if(user == null) {
			throwError("用户名不存在");
		}
//		if(!encodePassword(password, user.getSalt()).equals(user.getPassword())) {
//			throwError("密码不正确");
//		}
//		if(user.getState() == Dictionary.User.STATE_LOCK) {
//			throwError("用户已锁定");
//		}
		
		return user;
	}

	@Override
	public void sendMsisdnCheckcode(String msisdn, int type) {
		User user = userMapper.loadByUsername(msisdn);
		if(user != null && type == Dictionary.Checkcode.TYPE_REGISTE) {
			throwError("手机号码已经被注册");
		}
		
		if(user == null && type == Dictionary.Checkcode.TYPE_FIND_PWD) {
			throwError("您还未注册");
		}
		
		int countToday = checkcodeMapper.countToday(msisdn);
		if(countToday >= Constants.CHECKCODE_MAX_COUNT) {
			throwError("今日验证码发送次数已达上限");
		}
		
		String code = SaltUtil.randon6Num() + "";
		Checkcode checkcode = new Checkcode(msisdn, code, type, dateMapper.afterMinute(Constants.CHECKCODE_TIME));
		checkcodeMapper.add(checkcode);
		log.debug("checkcode:" + code);
		boolean success = false;
		if(Constants.DEVELOP_MODE) {//开发模式不发短信
			success = true;
		} else {
			success = SmsUtils.sendCheckCode(msisdn, code);
		}
		if(!success) {
			log.error("send sms checkcode error:" + code);
			throwError("发送验证码失败");
		}
	}

	@Override
	public void registeUser(String username, String nickname, String msisdn, String checkcode, String password) {
		checkSmsCode(msisdn, checkcode, Dictionary.Checkcode.TYPE_REGISTE);
		//验证用户名是否存在
		User loadUser = userMapper.loadByUsername(username);
		if(loadUser != null) {
			throwError("用户名已存在");
		}
		//插入用户表
		String salt = SaltUtil.randomSalt(4);
		String encodePwd = encodePassword(password, salt);
		User user = new User(username, nickname, encodePwd, salt, null, null, 1, 0);
		userMapper.add(user);
		//插入用户操作日志表
		UserLog userLog = new UserLog(user.getId(), Dictionary.UserLog.LOGTYPE_REGIST, HttpHeaderContext.getVersion(), HttpHeaderContext.getSystype(), HttpHeaderContext.getChannel(), HttpHeaderContext.getDevice());
		userLogMapper.add(userLog);
	}

	private void checkSmsCode(String msisdn, String checkcode, int type) {
		//验证验证码是否正确
		Checkcode lastCheckcode = checkcodeMapper.loadLast(loadLastParams(msisdn, type));
		if(lastCheckcode == null) {
			throwError("请先获取验证码");
		}
		if(!lastCheckcode.getCheckcode().equalsIgnoreCase(checkcode)) {
			throwError("验证码不正确");
		}
		if(dateMapper.getNow().after(lastCheckcode.getDateline())) {
			throwError("验证码已失效");
		}
	}

	private Map<String, Object> loadLastParams(String msisdn, int type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("msisdn", msisdn);
		params.put("type", type);
		return params;
	}

	@Override
	public String encodePassword(String pwd, String salt) {
		return Md5Util.md5((salt + pwd).getBytes());
	}

	@Override
	public void findPassword(String msisdn, String password, String checkCode) {
		//验证用户名是否存在
		User loadUser = userMapper.loadByUsername(msisdn);
		if(loadUser == null) {
			throwError("用户不存在");
		}
		checkSmsCode(msisdn, checkCode, Dictionary.Checkcode.TYPE_FIND_PWD);
		String pwd = encodePassword(password, loadUser.getSalt());
		loadUser.setPassword(pwd);
		userMapper.update(loadUser);
	}
	
}
