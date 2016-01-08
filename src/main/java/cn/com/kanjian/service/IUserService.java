package cn.com.kanjian.service;

import cn.com.kanjian.model.User;

public interface IUserService {
	
	/**
	 * 用户注册
	 * @param user
	 */
	public void registeUser(String username, String nickname, String msisdn, String checkcode, String password);
	
	/**
	 * APP用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	public User appUserLogin(String username, String password);
	
	/**
	 * 发送手机验证码
	 * @param msisdn
	 * 
	 * @param type
	 * 	see Dictionary.Checkcode class
	 */
	public void sendMsisdnCheckcode(String msisdn, int type);
	
	/**
	 * 根据密码和盐加密
	 * @param pwd
	 * @param salt
	 * @return
	 */
	public String encodePassword(String pwd, String salt);
	
	/**
	 * 找回密码
	 * @param msisdn
	 * @param password
	 * @param checkCode
	 */
	public void findPassword(String msisdn, String password, String checkCode);
}
