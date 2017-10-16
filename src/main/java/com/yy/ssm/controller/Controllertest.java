package com.yy.ssm.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yy.ssm.bean.Employy;
import com.yy.ssm.service.EmployyService;
/*@RequestMapping("/web")
@Controller*/
public class Controllertest{
	@Autowired(required=false)
	EmployyService employyService;
	@RequestMapping("/test")
    public String test1(Model moModel ){
		/*EmployyMapper em= getSqlSession().getMapper(EmployyMapper.class);
		Employy selectByPrimaryKey = em.selectByPrimaryKey(1);
		System.out.println(selectByPrimaryKey.toString());*/
		/*Employy employy = employyService.selectWithByIdDepartment(1001);
		System.out.println(employy.toString());
		System.out.println(employy.getDept().toString());*/
		PageHelper.startPage(1, 10);
		List<Employy> allEmployy = employyService.selectWithAllDepartment();
		PageInfo<Employy> pageInfo=new PageInfo<Employy>(allEmployy,10);
		moModel.addAttribute("pageInfo", pageInfo);
		
	   return "test";
   }
}
