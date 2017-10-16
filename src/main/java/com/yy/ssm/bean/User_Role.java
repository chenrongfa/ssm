package com.yy.ssm.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 *  
 * @author crf
 *  user 表于role的外映射关联表
 */
@TableName(value="user_role")
public class User_Role {
	@TableId(type=IdType.AUTO)
	private int id;
	@TableField(value="user_id")
	private int userId;
	@TableField(value="role_id")
	private int roleId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	

}
