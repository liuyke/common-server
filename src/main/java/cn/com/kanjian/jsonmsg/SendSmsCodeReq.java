package cn.com.kanjian.jsonmsg;

/**
 * 发送短信验证码的请求体
 * 
 * @author liuyk
 * 
 */
public class SendSmsCodeReq extends BaseReq {

	private static final long serialVersionUID = 4449572838047950239L;

	private String msisdn;
	private Integer type;

	public SendSmsCodeReq() {
	}

	public SendSmsCodeReq(String msisdn, Integer type) {
		super();
		this.msisdn = msisdn;
		this.type = type;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
