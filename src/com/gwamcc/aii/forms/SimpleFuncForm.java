package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 单独接口对象
 * @author 范大德
 *
 */
@Table("T_ApplicationFunction")
public class SimpleFuncForm extends DefaultForm {
	@PK
	private int id;//接口id
	private String name;//接口名称
	public SimpleFuncForm() {}
	public int getId() {
		return id;
	}
	public SimpleFuncForm setId(int id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public SimpleFuncForm setName(String name) {
		this.name = name;
		return this;
	}

}
