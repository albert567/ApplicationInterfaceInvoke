package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 数据库对象字段源数据对象(导入功能专用form)
 * @author 张亚平
 *
 */
@Table("T_ObjColSource")
public class ColSrcForm extends DefaultForm {
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
	@Left(table="T_DataBaseObj DbObj",on="id",fields={"DataBaseID"})
	private String objID;//目标对象ID
	@Field("DbObj.DataBaseID as DbID")
	@NoInsert
	@NoUpdate
	private String dbID;//目标数据库ID
	@Field("SourceID")
	@Left(table="T_DataBaseObjCol",on="id",fields={"ObjID"})
	private int srcID;//源字段ID
	@Field("T_DataBaseObjCol.ObjID as SrcObjID")
	@NoInsert
	@NoUpdate
    @Left(table="T_DataBaseObj",on="id",fields={"DataBaseID"})
	private String srcObjID;//源字段对象ID
	@Field("T_DataBaseObj.DataBaseID as SrcDbID")
	@NoInsert
	@NoUpdate
	private String srcDbID;//源字段数据库ID
	@Field("T_ObjColSource.IsValid")
	private String isValid;//是否有效
	
	public int getId() {
		return id;
	}
	public ColSrcForm setId(int id) {
		this.id = id;
		return this;
	}
	public int getColID() {
		return colID;
	}
	public ColSrcForm setColID(int colID) {
		this.colID = colID;
		return this;
	}
	public String getSrcObjID() {
		return srcObjID;
	}
	public ColSrcForm setSrcObjID(String srcObjID) {
		this.srcObjID = srcObjID;
		return this;
	}
	public String getSrcDbID() {
		return srcDbID;
	}
	public ColSrcForm setSrcDbID(String srcDbID) {
		this.srcDbID = srcDbID;
		return this;
	}
	public int getSrcID() {
		return srcID;
	}
	public ColSrcForm setSrcID(int srcID) {
		this.srcID = srcID;
		return this;
	}
	public String getIsValid() {
		return isValid;
	}
	public ColSrcForm setIsValid(String isValid) {
		this.isValid = isValid;
		return this;
	}
	public String getObjID() {
		return objID;
	}
	public ColSrcForm setObjID(String objID) {
		this.objID = objID;
		return this;
	}
	public String getDbID() {
		return dbID;
	}
	public ColSrcForm setDbID(String dbID) {
		this.dbID = dbID;
		return this;
	}
}
