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
 * 数据库和数据库对象关联对象
 * @author 张亚平
 *
 */
@Table("T_DataBaseObj")
@QueryForm(title="数据库对象")
public class DataBaseObjForm extends DefaultForm {
	@PK
	@Field("ID")
	@NoInsert
	@NoUpdate
	private int objID;//数据库对象ID
	@Field("ObjName")
	@Title("对象名称")
	private String objName;//数据库对象名称
	@Field("DataBaseID")
	@Left(table="T_DataBase",on="id",fields={"Name"})
	private int dbID;//数据库ID
	@Field("T_DataBase.Name as DbName")
	@NoInsert
	@NoUpdate
	@Title("数据库名称")
	private String dbName;//数据库名称
	@Field("IsValid")
	private String isValid;//是否有效
	@Field("ObjType")
	@Left(table="T_Dict",on="DKey",fields={"DValue"})
	private String objTypeID;//对象类型ID
	@Field("T_Dict.DValue")
	@NoInsert
	@NoUpdate
	@Title("对象类型")
	private String objTypeName;//数据库对象类型
	@Field("ObjDescription")
	@Title("对象描述")
	private String description;//数据库对象描述
	private int sn;//排序号
	public int getObjID() {
		return objID;
	}
	public void setObjID(int objID) {
		this.objID = objID;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
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
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getObjTypeID() {
		return objTypeID;
	}
	public void setObjTypeID(String objTypeID) {
		this.objTypeID = objTypeID;
	}
	public String getObjTypeName() {
		return objTypeName;
	}
	public void setObjTypeName(String objTypeName) {
		this.objTypeName = objTypeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
}
