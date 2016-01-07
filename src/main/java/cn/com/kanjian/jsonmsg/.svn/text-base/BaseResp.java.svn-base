package cn.com.kanjian.jsonmsg;

import java.io.Serializable;

import org.springframework.util.StringUtils;

public class BaseResp implements Serializable {

	private static final long serialVersionUID = 2270343463610825735L;

	public static final int SUCCESS_CODE = 0;
	public static final int SYS_ERROR_RESP_CODE = 1;
	
	private static BaseResp SUCCESS_RESP;
	
	private int code;
	private String restr;

	public BaseResp() {
	}

	public static BaseResp createSuccessResp() {
		if(SUCCESS_RESP != null) {
			return SUCCESS_RESP;
		}
		return SUCCESS_RESP = new BaseResp(SUCCESS_CODE, null);
	}
	
	public static BaseResp createSysErrorResp(Exception e) {
		String error = e.getMessage();
		if(StringUtils.isEmpty(error)) {
			error = "服务器开小差了~~~";
		}
		return new BaseResp(SYS_ERROR_RESP_CODE, error);
	}
	
	private BaseResp(int code, String restr) {
		super();
		this.code = code;
		this.restr = restr;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getRestr() {
		return restr;
	}

	public void setRestr(String restr) {
		this.restr = restr;
	}

	@Override
	public String toString() {
		return "BaseResp [code=" + code + ", restr=" + restr + "]";
	}

}
