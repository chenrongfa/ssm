package com.yy.ssm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yy.ssm.bean.Department;
import com.yy.ssm.bean.Msg;
import com.yy.ssm.service.DepartmentService;

@Controller
public class DepartmentController {
	@Autowired(required=false)
	private DepartmentService service;
	@RequestMapping("dept-all")
	@ResponseBody
	public Msg getDepartmentAll(){
		Msg msg=new Msg();
		 List<Department> departmentAll = service.getDepartmentAll();
		 
		 if(departmentAll.size()>0){
			 msg.addSuccessMessage();
			 msg.addDataBean("dept", departmentAll);
		 }else{
			 msg.addErrorMessage();
		 }
		return msg;
	}
}
