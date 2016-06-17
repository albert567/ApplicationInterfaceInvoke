package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 接口方法与数据对象字段关联关系模型（详细信息中展示）
 * @author 范大德
 *
 */
@Table("T_IM_DBOC_Rel")
public class MethodDbForm extends DefaultForm{
	@PK
	private int id;//关联id
	@Left(table="T_InterfaceMethod",on="id",fields="name")
	@Field("InterfaceMethodID")
	private int methodID;//方法id
	@Field("T_InterfaceMethod.Name as methodName")
	private String methodName;//方法名称
	@Left(table="T_DataBase",on="id",fields="name")
	@Field("DataBaseID")
	private int dbID;//数据库id
	@Field("T_DataBase.Name as dbName")
	private String dbName;//数据库名称
	@Left(table="T_DataBaseObj",on="id",fields={"objName","objType"})
	@Field("DataBaseObjID")
	private int objID;//数据库对象id
	@Field("T_DataBaseObj.objName")
	private String objName;//数据库对象名称
	@Left(table="T_Dict",on="DKey",fields="DValue")
	@Field("T_DataBaseObj.objType")
	private String objTypeID;
	@Field("T_Dict.DValue")
	private String objTypeName;//数据库对象类型
	private int isValid=1;//是否有效
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
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

}
