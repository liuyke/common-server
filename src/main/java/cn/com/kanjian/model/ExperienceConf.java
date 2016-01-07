package cn.com.kanjian.model;

import java.io.Serializable;

public class ExperienceConf implements Serializable {

	private static final long serialVersionUID = 4821691467088451914L;

	private String opename;
	private String opedes;
	private Integer evalue;

	public ExperienceConf() {
	}
	
	public String getOpename() {
		return opename;
	}

	public void setOpename(String opename) {
		this.opename = opename;
	}

	public String getOpedes() {
		return opedes;
	}

	public void setOpedes(String opedes) {
		this.opedes = opedes;
	}

	public Integer getEvalue() {
		return evalue;
	}

	public void setEvalue(Integer evalue) {
		this.evalue = evalue;
	}

}
