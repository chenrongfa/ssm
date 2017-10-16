package com.yy.ssm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yy.ssm.bean.Role;
import com.yy.ssm.dao.RoleMapper;

@Service
public class RoleService {
		
	@Autowired
	private RoleMapper roleMapper;
	/**
	 *  通过角色名称查看其id;
	 * @return
	 */
	public int getRoleIdByName(String defaultValue){
		if(defaultValue==null){
			defaultValue="user";
		}
		Map<String, Object> columnMap=new HashMap<String, Object>();
		columnMap.put("role_name", defaultValue);
		List<Role> selectByMap = roleMapper.selectByMap(columnMap);
		if(selectByMap.size()>0){
			return selectByMap.get(0).getId();
		}
		return 0;
	}
}
