package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.form.anno.QueryForm;
import com.gwamcc.aii.util.form.anno.Title;
import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoQuery;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 数据库接口对象
 * @author 张亚平
 *
 */
@Table("T_InterProcedure")
@QueryForm(title="数据库接口对象")
public class InterProcedureForm extends DefaultForm {
	@PK
	@Field("ID")
	@NoInsert
	@NoUpdate
	private int id;//主键ID
	@Field("InterfaceID")
	private int interID;//接口ID
	@Field("ObjID")
	@Left(table="T_DataBaseObj",on="id",fields={"ObjName","ObjType","ObjDescription","DataBaseID"})
	private int objID;//对象ID
	@Field("T_DataBaseObj.ObjName")
	@NoInsert
	@NoUpdate
	@Title("对象名称")
	private String objName;//对象名称
	@NoInsert
	@NoUpdate
	@Field("T_DataBaseObj.ObjType")
	@Left(table="T_Dict",on="DKey",fields={"DValue"})
	private String objTypeID;//对象类型ID
	@NoInsert
	@NoUpdate
	@Field("T_Dict.DValue")
	@Title("对象类型")
	private String objTypeName;//对象类型
	@NoInsert
	@NoUpdate
	@Field("T_DataBaseObj.ObjDescription")
	@Title("对象描述")
	private String objDesc;//对象描述
	@NoInsert
	@NoUpdate
	@Field("T_DataBaseObj.DataBaseID")
	@Left(table="T_DataBase",on="ID",fields={"Name"})
	private int dbID;
	@NoInsert
	@NoUpdate
	@Field("T_DataBase.Name as DbName")
	@Title("对象所属数据库")
	private String dbName;
	@Field("IsValid")
	private String isValid;

	@NoUpdate
	@NoInsert
	@NoQuery
	@Title("对象所属应用")
	private String appName;

	public int getId() {
		return id;
	}
	public InterProcedureForm setId(int id) {
		this.id = id;
		return this;
	}
	public int getInterID() {
		return interID;
	}
	public InterProcedureForm setInterID(int interID) {
		this.interID = interID;
		return this;
	}
	public int getObjID() {
		return objID;
	}
	public InterProcedureForm setObjID(int objID) {
		this.objID = objID;
		return this;
	}
	public String getObjName() {
		return objName;
	}
	public InterProcedureForm setObjName(String objName) {
		this.objName = objName;
		return this;
	}
	public String getObjTypeID() {
		return objTypeID;
	}
	public InterProcedureForm setObjTypeID(String objTypeID) {
		this.objTypeID = objTypeID;
		return this;
	}
	public String getObjTypeName() {
		return objTypeName;
	}
	public InterProcedureForm setObjTypeName(String objTypeName) {
		this.objTypeName = objTypeName;
		return this;
	}
	public String getObjDesc() {
		return objDesc;
	}
	public InterProcedureForm setObjDesc(String objDesc) {
		this.objDesc = objDesc;
		return this;
	}
	public int getDbID() {
		return dbID;
	}
	public InterProcedureForm setDbID(int dbID) {
		this.dbID = dbID;
		return this;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getIsValid() {
		return isValid;
	}
	public InterProcedureForm setIsValid(String isValid) {
		this.isValid = isValid;
		return this;
	}
	public String getAppName() {
		return appName;
	}
	public InterProcedureForm setAppName(String appName) {
		this.appName = appName;
		return this;
	}
}
