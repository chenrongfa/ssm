package com.yy.ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yy.ssm.bean.User;
import com.yy.ssm.dao.UserMapper;
@Service
public class UserService {
	@Autowired
  private UserMapper userMapper;
	public boolean addUser(User user){
		Integer insert = userMapper.insert(user);
		return insert!=-1;
	}
}
