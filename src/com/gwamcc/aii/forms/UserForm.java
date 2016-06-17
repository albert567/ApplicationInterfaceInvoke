package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoQuery;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.Table;

@Table(value="[User].gwauth.dbo.tblUser",hasUuid=false)
public class UserForm extends DefaultForm{
	private String loginName;

	@Field("userName")
	private String name;
	@Field("userDescription")
	private String description;
	@Field("userId")
	@NoInsert
	@NoUpdate
	@Left(table="[User].gwauth.dbo.AssignedRole",on="userid",fields="roleid")
	private String userId;
	@NoInsert
	@NoUpdate
	@Field("AssignedRole.roleid")
	@Left(table="[User].gwauth.dbo.CanDo",on="roleid",fields="rightid")
	private String roleId;
	@NoInsert
	@NoUpdate
	@Field("[User].gwauth.dbo.CanDo.rightid")
	private String rightId;
	@Field("userId")
	@NoInsert
	@NoUpdate
	@NoQuery
	@Left(table="[User].gwauth.dbo.UserBelongsToSect",on="userid",fields="sectionid")
	private String _userId;
	@Field("UserBelongsToSect.sectionid")
	@NoInsert
	@NoUpdate
	@Left(table="[User].gwauth.dbo.tblSection",on="sectionid",fields="sectionname")
	private String sectionId;
	@Field("[User].gwauth.dbo.tblSection.sectionname")
	@NoInsert
	@NoUpdate
	private String setctionName;


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
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRightId() {
		return rightId;
	}
	public void setRightId(String rightId) {
		this.rightId = rightId;
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
	public String getSetctionName() {
		return setctionName;
	}
	public void setSetctionName(String setctionName) {
		this.setctionName = setctionName;
	}
	
	@Override
	public String toString() {
		return StrKit.toString(this);
	}

}
