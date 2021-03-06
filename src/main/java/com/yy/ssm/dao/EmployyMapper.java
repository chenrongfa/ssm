package com.yy.ssm.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yy.ssm.bean.Employy;

public interface EmployyMapper extends BaseMapper<Employy>{
	int deleteByPrimaryKey(Integer empId);

	Integer insert(Employy record);

	int insertSelective(Employy record);

	Employy selectByPrimaryKey(Integer empId);

	int updateByPrimaryKeySelective(Employy record);

	int updateByPrimaryKey(Employy record);

	/**
	 *  通过id查询
	 * @param id
	 * @return
	 */
	Employy selectWithByIdDepartment(int id);
	/**
	 * 查询所有的员工
	 * @return
	 */
	List<Employy> selectWithAllDepartment();
	List<Employy>  selectWithByName(String empName);
}