package com.gwamcc.aii.util.excel.validator;

import java.util.Map;

import com.gwamcc.aii.util.excel.RowValidator;

/**
 * 非空验证器
 * @author 范大德
 *
 */
public class RequiredRowlValidator extends RowValidator {

	public RequiredRowlValidator(int...column) {
		super(column);
		setMsg("不能为空!");
	}

	@Override
	public boolean validate(Map<Integer, Object>valueMap) {
		for(int col:getColumns()){
			if(valueMap.get(col)==null){
				return false;
			}
		}
		return true;
	}

}
