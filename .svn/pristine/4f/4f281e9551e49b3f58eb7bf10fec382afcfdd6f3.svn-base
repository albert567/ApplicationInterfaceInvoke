package com.gwamcc.aii.util.excel.formattor;
import org.apache.poi.ss.usermodel.Row;

import com.gwamcc.aii.util.ExcelKit;

/**
 * Excel单元格值格式化工具
 * @author 范大德
 *
 */
public abstract class ValueFormatter {
	private ExcelKit excelKit;//excel操作类
	private Row row;//值所在行
	/**
	 * 格式化操作
	 * @param value	待格式化的值
	 * @return	格式化之后的值
	 */
	public abstract Object format(Object value);
	/**
	 * 获取当前行指定单元格未格式化的值
	 * @param index	单元格ID
	 * @return
	 */
	public Object getCellValue(int index){
		return getExcelKit().getCellValueWithOutFormat(getRow().getCell(index));
	}
	/**
	 * 获取当前行指定的单元格格式化后的值
	 * @param index	单元格Id
	 * @return
	 */
	public Object getFormatValue(int index){
		return getExcelKit().getCellValue(getRow().getCell(index));
	}

	/**
	 * 获取Excel操作类
	 * @return
	 */
	private ExcelKit getExcelKit() {
		return excelKit;
	}
	/**
	 * 设置Excel操作类
	 * @param excelKit
	 */
	public void setExcelKit(ExcelKit excelKit) {
		this.excelKit = excelKit;
	}
	/**
	 * 获取当前行
	 * @return
	 */
	private Row getRow() {
		return row;
	}
	/**
	 * 设置当前行
	 * @param row
	 * @return
	 */
	public ValueFormatter setRow(Row row) {
		this.row = row;
		return this;
	}
}
