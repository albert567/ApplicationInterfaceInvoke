package com.gwamcc.aii.dao.core;

import java.util.List;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.util.excel.formattor.ValueFormatter;

/**
 * 提供数据模型列表的单元格格式化工具
 * @author 范大德
 *
 */
public abstract class FormListValueFormatter extends ValueFormatter {
	private List<DefaultForm>formList;

	public FormListValueFormatter(List<DefaultForm>formList) {
		this.formList=formList;
	}
	/**
	 * 获取数据模型列表
	 * @return
	 */
	public List<DefaultForm> getFormList() {
		return formList;
	}

}
