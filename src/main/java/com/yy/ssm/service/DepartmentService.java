package com.yy.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yy.ssm.bean.Department;
import com.yy.ssm.dao.DepartmentMapper;

@Service
public class DepartmentService  {
	@Autowired(required=false)
     private DepartmentMapper dept;
	/**
	 *  查询所有的部门
	 * @return
	 */
	public List<Department> getDepartmentAll(){
		
		return dept.selectAll();
	}
}
