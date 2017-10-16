package com.yy.ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yy.ssm.bean.User_Role;
import com.yy.ssm.dao.User_RoleMapper;

@Service
public class User_RoleService {
	@Autowired
	private User_RoleMapper user_RoleMapper;
	public boolean add_userRole(User_Role user_Role){
		
		Integer integer = user_RoleMapper.insert(user_Role);
		
		return integer!=-1;
		
	}
	
}
