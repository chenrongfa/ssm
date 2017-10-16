package com.yy.ssm.realm;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import com.yy.ssm.bean.User;
/**
 *  密码加密
 * @author crf
 *
 */
@Component
public class PasswordHash {
  
	public SimpleHash setPasswordHash(User user){
		SimpleHash password=null;
		
		password=new SimpleHash("MD5", user.getPassword(),ByteSource.Util.bytes(user.getSalt()),1024);
		
		 return password;
	}
}
