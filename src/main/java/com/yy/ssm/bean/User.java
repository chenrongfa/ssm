package com.yy.ssm.bean;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
@TableName("user")
public class User implements Serializable{
	/**
	 * 
	 */
	@TableField(exist=false)
	private static final long serialVersionUID = 1L;
	@TableId(type=IdType.AUTO)
	private Integer id;
	/**
	 *  登录用户名
	 */
	private String loginName;
	private String name;
	/**
	 *  盐值
	 */
	private String salt;
	
	private String password;
	@TableField(value="create_time")
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private Date createTime;
	@Email
	private String email;
	/**
	 *  禁用状态(1)还是启用(0)
	 */
	private int status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User(Integer id, String loginName, String name, String salt,
			String password, Date createTime, String email) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.salt = salt;
		this.password = password;
		this.createTime = createTime;
		this.email = email;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", name=" + name
				+ ", salt=" + salt + ", password=" + password + ", createTime="
				+ createTime + ", email=" + email + "]";
	}
	

}
