package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 简单数据字典模型
 * 只提供k/v
 * @author 范大德
 *
 */
@Table("T_Dict")
public class SimpleDictForm extends DefaultForm {
	@Field("DKey")
	private String dKey;//数据字典键
	@Field("DValue")
	private String dValue;//数据字典值
	public SimpleDictForm() {}
	public SimpleDictForm(String dKey, String dValue) {
		super();
		this.dKey = dKey;
		this.dValue = dValue;
	}
	public String getDKey() {
		return dKey;
	}
	public void setDKey(String dKey) {
		this.dKey = dKey;
	}
	public String getDValue() {
		return dValue;
	}
	public void setDValue(String dValue) {
		this.dValue = dValue;
	}

}
