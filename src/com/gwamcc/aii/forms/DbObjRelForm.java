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
 * 数据库对象和字段关联对象
 * @author 张亚平
 *
 */
@Table("T_ObjRelation")
@QueryForm(title="数据库对象和字段关联")
public class DbObjRelForm extends DefaultForm {
	@PK
	@Field("ID")
	@NoInsert
	@NoUpdate
	private int id;//关联ID
	@Field("ParentID")
	@Left(table="T_DataBaseObj Parent",on="ID",fields={"ObjName"})
	private int parentID;
	@NoInsert
	@NoUpdate
	@NoQuery
	@Field("Parent.ObjName as PrtObjName")
	@Title("所属对象")
	private String parentName;
	@Field("ChildID")
	@Left(table="T_DataBaseObj Child",on="ID",fields={"ObjName","ObjType","ObjDescription","DataBaseID"})
	private int childID;
	@Field("Child.ObjName")
	@NoInsert
	@NoUpdate
	@Title("子对象")
	private String childName;
	@Field("Child.ObjType")
	@Left(table="T_Dict ChildType",on="DKey",fields={"DValue"})
	@NoInsert
	@NoUpdate
	private String childTypeID;
	@NoInsert
	@NoUpdate
	@Field("ChildType.Dvalue")
	@Title("子对象类型")
	private String childTypeName;
	@Field("Child.ObjDescription")
	@NoInsert
	@NoUpdate
	@Title("子对象描述")
	private String childDesc;
	@Field("Child.DataBaseID")
	@NoInsert
	@NoUpdate
	@Left(table="T_DataBase ChildDb",on="ID",fields={"Name"})
	private int childDbID;
	@Field("ChildDb.Name")
	@NoInsert
	@NoUpdate
	@Title("子对象所属数据库")
	private String childDbName;
	@Field("T_ObjRelation.IsValid")
	private String isValid;
	@NoQuery
	@NoUpdate
	@NoInsert
	private String prtDbName;
	@NoQuery
	@NoUpdate
	@NoInsert
	private String chldDbName;
	public int getId() {
		return id;
	}
	public DbObjRelForm setId(int id) {
		this.id = id;
		return this;
	}
	public int getParentID() {
		return parentID;
	}
	public DbObjRelForm setParentID(int parentID) {
		this.parentID = parentID;
		return this;
	}
	public String getParentName() {
		return parentName;
	}
	public DbObjRelForm setParentName(String parentName) {
		this.parentName = parentName;
		return this;
	}
	public int getChildID() {
		return childID;
	}
	public DbObjRelForm setChildID(int childID) {
		this.childID = childID;
		return this;
	}
	public String getChildName() {
		return childName;
	}
	public DbObjRelForm setChildName(String childName) {
		this.childName = childName;
		return this;
	}
	public String getChildTypeID() {
		return childTypeID;
	}
	public DbObjRelForm setChildTypeID(String childTypeID) {
		this.childTypeID = childTypeID;
		return this;
	}
	public String getChildTypeName() {
		return childTypeName;
	}
	public DbObjRelForm setChildTypeName(String childTypeName) {
		this.childTypeName = childTypeName;
		return this;
	}
	public String getChildDesc() {
		return childDesc;
	}
	public DbObjRelForm setChildDesc(String childDesc) {
		this.childDesc = childDesc;
		return this;
	}
	public int getChildDbID() {
		return childDbID;
	}
	public DbObjRelForm setChildDbID(int childDbID) {
		this.childDbID = childDbID;
		return this;
	}
	public String getChildDbName() {
		return childDbName;
	}
	public DbObjRelForm setChildDbName(String childDbName) {
		this.childDbName = childDbName;
		return this;
	}
	public String getIsValid() {
		return isValid;
	}
	public DbObjRelForm setIsValid(String isValid) {
		this.isValid = isValid;
		return this;
	}
	public String getPrtDbName() {
		return prtDbName;
	}
	public DbObjRelForm setPrtDbName(String prtDbName) {
		this.prtDbName = prtDbName;
		return this;
	}
	public String getChldDbName() {
		return chldDbName;
	}
	public DbObjRelForm setChldDbName(String chldDbName) {
		this.chldDbName = chldDbName;
		return this;
	}
}
