package cn.com.kanjian.model;

import java.io.Serializable;
import java.util.Date;

public class Tags implements Serializable {

	private static final long serialVersionUID = -2986785830663903629L;
	private Long id;
	private String tagname;
	private Date intime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

}
