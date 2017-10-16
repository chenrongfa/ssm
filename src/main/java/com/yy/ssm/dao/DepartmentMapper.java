package com.yy.ssm.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yy.ssm.bean.Department;

public interface DepartmentMapper extends BaseMapper<Department>{
    int deleteByPrimaryKey(Integer deptId);

    Integer insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer deptId);
    List<Department> selectAll();

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
} 