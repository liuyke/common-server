package cn.com.kanjian.model;

import java.io.Serializable;
import java.util.Date;

public class ResourceOperate extends HeadModel implements Serializable {

	private static final long serialVersionUID = -9072605386412352640L;

	private Long id;
	private Integer opetype;
	private Long resid;
	private Long userid;
	private Date intime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOpetype() {
		return opetype;
	}

	public void setOpetype(Integer opetype) {
		this.opetype = opetype;
	}

	public Long getResid() {
		return resid;
	}

	public void setResid(Long resid) {
		this.resid = resid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

}
