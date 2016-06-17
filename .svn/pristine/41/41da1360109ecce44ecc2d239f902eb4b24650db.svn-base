package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 存储过程参数列表
 * @author 张亚平
 *
 */
@Table("T_ObjParam")
public class ProParamNameForm extends DefaultForm{
	@PK
	@NoUpdate
	@NoInsert
	@Field("ID")
	private int id;//参数ID
	@Field("Name")
	private String name;//参数名称
	public int getId() {
		return id;
	}
	public ProParamNameForm setId(int id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public ProParamNameForm setName(String name) {
		this.name = name;
		return this;
	}


}
