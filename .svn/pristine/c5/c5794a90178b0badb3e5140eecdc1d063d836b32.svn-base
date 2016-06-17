package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 单独接口对象
 * @author 范大德
 *
 */
@Table("T_Interface")
public class InterForm extends DefaultForm {
	@PK
	@Field("ID")
	private int id;//接口id
	@Field("Name")
	private String name;//接口名称
	@Field("InterfaceTypeID")
	@Left(table="T_Dict",on="DKey",fields={"DValue"})
	private String interfaceTypeID;//接口类型ID
	@Field("T_Dict.DValue")
	@NoInsert
	@NoUpdate
	private String dValue;//接口类型
	@Field("T_Interface.Description")
	private String description;//接口描述
	public InterForm() {}
	public int getId() {
		return id;
	}
	public InterForm setId(int id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public InterForm setName(String name) {
		this.name = name;
		return this;
	}
	public String getDValue() {
		return dValue;
	}
	public InterForm setDValue(String dValue) {
		this.dValue = dValue;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public InterForm setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getInterfaceTypeID() {
		return interfaceTypeID;
	}
	public void setInterfaceTypeID(String interfaceTypeID) {
		this.interfaceTypeID = interfaceTypeID;
	}


}
