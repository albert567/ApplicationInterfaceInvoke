package com.gwamcc.aii.util.excel.validator;

import java.util.Map;
import java.util.regex.Pattern;

import com.gwamcc.aii.util.excel.RowValidator;

/**
 * 正则表达式验证器
 * 	如果值为空返回true
 * @author 范大德
 *
 */
public class RegexRowValidator extends RowValidator {

	private String regex;

	public RegexRowValidator(String regex,int...columns) {
		super(columns);
		this.regex=regex;
		setMsg("不是有效的!");
	}

	@Override
	public boolean validate(Map<Integer,Object> valueMap) {
		for(int col:getColumns()){
			if(valueMap.get(col)!=null&&!Pattern.matches(regex, (String)valueMap.get(col))){
				return false;
			}
		}
		return true;
	}

}
