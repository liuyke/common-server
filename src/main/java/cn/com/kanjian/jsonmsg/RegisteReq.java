package cn.com.kanjian.jsonmsg;

import java.io.Serializable;

public class RegisteReq implements Serializable {

	private static final long serialVersionUID = -3490012825067484387L;

	private String nickname;
	private String msisdn;
	private String checkcode;
	private String password;

	public RegisteReq() {

	}

	public RegisteReq(String nickname, String msisdn, String checkcode,
			String password) {
		super();
		this.nickname = nickname;
		this.msisdn = msisdn;
		this.checkcode = checkcode;
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
