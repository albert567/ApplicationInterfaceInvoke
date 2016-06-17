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
 * 接口方法对象（列表）
 * @author 张亚平
 *
 */
@Table("T_InterfaceMethodParameter")
@QueryForm(title="方法参数")
public class MethodParamForm extends DefaultForm{
	@PK
	@Field("ID")
	@NoInsert
	@NoUpdate
	private int paramID;//参数ID
	@Field("FieldName")
	@Title("参数名称")
	private String paramName;//参数名称
	@Field("FieldTypeID")
	@Left(table="T_Dict ParamType",on="DKey",fields={"DValue"})
	private String paramTypeID;//参数类型id
	@Field("ParamType.DValue as ParamTypeName")
	@NoInsert
	@NoUpdate
	@Title("参数类型")
	private String paramTypeName;//参数类型名称
	@Field("Description")
	@Title("参数描述")
	private String description;//参数描述
	@Field("IsValid")
	private String isValid;//是否有效 1有效 0无效
	@Field("FieldLength")
	@Title("参数长度")
	private int length;//参数长度
	@Field("InOrOut")
	@Title(value="出参入参",formatter="function(val){return val==0?'入参':(val==1?'出参':val)}")
	private String inOrOut;//0入参，1出参
	@Field("Sn")
	private int sn;//排序ID
	@Field("InterfaceMethodID")
	@Left(table="T_InterfaceMethod",on="ID",fields={"Name","MethodTypeID","InterfaceID"})
	private int methodID;//方法ID
	@Field("T_InterfaceMethod.Name as MethodName")
	@NoInsert
	@NoUpdate
	@Title("方法名称")
	private String methodName;//方法名称
	@Field("T_InterfaceMethod.MethodTypeID")
	@NoInsert
	@NoUpdate
	@Left(table="T_Dict MethodType",on="DKey",fields={"DValue"})
	private String methodTypeID;//方法类型id
	@Field("MethodType.DValue as MethodTypeName")
	@NoInsert
	@NoUpdate
	@Title("方法类型")
	private String methodTypeName;//方法类型名称
	@Field("T_InterfaceMethod.InterfaceID")
	@NoInsert
	@NoUpdate
	@Left(table="T_Interface",on="ID",fields={"Name","ApplicationID"})
	private int interID;//接口ID
	@Field("T_Interface.Name as InterName")
	@NoInsert
	@NoUpdate
	@Title("接口名称")
	private String interName;//接口名称
	@Field("T_Interface.ApplicationID")
	@NoInsert
	@NoUpdate
	@Left(table="T_Application",on="ID",fields={"Name"})
	private int appID;//应用ID
	@Field("T_Application.Name as AppName")
	@NoInsert
	@NoUpdate
	@Title("应用名称")
	private String appName;//应用名称

	public int getAppID() {
		return appID;
	}
	public MethodParamForm setAppID(int appID) {
		this.appID = appID;
		return this;
	}
	public String getAppName() {
		return appName;
	}
	public MethodParamForm setAppName(String appName) {
		this.appName = appName;
		return this;
	}
	public int getInterID() {
		return interID;
	}
	public MethodParamForm setInterID(int interID) {
		this.interID = interID;
		return this;
	}
	public String getInterName() {
		return interName;
	}
	public MethodParamForm setInterName(String interName) {
		this.interName = interName;
		return this;
	}
	public int getMethodID() {
		return methodID;
	}
	public MethodParamForm setMethodID(int methodID) {
		this.methodID = methodID;
		return this;
	}
	public String getMethodName() {
		return methodName;
	}
	public MethodParamForm setMethodName(String methodName) {
		this.methodName = methodName;
		return this;
	}
	public String getMethodTypeID() {
		return methodTypeID;
	}
	public MethodParamForm setMethodTypeID(String methodTypeID) {
		this.methodTypeID = methodTypeID;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public MethodParamForm setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getIsValid() {
		return isValid;
	}
	public MethodParamForm setIsValid(String isValid) {
		this.isValid = isValid;
		return this;
	}
	public String getMethodTypeName() {
		return methodTypeName;
	}
	public MethodParamForm setMethodTypeName(String methodTypeName) {
		this.methodTypeName = methodTypeName;
		return this;
	}
	public int getParamID() {
		return paramID;
	}
	public MethodParamForm setParamID(int paramID) {
		this.paramID = paramID;
		return this;
	}
	public String getParamName() {
		return paramName;
	}
	public MethodParamForm setParamName(String paramName) {
		this.paramName = paramName;
		return this;
	}
	public int getLength() {
		return length;
	}
	public MethodParamForm setLength(int length) {
		this.length = length;
		return this;
	}
	public String getInOrOut() {
		return inOrOut;
	}
	public MethodParamForm setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
		return this;
	}
	public int getSn() {
		return sn;
	}
	public MethodParamForm setSn(int sn) {
		this.sn = sn;
		return this;
	}
	public String getParamTypeID() {
		return paramTypeID;
	}
	public MethodParamForm setParamTypeID(String paramTypeID) {
		this.paramTypeID = paramTypeID;
		return this;
	}
	public String getParamTypeName() {
		return paramTypeName;
	}
	public MethodParamForm setParamTypeName(String paramTypeName) {
		this.paramTypeName = paramTypeName;
		return this;
	}

}
