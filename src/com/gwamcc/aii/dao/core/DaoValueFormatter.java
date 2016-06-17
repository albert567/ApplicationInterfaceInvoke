package com.gwamcc.aii.dao.core;

import com.gwamcc.aii.util.excel.formattor.ValueFormatter;

/**
 * 提供数据库操作的Excel单元格格式化工具
 * @author 范大德
 *
 */
public abstract class DaoValueFormatter extends ValueFormatter {
	private DefaultDao dao;
	public DaoValueFormatter(DefaultDao dao) {
		this.dao=dao;
	}
	public DefaultDao getDao() {
		return dao;
	}
}
