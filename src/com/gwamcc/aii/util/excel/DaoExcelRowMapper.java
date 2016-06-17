package com.gwamcc.aii.util.excel;

import com.gwamcc.aii.dao.core.DefaultDao;

/**
 * Excel行数据库操作导航
 * @author 范大德
 *
 */
public abstract class DaoExcelRowMapper extends ExcelRowMapper{
	private DefaultDao dao;
	@SuppressWarnings("rawtypes")
	private Class cs;
	@SuppressWarnings("rawtypes")
	public DaoExcelRowMapper(DefaultDao dao,Class cs){
		this.dao=dao;
		this.cs=cs;
	}
	/**
	 * 获取数据操作类
	 * @return
	 */
	public DefaultDao getDao() {
		return dao;
	}

	/**
	 * 获取数据模型类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Class getCs() {
		return cs;
	}
	/**
	 * 获取字段对象数组
	 * @return
	 */
	public String[] getFields() {
		return getKit().getTitles();
	}

}
