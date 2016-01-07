package cn.com.kanjian.jsonmsg;

public class LoginReq extends BaseReq {

	private static final long serialVersionUID = -5978908691146824497L;

	private String username;
	private String password;
	public LoginReq() {
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
