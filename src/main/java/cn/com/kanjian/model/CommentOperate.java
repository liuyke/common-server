package cn.com.kanjian.model;

import java.io.Serializable;
import java.util.Date;

public class CommentOperate extends HeadModel implements Serializable {

	private static final long serialVersionUID = 5033626389873426812L;
	private Long id;
	private Long commentid;
	private Long usrid;
	private Integer opetype;
	private Date datetime;

	public CommentOperate() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCommentid() {
		return commentid;
	}

	public void setCommentid(Long commentid) {
		this.commentid = commentid;
	}

	public Long getUsrid() {
		return usrid;
	}

	public void setUsrid(Long usrid) {
		this.usrid = usrid;
	}

	public Integer getOpetype() {
		return opetype;
	}

	public void setOpetype(Integer opetype) {
		this.opetype = opetype;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

}
