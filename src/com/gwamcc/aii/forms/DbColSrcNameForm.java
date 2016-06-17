package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 数据库对象字段源数据对象
 * @author 张亚平
 *
 */
@Table("T_ObjColSource")
public class DbColSrcNameForm extends DefaultForm {
	@PK
	@Field("ID")
	@NoInsert
	@NoUpdate
	private int id;//主键ID
	@Field("ColID")
	private int colID;//字段ID
	@Field("SourceID")
	@Left(table="T_DataBaseObjCol",on="id",fields={"ColumnName"})
	private int srcId;//源字段ID
	@Field("T_DataBaseObjCol.ColumnName")
	@NoInsert
	@NoUpdate
	private String srcName;//源字段名称
	
	public int getId() {
		return id;
	}
	public DbColSrcNameForm setId(int id) {
		this.id = id;
		return this;
	}
	public int getColID() {
		return colID;
	}
	public DbColSrcNameForm setColID(int colID) {
		this.colID = colID;
		return this;
	}
	public int getSrcId() {
		return srcId;
	}
	public DbColSrcNameForm setSrcId(int srcId) {
		this.srcId = srcId;
		return this;
	}
	public String getSrcName() {
		return srcName;
	}
	public DbColSrcNameForm setSrcName(String srcName) {
		this.srcName = srcName;
		return this;
	}
}
