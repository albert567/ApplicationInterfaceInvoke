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
 * 应用功能数据库对象关联数据模型
 * @author 范大德
 *
 */
@Table("T_AppFuncObj")
@QueryForm(title="应用功能与数据库对象")
public class AppFuncObjForm extends DefaultForm{
	@PK
	@NoInsert
	@NoUpdate
	private int id;//ID
	@Field("FuncID")
	@Left(table="T_ApplicationFunction",on="ID",fields={"NAME"})
	private int funcID;//功能ID
	@NoInsert
	@NoUpdate
	@Field("T_ApplicationFunction.Name as FuncName")
	@Title("功能名称")
	private String funcName;
	@Field("ObjID")
	@Left(table="T_DataBaseObj",on="ID",fields={"ObjName","ObjType","ObjDescription","DataBaseID"})
	private int objID;//数据库对象ID
	@Field("T_DataBaseObj.ObjName")
	@NoInsert
	@NoUpdate
	@Title("对象名称")
	private String objName;
	@Field("T_DataBaseObj.ObjType")
	@NoInsert
	@NoUpdate
	@Left(table="T_Dict",on="DKey",fields={"DValue"})
	private String objTypeID;//对象类型ID
	@Field("T_Dict.DValue")
	@NoInsert
	@NoUpdate
	@Title("对象类型")
	private String objTypeName;//对象类型名称
	@Field("T_DataBaseObj.ObjDescription")
	@NoInsert
	@NoUpdate
	@Title("对象描述")
	private String objDesc;//数据库对象描述
	@Field("T_DataBaseObj.DataBaseID")
	@NoInsert
	@NoUpdate
	@Left(table="T_DataBase",on="ID",fields={"Name"})
	private int dbID;//数据库ID
	@Field("T_DataBase.Name as DbName")
	@NoInsert
	@NoUpdate
	@Title("数据库")
	private String dbName;//数据库名称
	@NoInsert
	@NoUpdate
	@NoQuery
	private String description;//关键字查询用
	@NoInsert
	@NoUpdate
	@NoQuery
	private String appName;//应用名称（导入时用）
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFuncID() {
		return funcID;
	}
	public void setFuncID(int funcID) {
		this.funcID = funcID;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public int getObjID() {
		return objID;
	}
	public void setObjID(int objID) {
		this.objID = objID;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
	public String getObjTypeID() {
		return objTypeID;
	}
	public void setObjTypeID(String objTypeID) {
		this.objTypeID = objTypeID;
	}
	public String getObjTypeName() {
		return objTypeName;
	}
	public void setObjTypeName(String objTypeName) {
		this.objTypeName = objTypeName;
	}
	public String getObjDesc() {
		return objDesc;
	}
	public void setObjDesc(String objDesc) {
		this.objDesc = objDesc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDbID() {
		return dbID;
	}
	public void setDbID(int dbID) {
		this.dbID = dbID;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
}
