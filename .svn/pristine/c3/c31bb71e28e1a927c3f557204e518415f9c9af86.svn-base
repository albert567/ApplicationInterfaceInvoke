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
 * 应用程序和接口关联对象
 * @author 范大德
 *
 */
@Table("T_Interface")
@QueryForm(title="应用接口")
public class AppInterForm extends DefaultForm {
	@PK
	@Field("ID")
	@NoInsert
	@NoUpdate
	private int interID;//接口ID
	@Field("applicationID")
	@Left(table="T_Application",on="id",fields={"Name"})
	private int appID;//应用ID
	@Field("IsValid")
	private int valid=-1;//是有有效
	@Field("Name")
	@Title("接口名称")
	private String interName;//接口名称
	@Field("interfaceTypeID")
	@Left(table="T_Dict",on="DKey",fields={"DValue"})
	private String interfaceTypeID;//接口类型ID
	@Field("T_Dict.DValue")
	@NoInsert
	@NoUpdate
	@Title("接口类型")
	private String interfaceType;//接口类型
	@Title("接口描述")
	private String description;//接口描述
	@Field("T_Application.Name AS AppName")
	@NoInsert
	@NoUpdate
	@Title("所属应用")
	private String appName;//应用程序名称
	public String getInterfaceTypeID() {
		return interfaceTypeID;
	}
	public void setInterfaceTypeID(String interfaceTypeID) {
		this.interfaceTypeID = interfaceTypeID;
	}
	public AppInterForm() {}
	public int getInterID() {
		return interID;
	}
	public AppInterForm setInterID(int interID) {
		this.interID = interID;
		return this;
	}
	public int getAppID() {
		return appID;
	}
	public AppInterForm setAppID(int appID) {
		this.appID = appID;
		return this;
	}
	public int getValid() {
		return valid;
	}
	public AppInterForm setValid(int valid) {
		this.valid = valid;
		return this;
	}
	public String getInterName() {
		return interName;
	}
	public AppInterForm setInterName(String interName) {
		this.interName = interName;
		return this;
	}
	public String getInterfaceType() {
		return interfaceType;
	}
	public AppInterForm setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public AppInterForm setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getAppName() {
		return appName;
	}
	public AppInterForm setAppName(String appName) {
		this.appName = appName;
		return this;
	}
}
