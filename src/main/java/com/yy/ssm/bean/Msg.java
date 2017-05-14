package com.yy.ssm.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回json的通用类
 * 
 * @author crf
 * 
 */
public class Msg {
	/**
	 * 响应吗
	 */
	private int code;
	/**
	 * 返回描述
	 */
	private String message;

	private Map<String, Object> dataBean = new HashMap<String, Object>();

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getDataBean() {
		return dataBean;
	}

	public void setDataBean(Map<String, Object> dataBean) {
		this.dataBean = dataBean;
	}

	public Msg addSuccessMessage(int code, String message) {
		this.code = code;
		this.message = message;
		return this;
	}

	/**
	 * 默认
	 * 
	 */
	public Msg addSuccessMessage() {
		this.code = 200;
		this.message = "ok";
		return this;
	}

	/**
	 * 默认
	 * 
	 */
	public Msg addErrorMessage() {
		this.code = 100;
		this.message = "error";
		return this;
	}

	/**
	 * 
	 * @param code
	 * @param meassage
	 * @return
	 */
	public Msg addErrorMessage(int code, String meassage) {
		this.code = code;
		this.message = meassage;
		return this;
	}

	/**
	 * 添加数据
	 * 
	 * @param key
	 * @param object
	 * @return
	 */
	public Msg addDataBean(String key, Object object) {
		dataBean.put(key, object);
		return this;
	}

	
}