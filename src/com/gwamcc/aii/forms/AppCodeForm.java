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
 * 应用 系统代码结构数据模型
 * @author 张亚平
 *
 */
@Table("T_CodeStructure")
@QueryForm(title="应用系统代码结构")
public class AppCodeForm extends DefaultForm{
	@PK
	@NoInsert
	@NoUpdate
	private int id;//目录ID
	@Title("文件名称")
	@Field("T_CodeStructure.Name")
	private String name;//目录名称
	@Title("路径")
	private String path;//路径
	@Field("ApplicationID")
	@Left(table="T_Application",on="ID",fields={"NAME"})
	private int appID;//所属应用ID
	@Title("应用名称")
	@Field("T_Application.Name AS AppName")
	@NoInsert
	@NoUpdate
	private String appName;//所属应用名称
	@Field("T_CodeStructure.ParentID AS _parentId")
	@NoInsert
	@NoUpdate
	private int _parentId;//父级ID
	@Field("T_CodeStructure.ParentID")
	@Left(table="T_CodeStructure ParentStr",on="id",fields={"Name"})
	private int parentID;//父级ID
	@Field("ParentStr.Name as ParentName")
	@NoUpdate
	@NoInsert
	@Title("上级目录")
	private String parentName;
	@NoUpdate
	@NoInsert
	@NoQuery
	private String description;
	private String pname;
	@Field("(case when(select COUNT(*)from T_CodeStructure A where A.ParentID=T_CodeStructure.ID)=0 THEN 'open' ELSE 'closed' END) State")
	@NoUpdate
	@NoInsert
	private String state;
	
	public int getId() {
		return id;
	}
	public AppCodeForm setId(int id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public AppCodeForm setName(String name) {
		this.name = name;
		return this;
	}
	public String getPath() {
		return path;
	}
	public AppCodeForm setPath(String path) {
		this.path = path;
		return this;
	}
	public int getAppID() {
		return appID;
	}
	public AppCodeForm setAppID(int appID) {
		this.appID = appID;
		return this;
	}
	public String getAppName() {
		return appName;
	}
	public AppCodeForm setAppName(String appName) {
		this.appName = appName;
		return this;
	}
	public String getParentName() {
		return parentName;
	}
	public AppCodeForm setParentName(String parentName) {
		this.parentName = parentName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public AppCodeForm setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getPname() {
		return pname;
	}
	public AppCodeForm setPname(String pname) {
		this.pname = pname;
		return this;
	}
	public int get_parentId() {
		return _parentId;
	}
	public AppCodeForm set_parentId(int _parentId) {
		this._parentId = _parentId;
		return this;
	}
	public int getParentID() {
		return parentID;
	}
	public AppCodeForm setParentID(int parentID) {
		this.parentID = parentID;
		return this;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
