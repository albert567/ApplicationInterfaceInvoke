package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.form.anno.QueryForm;
import com.gwamcc.aii.util.form.anno.Title;
import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 数据字典数据模型
 * @author 范大德
 *
 */
@Table("T_Dict")
@QueryForm(title="数据字典")
public class DictForm extends DefaultForm {
	@PK
	@NoInsert
	@NoUpdate
	private int id;//数据字典ID
	@Title("字典类型")
	private String dtype;//数据字典类型

	@NoInsert
	@NoUpdate
	@Title("数据键")
	private String dkey;//数据键
	@Title("数据值")
	private String dvalue;//数据值
	@Title("数据描述")
	private String description;//数据描述
	private int isValid=1;//是否有效

	@Field("(case when T_Dict.dtype='00' then null else T_Dict.dtype end) as parent")
	@NoInsert
	@NoUpdate
	private String _parentId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDkey() {
		return dkey;
	}
	public void setDkey(String dkey) {
		this.dkey = dkey;
	}
	public String getDvalue() {
		return dvalue;
	}
	public void setDvalue(String dvalue) {
		this.dvalue = dvalue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsValid() {
		if("00".equals(getDtype())){
			return -1;
		}
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public String get_parentId() {
		return _parentId;
	}
	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
}
