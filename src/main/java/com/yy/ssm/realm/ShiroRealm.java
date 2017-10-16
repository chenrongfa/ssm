package com.yy.ssm.realm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.yy.ssm.bean.Role;
import com.yy.ssm.bean.User;
import com.yy.ssm.bean.User_Role;
import com.yy.ssm.dao.RoleMapper;
import com.yy.ssm.dao.UserMapper;
import com.yy.ssm.dao.User_RoleMapper;

public class ShiroRealm extends AuthorizingRealm {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private User_RoleMapper user_RoleMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo(shiroUser.getRoles());
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("执行了realm");
		User user=null;
		ShiroUser shiroUser=new ShiroUser();
		//用户的角色存放
		Set<String> userRoles=(Set<String>) new HashSet<String>();
		UsernamePasswordToken usernametoken=(UsernamePasswordToken) token;
		String username= (String) token.getPrincipal();
		if(username!=null){
			Map<String, Object> select=new HashMap<String, Object>();
			select.put("name", username);
			List<User> selectByMap = userMapper.selectByMap(select);
			if(selectByMap.size()>0){
				user = selectByMap.get(0);
				System.out.println("user"+user.toString());
				Map<String, Object> columnMap=new HashMap<String, Object>();
				columnMap.put("user_id", user.getId());
				List<User_Role> user_roles = user_RoleMapper.selectByMap(columnMap);
				List<Integer> idList=new LinkedList<Integer>();
				for(User_Role user_role:user_roles){
					idList.add(user_role.getRoleId());	
				}
				List<Role> roles = roleMapper.selectBatchIds(idList);
				for(Role role:roles){
					/**
					 *  检查角色是否可用
					 */
					if(role.getStatus()==0){
						userRoles.add(role.getRoleName());
					}
				}
				shiroUser.setLoginName(username);
				shiroUser.setRoles(userRoles);
			}
			
		}
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(shiroUser
				,user.getPassword(),ByteSource.Util.bytes(user.getSalt()), getName());
		return info;
	}
/*	@Override
	public void onLogout(PrincipalCollection principals) {
//	 清除授权
		super.clearCachedAuthorizationInfo(principals);
		System.out.println("清除缓存");
//清除认证
		super.clearCachedAuthenticationInfo(principals);
	}*/

}
