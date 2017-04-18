package cn.com.kanjian.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.kanjian.annotation.ExcelFiled;
import cn.com.kanjian.annotation.SqlValue;

@Table(name="kj_user")
public class User extends MybatisEntity implements Serializable {

	private static final long serialVersionUID = 5209582805132320832L;
	
	@ExcelFiled(title="用户ID", sequence=10)
	private Long id;
	@ExcelFiled(title="用户姓名", sequence=20)
	private String username;
	@ExcelFiled(title="用户昵称", sequence=30)
	private String nickname;
	private String password;
	private String salt;
	private String accesstoken;
	@ExcelFiled(title="用户头像", sequence=30)
	private String photo;
	private Integer role;
	private Integer state;
	@ExcelFiled(title="注册时间", sequence=30)
	private Date intime;
	
	public User() {
		
	}
	
	public User(String username,String nickname, String password, String salt,
			String accesstoken, String photo, Integer role, Integer state) {
		super();
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.salt = salt;
		this.accesstoken = accesstoken;
		this.photo = photo;
		this.role = role;
		this.state = state;
	}

	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@SqlValue("now()")
	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
