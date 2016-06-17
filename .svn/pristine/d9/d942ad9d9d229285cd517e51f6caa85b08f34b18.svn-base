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
@Table("T_InterfaceMethod")
@QueryForm(title="接口方法")
public class InterMethodForm extends DefaultForm{
	@PK
	@NoInsert
	@NoUpdate
	@Field("ID")
	private int methodID;//主键ID
	@Field("Name")
	@Title("方法名称")
	private String methodName;//方法名称
	@Field("MethodTypeID")
	@Left(table="T_Dict MeDict",on="DKey",fields={"DValue"})
	private String methodTypeID;//方法类型id
	@NoInsert
	@NoUpdate
	@Field("MeDict.DValue as MethodTypeName")
	@Title("方法类型")
	private String methodTypeName;//关联字典表
	@Field("T_InterfaceMethod.Description")
	@Title("方法描述")
	private String description;//方法描述
	@Field("InterfaceID")
	@Left(table="T_Interface",on="ID",fields={"ApplicationID","Name","InterfaceTypeID"})
	private int interID;//接口ID
	@Field("T_Interface.Name as InterName")
	@NoInsert
	@NoUpdate
	@Title("接口名称")
	private String interName;//接口名称
	@Field("T_Interface.InterfaceTypeID")
	@NoInsert
	@NoUpdate
	@Left(table="T_Dict InterDict",on="DKey",fields={"DValue"})
	private String interfaceTypeID;//接口类型ID
	@Field("InterDict.DValue as InterfaceTypeName")
	@NoInsert
	@NoUpdate
	@Title("接口类型")
	private String 	interfaceTypeName;//接口类型
	@NoInsert
	@NoUpdate
	@Left(table="T_Application",on="ID",fields="name",seq=9)
	@Field("T_Interface.ApplicationID")
	private int appID;//应用ID
	@NoInsert
	@NoUpdate
	@Field("T_Application.Name as AppName")
	@Title("所属应用")
	private String appName;//应用名称
	private String isValid;//是否有效 1有效 0无效
	@Title("创建人")
	private int createEmpID;//创建人员id
	@Title("创建时间")
	private String createDate;//创建日期 yyyy-MM-dd
	@Title(value="读写类型",formatter="function(val){return val==1?'读':'写'}")
	private String rw;//读写 读1，写0
	public int getAppID() {
		return appID;
	}
	public InterMethodForm setAppID(int appID) {
		this.appID = appID;
		return this;
	}
	public String getAppName() {
		return appName;
	}
	public InterMethodForm setAppName(String appName) {
		this.appName = appName;
		return this;
	}
	public int getInterID() {
		return interID;
	}
	public InterMethodForm setInterID(int interID) {
		this.interID = interID;
		return this;
	}
	public String getInterName() {
		return interName;
	}
	public InterMethodForm setInterName(String interName) {
		this.interName = interName;
		return this;
	}
	public String getInterfaceTypeName() {
		return interfaceTypeName;
	}
	public InterMethodForm setInterfaceTypeName(String interfaceTypeName) {
		this.interfaceTypeName = interfaceTypeName;
		return this;
	}
	public int getMethodID() {
		return methodID;
	}
	public InterMethodForm setMethodID(int methodID) {
		this.methodID = methodID;
		return this;
	}
	public String getMethodName() {
		return methodName;
	}
	public InterMethodForm setMethodName(String methodName) {
		this.methodName = methodName;
		return this;
	}
	public String getMethodTypeID() {
		return methodTypeID;
	}
	public InterMethodForm setMethodTypeID(String methodTypeID) {
		this.methodTypeID = methodTypeID;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public InterMethodForm setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getIsValid() {
		return isValid;
	}
	public InterMethodForm setIsValid(String isValid) {
		this.isValid = isValid;
		return this;
	}
	public String getCreateDate() {
		return createDate;
	}
	public InterMethodForm setCreateDate(String createDate) {
		this.createDate = createDate;
		return this;
	}
	public int getCreateEmpID() {
		return createEmpID;
	}
	public InterMethodForm setCreateEmpID(int createEmpID) {
		this.createEmpID = createEmpID;
		return this;
	}
	public String getRw() {
		return rw;
	}
	public InterMethodForm setRw(String rw) {
		this.rw = rw;
		return this;
	}
	public String getMethodTypeName() {
		return methodTypeName;
	}
	public InterMethodForm setMethodTypeName(String methodTypeName) {
		this.methodTypeName = methodTypeName;
		return this;
	}
	public String getInterfaceTypeID() {
		return interfaceTypeID;
	}
	public InterMethodForm setInterfaceTypeID(String interfaceTypeID) {
		this.interfaceTypeID = interfaceTypeID;
		return this;
	}


}
