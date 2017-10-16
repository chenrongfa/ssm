package com.yy.ssm.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yy.ssm.bean.Msg;
import com.yy.ssm.bean.User;
import com.yy.ssm.bean.User_Role;
import com.yy.ssm.dao.UserMapper;
import com.yy.ssm.realm.PasswordHash;
import com.yy.ssm.service.RoleService;
import com.yy.ssm.service.UserService;
import com.yy.ssm.service.User_RoleService;
import com.yy.ssm.utils.ImagesRandomCode;

@Controller
public class LoginController {
	@Autowired
	private UserService userMapper;
	@Autowired
	private PasswordHash passwordHash;
	@Autowired
	private User_RoleService user_RoleService;
	@Autowired
	private RoleService roleService;
	@Resource
	private ImagesRandomCode imagesRandomCode;

	@RequestMapping("/login")
	public String testLogin(@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpServletResponse response) throws IOException {
		   System.out.println("进来了?");

		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);

		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);

		return "index";
	}

	@RequestMapping("/randomCode")
	@ResponseBody
	public String testRandomCode(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进来了呀??");
		ServletOutputStream outputStream = null;
		try {
			request.setCharacterEncoding("utf-8");
			// 禁用缓存
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-control", "no-cache");
			response.setDateHeader("Expires", 0);

			outputStream = response.getOutputStream();
			ImageIO.write(imagesRandomCode.getImage(request), "JPEG",
					outputStream);
			outputStream.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "index";
	}

	@RequestMapping("/add_user")
	public String testAdd_user(@Validated User user) {
		user.setCreateTime(new Date());
		user.setLoginName(user.getName());
		user.setSalt(user.getEmail());
		System.out.println(user.toString());
		SimpleHash setPasswordHash = passwordHash.setPasswordHash(user
				);
		user.setPassword(setPasswordHash.toHex());

		boolean addUser = userMapper.addUser(user);
		System.out.println(user.toString());
		if (addUser) {
			System.out.println("添加成功");
			/**
			 * 为用户添加默认角色
			 */
			int roleIdByName = roleService.getRoleIdByName(null);
			User_Role user_Role = new User_Role();
			user_Role.setUserId(user.getId());
			user_Role.setRoleId(roleIdByName);
			user_RoleService.add_userRole(user_Role);

		} else {
			System.out.println("添加失败");
		}
		return "redirect:/login.jsp";
	}
	
	@RequestMapping("/checkCode")
	@ResponseBody
	public Msg testCheckRandomCode(@RequestParam("imageCode") String randomCode) {
		Msg msg = new Msg();
		Subject subject = SecurityUtils.getSubject();
		String code = (String) subject.getSession(false).getAttribute(
				"chenrongfa");
		System.out.println("code" + code);
		System.out.println("randomCode" + randomCode);
		if (code.equalsIgnoreCase(randomCode)) {
				msg.addSuccessMessage();
				msg.getDataBean().put("codeMessage", "验证通过");
		}else{
			msg.addErrorMessage();
			msg.getDataBean().put("codeMessage", "验证码错误");
		}
		
		return msg;
	}
}
