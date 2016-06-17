package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 方法参数名称对象
 * @author 张亚平
 *
 */
@Table("T_InterfaceMethodParameter")
public class ParamNameForm extends DefaultForm{
	@PK
	@NoInsert
	@NoUpdate	
	private int id;//主键ID
	@Field("FieldName")
	private String name;//参数名称
	public ParamNameForm() {	}
	public int getId() {
		return id;
	}
	public ParamNameForm setId(int id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public ParamNameForm setName(String name) {
		this.name = name;
		return this;
	}

}
