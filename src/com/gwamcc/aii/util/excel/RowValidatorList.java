package com.gwamcc.aii.util.excel;

import java.util.ArrayList;
import java.util.List;

import com.gwamcc.aii.util.excel.validator.InListRowValidator;
import com.gwamcc.aii.util.excel.validator.RegexRowValidator;
import com.gwamcc.aii.util.excel.validator.RequiredRowlValidator;
import com.gwamcc.aii.util.excel.validator.UniqueRowValidator;


/**
 * 行验证器列表
 * @author 范大德
 *
 */
public class RowValidatorList {
	private List<RowValidator>list=new ArrayList<RowValidator>();
	private int[]columns;
	public RowValidatorList(int...columns) {
		this.columns=columns;
	}
	public RowValidatorList(int[]columns,RowValidator ...validators) {
		for(RowValidator validator:validators){
			list.add(validator);
		}
		this.columns=columns;
	}


	/**
	 * 给当前行添加验证器
	 * @param validator
	 * @return
	 */
	public RowValidatorList add(RowValidator validator){
		list.add(validator);
		return this;
	}

	/**
	 * 添加一个唯一值验证器
	 * @return
	 */
	public RowValidatorList unique(){
		return add(new UniqueRowValidator(columns));
	}
	/**
	 * 添加一个非空验证器
	 * @return
	 */
	public RowValidatorList required(){
		return add(new RequiredRowlValidator(columns));
	}
	/**
	 * 添加一个非空验证
	 * @param msg	验证失败时提示此消息
	 * @return
	 */
	public RowValidatorList required(String msg){
		return add(new RequiredRowlValidator(columns).setMsg(msg));
	}
	/**
	 * 添加一个数据列表验证
	 * @param list	值应所在列表
	 * @return
	 */
	public RowValidatorList in(List<Object>list){
		return add(new InListRowValidator(getColumn(),list));
	}
	/**
	 * 添加一个数据列表验证
	 * @param list	列表
	 * @param msg	验证失败时返回的消息
	 * @return
	 */
	public RowValidatorList in(List<Object>list,String msg){
		return add(new InListRowValidator(getColumn(),list).setMsg(msg));
	}
	/**
	 * 添加一个数据数组验证
	 * @param array	验证数组
	 * @return
	 */
	public RowValidatorList in(Object...array){
		List<Object>list=new ArrayList<Object>();
		for(Object obj:array){
			list.add(obj);
		}
		return add(new InListRowValidator(getColumn(),list));
	}
	/**
	 * 添加一个正则表达式验证
	 * @param regex
	 * @return
	 */
	public RowValidatorList regex(String regex){
		return add(new RegexRowValidator(regex,columns));
	}
	/**
	 * 添加一个正则表达式验证
	 * @param regex
	 * @param msg	验证失败后返回的消息
	 * @return
	 */
	public RowValidatorList regex(String regex,String msg){
		return add(new RegexRowValidator(regex,columns).setMsg(msg));
	}
	/**
	 * 获取当前验证器列表
	 * @return
	 */
	public List<RowValidator>list(){
		return list;
	}

	/**
	 * 获取当前列表绑定的字段数组
	 * @return
	 */
	public int[]getColumns() {
		return columns;
	}
	/**
	 * 获取当前列表绑定的字段
	 * 	如有多个字段返回第一个
	 * @return
	 */
	public int getColumn() {
		return (columns==null||columns.length<1)?0:columns[0];
	}
}
