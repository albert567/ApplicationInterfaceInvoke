package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 单独数据库对象
 * @author 张亚平
 *
 */
@Table("T_DataBaseObj")
public class DbObjForm extends DefaultForm {
	@PK
	@Field("ID")
	private int id;//数据库对象id
	@Field("ObjName")
	private String objName;//对象名称
	@Field("ObjType")
	@Left(table="T_Dict",on="DKey",fields={"DValue"})
	private String objType;//对象类型ID
	@Field("T_Dict.DValue")
	private String dValue;//对象类型
	@Field("ObjDescription")
	private String description;//对象描述
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
	public String getObjType() {
		return objType;
	}
	public void setObjType(String objType) {
		this.objType = objType;
	}
	public String getDValue() {
		return dValue;
	}
	public void setDValue(String dValue) {
		this.dValue = dValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
