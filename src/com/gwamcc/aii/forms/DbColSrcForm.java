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
 * 数据库对象字段源数据对象
 * @author 张亚平
 *
 */
@Table("T_ObjColSource")
@QueryForm(title="数据库字段源")
public class DbColSrcForm extends DefaultForm {
	@PK
	@Field("ID")
	@NoInsert
	@NoUpdate
	private int id;//主键ID
	@Field("ColID")
	@Left(table="T_DataBaseObjCol ObjCol",on="id",fields={"ObjID"})
	private int colID;//目标字段ID
	@Field("ObjCol.ObjID")
	@NoInsert
	@NoUpdate
	@NoQuery
	@Left(table="T_DataBaseObj DbObj",on="id",fields={"DataBaseID"})
	private int objID;//目标对象ID
	@Field("ObjCol.ObjID as DbObjID")
	@NoInsert
	@NoUpdate
	private int dbObjID;
	@Field("DbObj.DataBaseID as DbID")
	@NoInsert
	@NoUpdate
	private int dbID;//目标数据库ID
	@Field("SourceID")
	@Left(table="T_DataBaseObjCol",on="id",fields={"ColumnName","Description","ColumnType","ColumnLength","ObjID"})
	private int srcID;//源字段ID
	@Field("T_DataBaseObjCol.ColumnName")
	@NoInsert
	@NoUpdate
	@Title("源字段名")
	private String srcName;//源字段名称
	@Field("T_DataBaseObjCol.Description")
	@NoInsert
	@NoUpdate
	@Title("源字段描述")
	private String srcDesc;//源字段描述
	@Field("T_DataBaseObjCol.ColumnType")
	@NoInsert
	@NoUpdate
	@Left(table="T_Dict",on="DKey",fields={"DValue"})
	private String srcTypeID;//源字段类型ID
	@Field("T_Dict.DValue")
	@NoInsert
	@NoUpdate
	@Title("源字段类型")
	private String srcTypeName;//源字段类型名称
	@Field("T_DataBaseObjCol.ColumnLength")
	@NoInsert
	@NoUpdate
	@Title("源字段长度")
	private int srcLength;//源字段类型长度
	@Field("T_DataBaseObjCol.ObjID")
	@NoInsert
	@NoUpdate
    @Left(table="T_DataBaseObj",on="id",fields={"ObjName","DataBaseID"})
	private int srcObjID;//源字段对象ID
	@Field("T_DataBaseObj.ObjName")
	@NoInsert
	@NoUpdate
	@Title("源字段对象名称")
	private String srcObjName;//源字段对象名称
	@Field("T_DataBaseObj.DataBaseID")
	@NoInsert
	@NoUpdate
	@Left(table="T_DataBase",on="id",fields={"Name"})
	private int srcDbID;//源字段数据库ID
	@Field("T_DataBase.Name")
	@NoInsert
	@NoUpdate
	@Title("源字段数据库名称")
	private String srcDbName;//源字段数据库名称
	@Field("T_ObjColSource.IsValid")
	private String isValid;//是否有效

	public int getId() {
		return id;
	}
	public DbColSrcForm setId(int id) {
		this.id = id;
		return this;
	}
	public int getColID() {
		return colID;
	}
	public DbColSrcForm setColID(int colID) {
		this.colID = colID;
		return this;
	}
	public String getSrcDesc() {
		return srcDesc;
	}
	public DbColSrcForm setSrcDesc(String srcDesc) {
		this.srcDesc = srcDesc;
		return this;
	}
	public String getSrcTypeID() {
		return srcTypeID;
	}
	public DbColSrcForm setSrcTypeID(String srcTypeID) {
		this.srcTypeID = srcTypeID;
		return this;
	}
	public String getSrcTypeName() {
		return srcTypeName;
	}
	public DbColSrcForm setSrcTypeName(String srcTypeName) {
		this.srcTypeName = srcTypeName;
		return this;
	}
	public int getSrcLength() {
		return srcLength;
	}
	public DbColSrcForm setSrcLength(int srcLength) {
		this.srcLength = srcLength;
		return this;
	}
	public int getSrcObjID() {
		return srcObjID;
	}
	public DbColSrcForm setSrcObjID(int srcObjID) {
		this.srcObjID = srcObjID;
		return this;
	}
	public String getSrcObjName() {
		return srcObjName;
	}
	public DbColSrcForm setSrcObjName(String srcObjName) {
		this.srcObjName = srcObjName;
		return this;
	}
	public int getSrcDbID() {
		return srcDbID;
	}
	public DbColSrcForm setSrcDbID(int srcDbID) {
		this.srcDbID = srcDbID;
		return this;
	}
	public String getSrcDbName() {
		return srcDbName;
	}
	public DbColSrcForm setSrcDbName(String srcDbName) {
		this.srcDbName = srcDbName;
		return this;
	}
	public int getSrcID() {
		return srcID;
	}
	public DbColSrcForm setSrcID(int srcID) {
		this.srcID = srcID;
		return this;
	}
	public String getSrcName() {
		return srcName;
	}
	public DbColSrcForm setSrcName(String srcName) {
		this.srcName = srcName;
		return this;
	}
	public String getIsValid() {
		return isValid;
	}
	public DbColSrcForm setIsValid(String isValid) {
		this.isValid = isValid;
		return this;
	}
	public int getObjID() {
		return objID;
	}
	public DbColSrcForm setObjID(int objID) {
		this.objID = objID;
		return this;
	}
	public int getDbID() {
		return dbID;
	}
	public DbColSrcForm setDbID(int dbID) {
		this.dbID = dbID;
		return this;
	}
	public int getDbObjID() {
		return dbObjID;
	}
	public DbColSrcForm setDbObjID(int dbObjID) {
		this.dbObjID = dbObjID;
		return this;
	}

}
