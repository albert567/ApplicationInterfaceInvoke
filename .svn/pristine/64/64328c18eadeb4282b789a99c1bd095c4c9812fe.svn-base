package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoQuery;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 接口方法参数（列表）
 * @author 张亚平
 *
 */
@Table("T_InterfaceMethodParameter")
public class ParamForm extends DefaultForm{
	@PK
	@NoInsert
	@NoUpdate	
	private int id;//参数ID
	@Field("FieldName")
	private String paramName;//参数名称
	@Field("InterfaceMethodID")
	@Left(table="T_InterfaceMethod",on="ID",fields={"Name"})
	private String methodID;//方法ID
	@NoInsert
	@NoUpdate	
	@Field("T_InterfaceMethod.Name")
	private String methodName;//方法名称
	@Field("FieldTypeID")
	@Left(table="T_Dict",on="DKey",fields={"DValue"})
	private String paramTypeID;//参数类型id
	@Field("T_Dict.DValue")
	@NoInsert
	@NoUpdate	
	private String paramTypeName;//参数类型名称
	@Field("T_InterfaceMethodParameter.Description")
	private String description;//参数描述
	@Field("T_InterfaceMethodParameter.IsValid")
	private String isValid;//是否有效 1有效 0无效
	@Field("FieldLength")
	private int length;//参数长度
	@Field("T_InterfaceMethodParameter.InOrOut")
	private String inOrOut;//0入参，1出参
	@Field("T_InterfaceMethodParameter.Sn")
	private int sn;//排序ID
	@NoQuery
	@NoUpdate
	@NoInsert
	private String appName;
	@NoQuery
	@NoUpdate
	@NoInsert
	private String interName;
	public int getId() {
		return id;
	}
	public ParamForm setId(int id) {
		this.id = id;
		return this;
	}
	public String getParamName() {
		return paramName;
	}
	public ParamForm setParamName(String paramName) {
		this.paramName = paramName;
		return this;
	}
	public String getMethodID() {
		return methodID;
	}
	public ParamForm setMethodID(String methodID) {
		this.methodID = methodID;
		return this;
	}
	public String getMethodName() {
		return methodName;
	}
	public ParamForm setMethodName(String methodName) {
		this.methodName = methodName;
		return this;
	}
	public String getParamTypeID() {
		return paramTypeID;
	}
	public ParamForm setParamTypeID(String paramTypeID) {
		this.paramTypeID = paramTypeID;
		return this;
	}
	public String getParamTypeName() {
		return paramTypeName;
	}
	public ParamForm setParamTypeName(String paramTypeName) {
		this.paramTypeName = paramTypeName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public ParamForm setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getIsValid() {
		return isValid;
	}
	public ParamForm setIsValid(String isValid) {
		this.isValid = isValid;
		return this;
	}
	public int getLength() {
		return length;
	}
	public ParamForm setLength(int length) {
		this.length = length;
		return this;
	}
	public String getInOrOut() {
		return inOrOut;
	}
	public ParamForm setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
		return this;
	}
	public int getSn() {
		return sn;
	}
	public ParamForm setSn(int sn) {
		this.sn = sn;
		return this;
	}
	public String getAppName() {
		return appName;
	}
	public ParamForm setAppName(String appName) {
		this.appName = appName;
		return this;
	}
	public String getInterName() {
		return interName;
	}
	public ParamForm setInterName(String interName) {
		this.interName = interName;
		return this;
	}
	
}
