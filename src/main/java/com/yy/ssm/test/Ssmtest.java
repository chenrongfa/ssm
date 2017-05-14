package com.yy.ssm.test;

import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yy.ssm.bean.Employy;
import com.yy.ssm.dao.EmployyMapper;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationspring.xml"})
public class Ssmtest {
	@Autowired
	EmployyMapper employyMapper;
	@Autowired
	SqlSession sqlsession;
    @Test
	public void testSelect(){
		System.out.println(employyMapper.selectByPrimaryKey(1001).toString());
	/*EmployyMapper em=sqlsession.getMapper(EmployyMapper.class);
		for(int i=0;i<1000;i++){
		String name=	UUID.randomUUID().toString().substring(0, 5)+i;
    	Employy employy = new Employy(null, name, "chenrongfa@66.com", null);
    	employy.setDeptId((int)(1+Math.random()*3));
    	em.insertSelective(employy);
		}*/
		System.out.println("完毕");
	}
		
	

}
