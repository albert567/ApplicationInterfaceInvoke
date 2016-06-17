package com.gwamcc.aii.forms;

import java.util.List;

import com.gwamcc.aii.util.form.anno.Title;
import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoQuery;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 单独数据库对象字段
 * @author 张亚平
 *
 */
@Table("T_DataBaseObjCol")
public class DbColForm extends DefaultForm {
	@PK
	private int id;//字段id
	@Title("字段名称")
	private String columnName;//字段名称
	@Left(table="T_Dict",on="DKey",fields={"DValue"})
	private String columnType;//字段类型ID
	@Field("T_Dict.DValue")
	@Title("字段类型")
	private String dValue;//字段类型
	@Field("ColumnLength")
	@Title("字段长度")
	private int length;//字段长度
	@Field("Description")
	@Title("字段描述")
	private String description;//字段描述
	@NoQuery
	@NoUpdate
	@NoInsert
	private List<String>src;
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDValue() {
		return dValue;
	}
	public void setDValue(String dValue) {
		this.dValue = dValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public List<String> getSrc() {
		return src;
	}
	public void setSrc(List<String> src) {
		this.src = src;
	}

}
