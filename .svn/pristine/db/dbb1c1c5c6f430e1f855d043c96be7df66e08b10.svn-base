package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 数据对象与接口方法字段关联关系模型
 * @author 张亚平
 *
 */
@Table("T_IM_DBOC_Rel")
public class DbMethodForm extends DefaultForm{
	@PK
	private int id;//关联id
	@Left(table="T_Application",on="id",fields="name",seq=9)
	@Field("T_Interface.ApplicationID")
	private int appID;//应用id
	@Field("T_Application.Name AS appName")
	private String appName;//应用名称
	@Left(table="T_Interface",on="id",fields="name")
	@Field("InterfaceID")
	private int interID;//接口id
	@Field("T_Interface.Name as InterName")
	private String interName;//接口名称
	@Left(table="T_InterfaceMethod",on="id",fields={"name","MethodTypeID"})
	@Field("InterfaceMethodID")
	private int methodID;//方法id
	@Field("T_InterfaceMethod.Name as methodName")
	private String methodName;//方法名称
	@Left(table="T_Dict",on="DKey",fields="DValue")
	@Field("T_InterfaceMethod.MethodTypeID")
	private String methodTypeID;
	@Field("T_Dict.DValue")
	private String methodTypeName;//方法类型
	private int isValid=1;//是否有效
	public String getMethodTypeID() {
		return methodTypeID;
	}
	public void setMethodTypeID(String methodTypeID) {
		this.methodTypeID = methodTypeID;
	}
	public String getMethodTypeName() {
		return methodTypeName;
	}
	public void setMethodTypeName(String methodTypeName) {
		this.methodTypeName = methodTypeName;
	}
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
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

}
