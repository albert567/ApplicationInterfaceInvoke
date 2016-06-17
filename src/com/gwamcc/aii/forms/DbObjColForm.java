package com.gwamcc.aii.forms;

import java.util.List;

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
@Table("T_DataBaseObjCol")
@QueryForm(title="数据库字段")
public class DbObjColForm extends DefaultForm {
	@PK
	@Field("ID")
	@NoInsert
	@NoUpdate
	private int colID;//字段ID
	@Title("字段名称")
	private String columnName;//字段名称
	@Field("ColumnType")
	@Left(table="T_Dict",on="DKey",fields={"DValue"})
	private String columnTypeID;//字段类型ID
	@Field("T_Dict.DValue")
	@NoInsert
	@NoUpdate
	@Title("字段类型")
	private String columnTypeName;//字段类型
	@Field("ColumnLength")
	@Title("字段长度")
	private int length;//字段长度
	@Left(table="T_DataBaseObj",on="ID",fields={"ObjName","DataBaseID"})
	private int objID;//对象ID
	@Field("T_DataBaseObj.ObjName")
	@NoInsert
	@NoUpdate
	@Title("对象名称")
	private String objName;//对象名称
	@Field("T_DataBaseObj.DataBaseID")
	@Left(table="T_DataBase",on="ID",fields={"Name"})
	@NoInsert
	@NoUpdate
	private int dbID;//数据库ID
	@Field("T_DataBase.Name as DbName")
	@NoInsert
	@NoUpdate
	@Title("数据库名称")
	private String dbName;//数据库名称
	@Field("IsValid")
	private String isValid;//是否有效
	@Title("字段描述")
	private String description;//字段描述
	@NoQuery
	@NoUpdate
	@NoInsert
	private List<String>src;
	@NoQuery
	@NoUpdate
	@NoInsert
	private String srcDesc;

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
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public int getColID() {
		return colID;
	}
	public void setColID(int colID) {
		this.colID = colID;
	}
	public String getColumnTypeID() {
		return columnTypeID;
	}
	public void setColumnTypeID(String columnTypeID) {
		this.columnTypeID = columnTypeID;
	}
	public String getColumnTypeName() {
		return columnTypeName;
	}
	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getSrc() {
		return src;
	}
	public void setSrc(List<String> src) {
		this.src = src;
	}
	public String getSrcDesc() {
		return srcDesc;
	}
	public void setSrcDesc(String srcDesc) {
		this.srcDesc = srcDesc;
	}
}
