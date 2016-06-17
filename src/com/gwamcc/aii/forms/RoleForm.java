package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.form.anno.QueryForm;
import com.gwamcc.aii.util.form.anno.Title;
import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

@Table("T_Role")
@QueryForm("权限组")
public class RoleForm extends DefaultForm{
	@PK
	@NoInsert
	@NoUpdate
	private int id;
	@Title("权限组名")
	private String name;
	@Title("权限组描述")
	private String description;
	@Title("应用管理员")
	private int appAdmin;
	@Title("数据库管理员")
	private int dbAdmin;
	@NoInsert
	@NoUpdate
	@Field("(case when appAdmin=1 then 'on' else null end) app")
	private String app;
	@NoInsert
	@NoUpdate
	@Field("(case when dbAdmin=1 then 'on' else null end) db")
	private String db;

	public int getId() {
		return id;
	}
	public RoleForm setId(int id) {
		this.id = id;
		return this;
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
	public int getAppAdmin() {
		return appAdmin;
	}
	public void setAppAdmin(int appAdmin) {
		this.appAdmin = appAdmin;
	}
	public int getDbAdmin() {
		return dbAdmin;
	}
	public void setDbAdmin(int dbAdmin) {
		this.dbAdmin = dbAdmin;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}




}
