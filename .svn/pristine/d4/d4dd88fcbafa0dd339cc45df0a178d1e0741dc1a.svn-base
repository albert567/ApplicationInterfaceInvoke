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
 * 应用功能数据模型
 * @author 范大德
 *
 */
@Table("T_ApplicationFunction")
@QueryForm(title="应用功能")
public class AppFuncForm extends DefaultForm{
	@PK
	@NoInsert
	@NoUpdate
	private int id;//应用功能ID
	@Title("功能名称")
	private String name;//应用功能名称
	@Title("功能描述")
	private String description;//应用功能描述
	@Field("ApplicationID")
	@Left(table="T_Application",on="ID",fields={"NAME"})
	private int appID;//所属应用ID
	@Field("T_Application.Name AS AppName")
	@NoInsert
	@NoUpdate
	@Title("应用名称")
	private String appName;//所属应用名称
	@Field("T_ApplicationFunction.ParentID")
	@Left(table="T_ApplicationFunction ParentFunc",on="id",fields={"Name"})
	private int parentID=-1;//父级ID
	@Field("ParentFunc.Name as ParentName")
	@NoUpdate
	@NoInsert
	@Title("所属功能")
	private String parentName;
	private int isValid=1;//是否有效
	@Field("T_ApplicationFunction.Sn")
	private String sn;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}
	public int getParentID() {
		return parentID;
	}
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}

}
