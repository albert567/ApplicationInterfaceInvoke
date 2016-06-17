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
 * 应用数据库关联数据模型
 * @author 范大德
 *
 */
@Table("T_AppDataBase")
@QueryForm(title="应用数据库")
public class AppDbForm extends DefaultForm {
	@PK
	@NoInsert
	@NoUpdate
	private int id;//应用数据库关联ID
	@Field("ApplicationID")
	@Left(table="T_Application",on="ID",fields={"NAME"})
	private int appID;//应用名称
	@NoInsert
	@NoUpdate
	@Field("T_Application.Name as AppName")
	@Title("应用名称")
	private String appName;
	@Field("T_AppDataBase.DataBaseID")
	@Left(table="T_DataBase",on="ID",fields={"Name","NameEn","Description"})
	private int dbID;//数据库ID
	@Field("T_DataBase.Name as DbName")
	@NoInsert
	@NoUpdate
	@Title("数据库")
	private String dbName;//数据库名称
	@Field("T_DataBase.NameEn as DbNameEn")
	@NoInsert
	@NoUpdate
	private String dbNameEn;//数据库英文名
	@Field("T_DataBase.Description as DbDes")
	@NoInsert
	@NoUpdate
	@Title("数据库描述")
	private String dbDes;//数据库描述
	@Title("应用描述")
	private String description;//描述

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getDbID() {
		return dbID;
	}
	public void setDbID(int dbID) {
		this.dbID = dbID;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDbNameEn() {
		return dbNameEn;
	}
	public void setDbNameEn(String dbNameEn) {
		this.dbNameEn = dbNameEn;
	}
	public String getDbDes() {
		return dbDes;
	}
	public void setDbDes(String dbDes) {
		this.dbDes = dbDes;
	}
}
