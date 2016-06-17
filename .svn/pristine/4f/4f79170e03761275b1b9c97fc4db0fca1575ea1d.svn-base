package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoQuery;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 添加人员时,部门下拉列表框用
 * @author ypzhang 张亚平
 */
@Table(value="[User].gwauth.dbo.tblSection",hasUuid=false)
public class DepartmentForm extends DefaultForm{
	@PK
	@Left(table="[User].gwauth.dbo.UserBelongsToSect",on="SectionId",fields={"UserId"})
	private String sectionId;//部门ID	
	private String sectionName;//部门名称
	@NoInsert
	@NoUpdate
	@NoQuery
	@Field("[User].gwauth.dbo.UserBelongsToSect.UserId")
	@Left(table="[User].gwauth.dbo.AssignedRole",on="UserId",fields={"RoleId"})
	private String userId; //userID
	@Field("AssignedRole.RoleId")
	@Left(table="[User].gwauth.dbo.CanDo",on="RoleId",fields="RightId")
	@NoInsert
	@NoUpdate
	@NoQuery
	private String roleId;//roleID
	@NoInsert
	@NoUpdate
	@NoQuery
	@Field("[User].gwauth.dbo.CanDo.RightId")
	private String rightId;

	public String getSectionId() {
		return sectionId;
	}

	public DepartmentForm setSectionId(String sectionId) {
		this.sectionId = sectionId;
		return this;
	}

	public String getSectionName() {
		return sectionName;
	}

	public DepartmentForm setSectionName(String sectionName) {
		this.sectionName = sectionName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public DepartmentForm setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getRoleId() {
		return roleId;
	}

	public DepartmentForm setRoleId(String roleId) {
		this.roleId = roleId;
		return this;
	}

	public String getRightId() {
		return rightId;
	}

	public DepartmentForm setRightId(String rightId) {
		this.rightId = rightId;
		return this;
	}
	
	@Override
	public String toString() {
		return StrKit.toString(this);
	}
}
