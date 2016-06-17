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
 * 角色人员查询时,部门下拉列表框用
 * @author ypzhang 张亚平
 *
 */
@Table(value="[User].gwauth.dbo.tblSection",hasUuid=false)
public class DepartCurrForm extends DefaultForm{
	@PK
	@Left(table="[User].gwauth.dbo.UserBelongsToSect",on="SectionId",fields={"UserId"})
	private String sectionId;//部门ID	
	private String sectionName;//部门名称
	@NoInsert
	@NoUpdate
	@NoQuery
	@Field("UserBelongsToSect.UserId")
	@Left(table="T_RoleUser",on="UserId",fields={"RoleId"})
	private String userId; //userID
	@Field("T_RoleUser.RoleId")
	@NoInsert
	@NoUpdate
	@NoQuery
	private String roleId;//roleID

	public String getSectionId() {
		return sectionId;
	}

	public DepartCurrForm setSectionId(String sectionId) {
		this.sectionId = sectionId;
		return this;
	}

	public String getSectionName() {
		return sectionName;
	}

	public DepartCurrForm setSectionName(String sectionName) {
		this.sectionName = sectionName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public DepartCurrForm setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getRoleId() {
		return roleId;
	}

	public DepartCurrForm setRoleId(String roleId) {
		this.roleId = roleId;
		return this;
	}
	
	@Override
	public String toString() {
		return StrKit.toString(this);
	}
}
