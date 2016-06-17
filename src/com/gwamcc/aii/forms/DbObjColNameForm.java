package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 对象字段
 * @author 张亚平
 *
 */
@Table("T_DataBaseObjCol")
public class DbObjColNameForm  extends DefaultForm{
	@PK
	private int id;//字段id
	private String columnName;//数据库对象名称
	public DbObjColNameForm() {	}
	public int getId() {
		return id;
	}
	public DbObjColNameForm setId(int id) {
		this.id = id;
		return this;
	}
	public String getColumnName() {
		return columnName;
	}
	public DbObjColNameForm setColumnName(String columnName) {
		this.columnName = columnName;
		return this;
	}

}
