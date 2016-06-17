package com.gwamcc.aii.util.excel;

import java.util.Map;

/**
 * Excel行验证器
 * @author 范大德
 *
 */
public abstract class RowValidator {
	private int row;//当前行
	private Map<Integer, Object>valueMap;//验证字段格式化后值列表
	private Map<Integer, Object>displayMap;//验证字段格式化前值列表
	private int[]columns;//验证字段

	private String msg;

	/**
	 * 验证操作
	 * @param valueMap
	 * @return
	 */
	public abstract boolean validate(Map<Integer, Object>valueMap);
	/**
	 * 错误消息
	 * @return
	 */
	public String errMsg(){
		String cells="";
		String values="";
		for(int col:getColumns()){
			cells+=getColumnName(col)+(getRow()+1)+",";
			values+=(getValueMap().get(col)==null?getDisplayMap().get(col):getValueMap().get(col))+",";
		}
		if(cells.indexOf(",")>0){
			cells=cells.substring(0,cells.lastIndexOf(","));
		}
		if(values.indexOf(",")>0){
			values=values.substring(0,values.lastIndexOf(","));
		}
		return "单元格["+cells+"]值["+values+"]验证失败: "+(getMsg()==null?"":getMsg());
	}

	public RowValidator(int...columns){
		this.columns=columns;
	}

	/**
	 * 错误消息具体内容
	 * @return
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * 设置错误消息
	 * @param msg
	 * @return
	 */
	public RowValidator setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	/**
	 * 获取列名
	 * @param index
	 * @return
	 */
	private String getColumnName(int index){
	    String colCode = "";
	    char key='A';
	    int loop = index / 26;
	    if(loop>0){
	        colCode += getColumnName(loop-1);
	    }
	    key = (char) (key+index%26);
	    colCode += key;
	    return colCode;

	}

	/**
	 * 获取当前行
	 * @return
	 */
	public int getRow() {
		return row;
	}
	/**
	 * 设置当前行
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * 获取格式化后的值列表
	 * @return
	 */
	public Map<Integer, Object> getValueMap() {
		return valueMap;
	}
	/**
	 * 设置格式化后的值列表
	 * @param valueMap
	 */
	public void setValueMap(Map<Integer, Object> valueMap) {
		this.valueMap = valueMap;
	}
	public int[] getColumns() {
		return columns;
	}
	public void setColumns(int[]columns) {
		this.columns = columns;
	}
	public int getColumn(){
		return (columns==null||columns.length<1)?0:columns[0];
	}
	public void setDisplayMap(Map<Integer, Object> displayMap) {
		this.displayMap = displayMap;
	}
	public Map<Integer, Object> getDisplayMap() {
		return displayMap;
	}
}
