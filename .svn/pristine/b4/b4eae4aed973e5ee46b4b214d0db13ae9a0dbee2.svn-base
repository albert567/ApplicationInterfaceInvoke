package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 对象字段
 * @author 张亚平
 *
 */
@Table("T_ObjRelation")
public class DbObjRelNameForm  extends DefaultForm{
	@PK
	private int id;//关联id
	@Left(table="T_DataBaseObj",on="ID",fields={"ObjName"})
	private int childID;//对象ID
	@Field("T_DataBaseObj.ObjName")
	private String name;//数据库对象名称
	public int getId() {
		return id;
	}
	public DbObjRelNameForm setId(int id) {
		this.id = id;
		return this;
	}
	public int getChildID() {
		return childID;
	}
	public void setChildID(int childID) {
		this.childID = childID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
