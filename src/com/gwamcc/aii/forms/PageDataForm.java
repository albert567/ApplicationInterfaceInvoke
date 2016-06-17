package com.gwamcc.aii.forms;

import java.util.List;

/**
 * 分页数据模板
 * @author 范大德
 *
 */
public class PageDataForm extends DefaultForm {
	private int total;//总行数
	private List<DefaultForm>rows;//当前页数据

	public PageDataForm(int total, List<DefaultForm> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<DefaultForm> getRows() {
		return rows;
	}
	public void setRows(List<DefaultForm> rows) {
		this.rows = rows;
	}

}
