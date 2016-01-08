package cn.com.kanjian.jsonmsg;

public class FindPasswordReq extends BaseReq {

	private static final long serialVersionUID = -4072284970216125507L;

	private String msisdn;
	private String checkcode;
	private String password;

	public FindPasswordReq() {
	}

	public FindPasswordReq(String msisdn, String checkcode, String password) {
		super();
		this.msisdn = msisdn;
		this.checkcode = checkcode;
		this.password = password;
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

}
