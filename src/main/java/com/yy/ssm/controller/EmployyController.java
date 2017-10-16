package com.yy.ssm.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yy.ssm.bean.Employy;
import com.yy.ssm.bean.Msg;
import com.yy.ssm.service.EmployyService;

@Controller
public class EmployyController {
	@Autowired(required=false)
	private EmployyService employyService;

	/**
	 * 查询所有
	 * 
	 * @param page
	 *            当前页
	 * @param moModel
	 * @return
	 */
	/*
	 * @RequestMapping("/emp-list") public String
	 * listAllEmployy(@RequestParam(name="page"
	 * ,defaultValue="0",required=false) int page, Model moModel){
	 * PageHelper.startPage(page, 10); List<Employy> allEmployy =
	 * employyService.selectWithAllDepartment(); PageInfo<Employy> pageInfo=new
	 * PageInfo<Employy>(allEmployy,4); moModel.addAttribute("pageInfo",
	 * pageInfo); return "list"; }
	 */
	@RequestMapping("/emp-list")
	@ResponseBody
	public Msg listAllEmployy(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page) {
		PageHelper.startPage(page, 10);
		Msg msg = new Msg();
		List<Employy> allEmployy = employyService.selectWithAllDepartment();
		PageInfo<Employy> pageInfo = new PageInfo<Employy>(allEmployy, 4);
		System.out.println("come");

		msg.addDataBean("pageInfo", pageInfo).addSuccessMessage();
		return msg;
	}

	@RequestMapping("/emp-test")
	public String test(HttpServletRequest request) {
		System.out.println(request.getAttribute("sex"));
		System.out.println(request.getParameter("sex"));

		System.out
				.println(Arrays.toString(request.getParameterValues("grade")));
		System.out.println(request.getParameter("select"));
		return "";
	}

	/**
	 * 通过id删除员工
	 * 
	 * @param id
	 * @param error
	 * @return
	 */

	@RequestMapping(method = RequestMethod.DELETE, value = "/emp")
	@ResponseBody
	public Msg empDeleteById(Employy emp, BindingResult error) {
		boolean success = employyService.deleteByEmpId(emp.getEmpId());
		Msg msg = new Msg();
		Map<String, Object> map = new HashMap<String, Object>();
		/* boolean success=false; */
		if (success) {
			msg.addSuccessMessage();

		} else {
			if (error.hasGlobalErrors()) {
				ObjectError globalError = error.getGlobalError();
				map.put(globalError.getObjectName(),
						globalError.getDefaultMessage());
			}
			msg.addErrorMessage().addDataBean("error", map);
		}
		return msg;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/emp-select")
	@ResponseBody
	public Msg empDeleteBySelectId(@RequestBody String result,
			BindingResult error) {
		System.out.println(result);
		String[] emp_ids = result.split(",");
		boolean flag = employyService.deleteBySelect(emp_ids);
		Msg msg = new Msg();
		Map<String, Object> map = new HashMap<String, Object>();
		if (flag) {
			msg.addSuccessMessage();
		} else {
			if (error.hasGlobalErrors()) {
				ObjectError globalError = error.getGlobalError();
				map.put(globalError.getObjectName(),
						globalError.getDefaultMessage());
			}
			msg.addErrorMessage().addDataBean("error", map);
		}
		return msg;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/emp_add")
	@ResponseBody
	public Msg putEmployy(@Valid Employy emp, BindingResult error) {
		Msg msg = new Msg();
		/*
		 * System.out.println(error.getFieldErrorCount());
		 * System.out.println(error.getGlobalError().getDefaultMessage());
		 */
		System.out.println(emp);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (emp != null) {
			/**
			 *  是否存在用户
			 */
			boolean withByName = employyService.selectWithByName(emp.getEmpName());
			if (!error.hasFieldErrors()&&!withByName) {
				boolean flag = employyService.addEmployy(emp);
			
				if (flag) {
					msg.addSuccessMessage();
				}
			} else {
				
					 for(FieldError field:error.getFieldErrors()){
						 map.put(field.getField(),field.getDefaultMessage());
					 }
					 map.put("isExist",true);
					 msg.addDataBean("errors", map);
					 msg.addErrorMessage();
					
			}
		}
		return msg;
	}
	@RequestMapping(method = RequestMethod.POST, value = "/emp-update")
	@ResponseBody
	public Msg updateEmployy(@Valid Employy emp,  BindingResult error) {
		Msg msg = new Msg();
		
		  System.out.println(error.getFieldErrorCount());
		  System.out.println(error.getGlobalError());
		 
		System.out.println(emp);
		boolean isExist=false;
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> username = new HashMap<String, Object>();
		if (emp != null) {
			/**
			 *  是否存在用户
			 */
			
			isExist = employyService.selectWithByEmp(emp);
			if(error.getGlobalErrorCount()>0){
				for(ObjectError object:error.getGlobalErrors()){
					map.put(object.getObjectName(),object.getDefaultMessage());
				}
				msg.addDataBean("errors", map);
			}else if (!error.hasFieldErrors()&&!isExist) {
				boolean flag = employyService.updateWithById(emp);		
				/**
				 *  更新成功
				 */
				if (flag) {
					msg.addSuccessMessage();
				}
			} 
			
			else {
				
				for(FieldError field:error.getFieldErrors()){
					map.put(field.getField(),field.getDefaultMessage());
				}
				
				msg.addDataBean("errors", map);
				msg.addErrorMessage();
				
			}
		}
		
		 username.put("isExist", isExist);
			msg.addDataBean("username", username);
		
		return msg;
	}

}
