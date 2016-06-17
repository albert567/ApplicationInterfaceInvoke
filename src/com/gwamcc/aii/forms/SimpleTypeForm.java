package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 通用字典类型对象
 * @author 范大德
 *
 */
@Table("T_Dict")
public class SimpleTypeForm extends DefaultForm {
	@Field("DKey")
	private String id;//类型ID
	@Field("DValue")
	private String name;//类型名称
	public SimpleTypeForm() {	}
	public String getId() {
		return id;
	}
	public SimpleTypeForm setId(String id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public SimpleTypeForm setName(String name) {
		this.name = name;
		return this;
	}
}
