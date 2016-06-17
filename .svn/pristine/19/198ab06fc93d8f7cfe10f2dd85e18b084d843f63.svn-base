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

@Table("T_RoleUser")
@QueryForm(title="用户角色")
public class RoleUserForm extends DefaultForm{
	@PK
	@NoInsert
	@NoUpdate
	private int id;
	private int roleId;

	@Field("T_RoleUser.userId")
	@Left(table="[User].gwauth.dbo.tblUser",on="userId",fields={"userName","LoginName","UserDescription"})
	private String userId;
	@Field("[User].gwauth.dbo.tblUser.userName")
	@NoInsert
	@NoUpdate
	@Title("用户名")
	private String name;
	@Field("[User].gwauth.dbo.tblUser.LoginName")
	@NoInsert
	@NoUpdate
	@Title("登陆名")
	private String loginName;
	@Field("[User].gwauth.dbo.tblUser.UserDescription")
	@NoInsert
	@NoUpdate
	@Title("用户描述")
	private String description;
	@Field("T_RoleUser.userId")
	@NoInsert
	@NoUpdate
	@NoQuery
	@Left(table="[User].gwauth.dbo.UserBelongsToSect",on="userId",fields={"SectionId"})
	private String _userId;
	@Field("UserBelongsToSect.SectionId")
	@NoInsert
	@NoUpdate
	@Left(table="[User].gwauth.dbo.tblSection",on="sectionId",fields={"SectionName"})
	private String sectionId;
	@Field("[User].gwauth.dbo.tblSection.SectionName")
	@NoInsert
	@NoUpdate
	@Title("部门名称")
	private String sectionName;

	public int getId() {
		return id;
	}
	public RoleUserForm setId(int id) {
		this.id = id;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String get_userId() {
		return _userId;
	}
	public void set_userId(String _userId) {
		this._userId = _userId;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

}
