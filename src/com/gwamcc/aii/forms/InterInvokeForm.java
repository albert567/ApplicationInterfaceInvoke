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
 * 应用接口调用对象
 * @author 张亚平
 *
 */
@Table("T_ApplicationInterfaceInvoke")
@QueryForm(title="接口调用")
public class InterInvokeForm extends DefaultForm{
	@PK
	@NoInsert
	@NoUpdate
	@Field("ID")
	private int interInvokeID;//主键ID
	@Field("InterfaceMethodID")
	@Left(table="T_InterfaceMethod T_Method",on="id",fields={"Name"})
	private int methodID;//方法ID
	@NoInsert
	@NoUpdate
	@Field("T_Method.name methodName")
	@Title("方法名称")
	private String methodName;//方法名称
	@Field("T_ApplicationInterfaceInvoke.InterfaceID")
	@Left(table="T_Interface T_Inter",on="id",fields={"Name"})
	private int interID;//接口ID
	@NoInsert
	@NoUpdate
	@Field("T_Inter.name interName")
	@Title("接口名称")
	private String interName;//接口名称
	@Field("T_Inter.ApplicationID appid")
	@NoInsert
	@NoUpdate
	private int appID;//应用ID
	@Field("T_Inter.ApplicationID")
	@NoQuery
	@NoInsert
	@NoUpdate
	@Left(table="T_Application T_App",on="id",fields={"Name"})
	private int _appID;//应用ID
	@NoInsert
	@NoUpdate
	@Field("T_App.Name AppName")
	@Title("应用名称")
	private String appName;//应用名称
	@Field("InvokeMethodID")
	@Left(table="T_InterfaceMethod T_InvokeMe",on="id",fields={"Name","InterfaceID"})
	private int invokeMethodID;//调用方方法ID
	@NoInsert
	@NoUpdate
	@Field("T_InvokeMe.name InvokeMeName")
	@Title("调用方法名")
	private String invokeMeName;//调用方方法名称
	@Field("T_InvokeMe.InterfaceID invokeInterID")
	@NoInsert
	@NoUpdate
	private int invokeInterID;//调用方接口ID
	@Field("T_InvokeMe.InterfaceID")
	@NoQuery
	@NoInsert
	@NoUpdate
	@Left(table="T_Interface T_InvokeInter",on="id",fields={"Name","ApplicationID"})
	private int _invokeInterID;//调用方接口ID
	@NoInsert
	@NoUpdate
	@Field("T_InvokeInter.name InvokeInterName")
	@Title("调用接口")
	private String invokeInterName;//调用方接口名称
	@Field("T_InvokeInter.ApplicationID InvokeAppID")
	@NoInsert
	@NoUpdate
	private int invokeAppID;//调用方应用ID
	@Left(table="T_Application T_InvokeApp",on="id",fields={"Name"})
	@Field("T_InvokeInter.ApplicationID")
	@NoQuery
	@NoInsert
	@NoUpdate
	private int _invokeAppID;//调用方应用ID
	@NoInsert
	@NoUpdate
	@Field("T_InvokeApp.name InvokeAppName")
	@Title("调用接口所属应用")
	private String invokeAppName;//调用方应用名称

	private String isValid;//是否有效 1有效 0无效
	@Title("调用描述")
	private String description;//描述
	@NoUpdate
	@Title("创建日期")
	private String createDate;//创建日期
	@NoUpdate
	@Title("创建人")
	private int createEmpID;//创建人员
	public int getInterInvokeID() {
		return interInvokeID;
	}
	public InterInvokeForm setInterInvokeID(int interInvokeID) {
		this.interInvokeID = interInvokeID;
		return this;
	}
	public int getMethodID() {
		return methodID;
	}
	public InterInvokeForm setMethodID(int methodID) {
		this.methodID = methodID;
		return this;
	}
	public String getMethodName() {
		return methodName;
	}
	public InterInvokeForm setMethodName(String methodName) {
		this.methodName = methodName;
		return this;
	}
	public int getInterID() {
		return interID;
	}
	public InterInvokeForm setInterID(int interID) {
		this.interID = interID;
		return this;
	}
	public String getInterName() {
		return interName;
	}
	public InterInvokeForm setInterName(String interName) {
		this.interName = interName;
		return this;
	}
	public int getAppID() {
		return appID;
	}
	public InterInvokeForm setAppID(int appID) {
		this.appID = appID;
		return this;
	}
	public String getAppName() {
		return appName;
	}
	public InterInvokeForm setAppName(String appName) {
		this.appName = appName;
		return this;
	}
	public int getInvokeMethodID() {
		return invokeMethodID;
	}
	public InterInvokeForm setInvokeMethodID(int invokeMethodID) {
		this.invokeMethodID = invokeMethodID;
		return this;
	}
	public String getInvokeMeName() {
		return invokeMeName;
	}
	public InterInvokeForm setInvokeMeName(String invokeMeName) {
		this.invokeMeName = invokeMeName;
		return this;
	}
	public int getInvokeInterID() {
		return invokeInterID;
	}
	public InterInvokeForm setInvokeInterID(int invokeInterID) {
		this.invokeInterID = invokeInterID;
		return this;
	}
	public String getInvokeInterName() {
		return invokeInterName;
	}
	public InterInvokeForm setInvokeInterName(String invokeInterName) {
		this.invokeInterName = invokeInterName;
		return this;
	}
	public int getInvokeAppID() {
		return invokeAppID;
	}
	public InterInvokeForm setInvokeAppID(int invokeAppID) {
		this.invokeAppID = invokeAppID;
		return this;
	}
	public String getInvokeAppName() {
		return invokeAppName;
	}
	public InterInvokeForm setInvokeAppName(String invokeAppName) {
		this.invokeAppName = invokeAppName;
		return this;
	}
	public String getIsValid() {
		return isValid;
	}
	public InterInvokeForm setIsValid(String isValid) {
		this.isValid = isValid;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public InterInvokeForm setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getCreateDate() {
		return createDate;
	}
	public InterInvokeForm setCreateDate(String createDate) {
		this.createDate = createDate;
		return this;
	}
	public int getCreateEmpID() {
		return createEmpID;
	}
	public InterInvokeForm setCreateEmpID(int createEmpID) {
		this.createEmpID = createEmpID;
		return this;
	}
	public int get_invokeAppID() {
		return _invokeAppID;
	}
	public InterInvokeForm set_invokeAppID(int _invokeAppID) {
		this._invokeAppID = _invokeAppID;
		return this;
	}
	public int get_appID() {
		return _appID;
	}
	public InterInvokeForm set_appID(int _appID) {
		this._appID = _appID;
		return this;
	}
	public int get_invokeInterID() {
		return _invokeInterID;
	}
	public InterInvokeForm set_invokeInterID(int _invokeInterID) {
		this._invokeInterID = _invokeInterID;
		return this;
	}


}
