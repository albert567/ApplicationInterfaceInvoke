package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 方法参数数据模型
 * @author 张亚平
 *
 */
@Table("T_InterfaceMethodParameter")
public class ArgForm extends DefaultForm {
	@PK
	private int id;
	@Field("FieldName")
	private String name;
	@Field("InterfaceMethodID")
	private int interfaceMethodID;
	@Field("FieldTypeID")
	@Left(table="T_Dict",on="DKey",fields={"DValue"})
	private String paramTypeID;//参数类型id
	@Field("T_Dict.DValue")
	private String paramTypeName;
	private String inOrOut;
	public String getParamTypeID() {
		return paramTypeID;
	}
	public ArgForm setParamTypeID(String paramTypeID) {
		this.paramTypeID = paramTypeID;
		return this;
	}
	public String getParamTypeName() {
		return paramTypeName;
	}
	public ArgForm setParamTypeName(String paramTypeName) {
		this.paramTypeName = paramTypeName;
		return this;
	}
	public int getId() {
		return id;
	}
	public ArgForm setId(int id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public ArgForm setName(String name) {
		this.name = name;
		return this;
	}
	public String getInOrOut() {
		return inOrOut;
	}
	public ArgForm setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
		return this;
	}
	public int getInterfaceMethodID() {
		return interfaceMethodID;
	}
	public ArgForm setInterfaceMethodID(int interfaceMethodID) {
		this.interfaceMethodID = interfaceMethodID;
		return this;
	}
	
	
}
