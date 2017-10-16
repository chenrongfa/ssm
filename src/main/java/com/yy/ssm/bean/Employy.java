package com.yy.ssm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;



public class Employy implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   
	private Integer empId;
	@Pattern(regexp="^[a-z0-9_-]*[\\u2E80-\\u9FFF]{2,16}$",message="格式错误:必须以字母开头两个汉字")
    private String empName;
    private Department dept;

    public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}
	@Pattern(regexp="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$",message="xxx@xxx.com")
	private String empEmail;
	
	@Past(message="已经进入未来")
	@NotNull(message="不能为空")
    private Date empBirthday;

    private Integer deptId;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail == null ? null : empEmail.trim();
    }

    public Date getEmpBirthday() {
        return empBirthday;
    }

    public void setEmpBirthday(Date empBirthday) {
        this.empBirthday = empBirthday;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

	@Override
	public String toString() {
		return "Employy [empId=" + empId + ", empName=" + empName
				+ ", empEmail=" + empEmail + ", empBirthday=" + empBirthday
				+ ", deptId=" + deptId + "]";
	}

	public Employy(Integer empId, String empName, String empEmail,
			Date empBirthday) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empEmail = empEmail;
		this.empBirthday = empBirthday;
	}

	public Employy() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}