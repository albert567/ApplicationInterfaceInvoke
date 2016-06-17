package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.form.anno.QueryForm;
import com.gwamcc.aii.util.form.anno.Title;
import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 接口方法与数据对象字段关联关系模型
 * @author 范大德
 *
 */
@Table("T_IM_DBOC_Rel")
@QueryForm(title="接口方法与数据对象字段关联")
public class IMDORelationForm extends DefaultForm{
	@PK
	@NoInsert
	@NoUpdate
	private int id;//关联id
	@NoInsert
	@NoUpdate
	@Left(table="T_Application",on="id",fields="name",seq=9)
	@Field("T_Interface.ApplicationID")
	private int appID;//应用id
	@Field("T_Application.Name AS appName")
	@NoInsert
	@NoUpdate
	@Title("应用名称")
	private String appName;//应用名称
	@Left(table="T_Interface",on="id",fields="name")
	@Field("InterfaceID")
	private int interID;//接口id
	@NoInsert
	@NoUpdate
	@Field("T_Interface.Name as InterName")
	@Title("接口名称")
	private String interName;//接口名称
	@Left(table="T_InterfaceMethod",on="id",fields="name")
	@Field("InterfaceMethodID")
	private int methodID;//方法id
	@NoInsert
	@NoUpdate
	@Field("T_InterfaceMethod.Name as methodName")
	@Title("方法名称")
	private String methodName;//方法名称
	@Left(table="T_DataBase",on="id",fields="name")
	@Field("DataBaseID")
	private int dbID;//数据库id
	@NoInsert
	@NoUpdate
	@Field("T_DataBase.Name as dbName")
	@Title("数据库名称")
	private String dbName;//数据库名称
	@Left(table="T_DataBaseObj",on="id",fields="objName")
	@Field("DataBaseObjID")
	private int objID;//数据库对象id
	@NoInsert
	@NoUpdate
	@Field("T_DataBaseObj.objName")
	@Title("数据库对象名称")
	private String objName;//数据库对象名称
	@Left(table="T_DataBaseObjCol",on="id",fields="columnName")
	@Field("DataBaseObjColID")
	private int columnID=-1;//数据库字段id
	@NoInsert
	@NoUpdate
	@Field("T_DataBaseObjCol.columnname")
	@Title("数据库字段名称")
	private String columnName;//数据库字段名称
	private int isValid=1;//是否有效
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public int getInterID() {
		return interID;
	}
	public void setInterID(int interID) {
		this.interID = interID;
	}
	public String getInterName() {
		return interName;
	}
	public void setInterName(String interName) {
		this.interName = interName;
	}
	public int getMethodID() {
		return methodID;
	}
	public void setMethodID(int methodID) {
		this.methodID = methodID;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
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

	public int getColumnID() {
		return columnID;
	}
	public void setColumnID(int columnID) {
		this.columnID = columnID;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

}
