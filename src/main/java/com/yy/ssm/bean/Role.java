package com.yy.ssm.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("role")
public class Role {
	@TableId(type=IdType.AUTO)
	private int id;
	@TableField(value="role_name")
	private String roleName;
	private int status;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDecription(String decription) {
		this.description = decription;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", status="
				+ status + ", decription=" + description + "]";
	}
	public Role(int id, String roleName, int status, String decription) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.status = status;
		this.description = decription;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
