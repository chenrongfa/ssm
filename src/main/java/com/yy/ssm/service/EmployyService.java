package com.yy.ssm.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yy.ssm.bean.Employy;
import com.yy.ssm.dao.EmployyMapper;

@Service
public class EmployyService {
	@Autowired(required=false)
	EmployyMapper emMapper;
	@Autowired
	SqlSession sqlsession;
public	Employy selectWithByIdDepartment(int id){
		return	emMapper.selectWithByIdDepartment(id);
	}
	public List<Employy> selectWithAllDepartment(){
		
		return emMapper.selectWithAllDepartment();
		
	}
	public boolean deleteByEmpId(int id) {
		// TODO Auto-generated method stub
		int count = emMapper.deleteByPrimaryKey(id);
		return count!=0;
	}
	public boolean deleteBySelect(String[] emp_ids) {
		// TODO Auto-generated method stub
		int count=0;
		int length=emp_ids.length;
		if(emp_ids.length>0){
			EmployyMapper mapper = sqlsession.getMapper(EmployyMapper.class);
			for(String id:emp_ids){
			int c=mapper.deleteByPrimaryKey(Integer.parseInt(id));
			System.out.println("c"+c);
			if(c!=-1){
				count++;	
			}
			c=0;
			}
			
		}
		return count==length;
	}
	public boolean addEmployy(Employy emp) {
		// TODO Auto-generated method stub
		int selective = emMapper.insertSelective(emp);
		
			
		return selective!=-1;
	}
	/**
	 *  不带id
	 * @param empName
	 * @return
	 */
	public boolean selectWithByName(String empName) {
		// TODO Auto-generated method stub
		List<Employy> selectWithByName = emMapper.selectWithByName(empName);
		return selectWithByName.size()!=0;
	}
	/**
	 *  带id 查询出来 是否一样
	 * @param emp
	 * @return
	 */
	public boolean selectWithByEmp(Employy emp) {
		// TODO Auto-generated method stub
		List<Employy> selectWithByName = emMapper.selectWithByName(emp.getEmpName());
		int count=0;
		if(selectWithByName.size()>0){
			//不存在
			
			if(selectWithByName.size()==1){
				System.out.println(selectWithByName.get(0).getEmpId());
				System.out.println(emp.getEmpId());
				if((int)emp.getEmpId()==(int)selectWithByName.get(0).getEmpId()){
					return false;
				}else{
					return true;
				}
			}
		   if(selectWithByName.size()>1){
			   return true;
		   }
		}else{
			return false;
		}
		 
		
		return selectWithByName==null;
	}
	public boolean updateWithById(Employy emp) {
		// TODO Auto-generated method stub
		int keySelective = emMapper.updateByPrimaryKeySelective(emp);
		return keySelective!=-1;
	}
}
