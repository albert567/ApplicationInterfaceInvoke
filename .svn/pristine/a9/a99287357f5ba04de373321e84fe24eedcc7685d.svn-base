package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 数据库接口对象
 * @author 张亚平
 *
 */
@Table("T_InterProcedure")
public class InterProNameForm extends DefaultForm {
	@PK
	@Field("ID")
	@NoInsert
	@NoUpdate	
	private int id;//主键ID
	@Field("InterfaceID")
	private int interID;//接口ID
	@Field("ObjID")
	@Left(table="T_DataBaseObj",on="id",fields={"ObjName"})
	private int objID;//对象ID
	@Field("T_DataBaseObj.ObjName")
	@NoInsert
	@NoUpdate
	private String objName;//对象名称
	public int getId() {
		return id;
	}
	public InterProNameForm setId(int id) {
		this.id = id;
		return this;
	}
	public int getInterID() {
		return interID;
	}
	public InterProNameForm setInterID(int interID) {
		this.interID = interID;
		return this;
	}
	public int getObjID() {
		return objID;
	}
	public InterProNameForm setObjID(int objID) {
		this.objID = objID;
		return this;
	}
	public String getObjName() {
		return objName;
	}
	public InterProNameForm setObjName(String objName) {
		this.objName = objName;
		return this;
	}
}
