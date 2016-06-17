package com.gwamcc.aii.util.excel;

import org.apache.poi.ss.usermodel.Row;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.util.ExcelKit;

/**
 * Excel行数据导航
 * @author 范大德
 *
 */
public abstract class ExcelRowMapper {
	private ExcelKit kit;
	/**
	 * 处理当前行数据
	 * @param row
	 * @return
	 * @throws Exception
	 */
	public abstract DefaultForm mapRow(Row row) throws Exception;

	/**
	 * 设置Excel操作类
	 * @param kit
	 * @return
	 */
	public ExcelRowMapper setKit(ExcelKit kit) {
		this.kit = kit;
		return this;
	}

	/**
	 * 获取Excel操作类
	 * @return
	 */
	public ExcelKit getKit() {
		return kit;
	}
}
