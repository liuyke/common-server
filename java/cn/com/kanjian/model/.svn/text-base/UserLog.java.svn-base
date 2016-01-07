package cn.com.kanjian.model;

import java.io.Serializable;
import java.util.Date;

public class UserLog extends HeadModel implements Serializable {

	private static final long serialVersionUID = 6518702680199311614L;

	private Long id;
	private Long userid;
	private Integer logtype;
	private Date intime;

	public UserLog() {
	}
	
	public UserLog(Long userid, Integer logtype, String version, String systype, String channel, String device) {
		super(version, systype, channel, device);
		this.userid = userid;
		this.logtype = logtype;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Integer getLogtype() {
		return logtype;
	}

	public void setLogtype(Integer logtype) {
		this.logtype = logtype;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

}
