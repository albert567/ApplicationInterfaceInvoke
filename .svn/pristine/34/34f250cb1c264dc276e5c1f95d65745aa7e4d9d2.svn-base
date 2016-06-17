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

@Table("T_RoleRight")
@QueryForm(title="用户权限")
public class RoleRightForm extends DefaultForm{
	@PK
	@NoInsert
	@NoUpdate
	private int id;
	@NoUpdate
	private int roleId;
	private int dataId;
	@NoUpdate
	private int menuId;
	@Field("(case when menuid='#{T(config.Res.MENU).APPLICATION}' then 'app' when menuid='#{T(config.Res.MENU).DATABASE}' then 'db' else '' end) menuType")
	@NoInsert
	@NoUpdate
	@Title(value="菜单类型",formatter="function(val){return val=='app'?'应用程序':'数据库'}")
	private String menuType;
	@Field("(case when menuid='#{T(config.Res.MENU).APPLICATION}' then T_application.name when menuid='#{T(config.Res.MENU).DATABASE}' then T_database.name else '' end) name")
	@NoInsert
	@NoUpdate
	@Title("权限名称")
	private String name;
	@Field("(case when menuid='#{T(config.Res.MENU).APPLICATION}' then T_application.description when menuid='#{T(config.Res.MENU).DATABASE}' then T_database.description else '' end) description")
	@NoInsert
	@NoUpdate
	@Title("权限描述")
	private String description;
	@NoQuery
	@NoInsert
	@NoUpdate
	@Left(table="T_Application",on="id",fields="name,description")
	@Field("dataId")
	private int appid;
	@NoQuery
	@NoInsert
	@NoUpdate
	@Left(table="T_Database",on="id",fields="name,description")
	@Field("dataId")
	private int dbid;

	public int getId() {
		return id;
	}
	public RoleRightForm setId(int id) {
		this.id = id;
		return this;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
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
	public int getAppid() {
		return appid;
	}
	public void setAppid(int appid) {
		this.appid = appid;
	}
	public int getDbid() {
		return dbid;
	}
	public void setDbid(int dbid) {
		this.dbid = dbid;
	}


	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}


}
