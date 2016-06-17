package com.gwamcc.aii.util.excel.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gwamcc.aii.util.excel.RowValidator;

/**
 * 唯一值验证器
 * 	可进行多字段唯一值验证,验证范围仅限于当前Excel中
 * @author 范大德
 *
 */
public class UniqueRowValidator extends RowValidator {

	public UniqueRowValidator(int...columns) {
		super(columns);
		setMsg("存在重复值!");
	}

	private List<Map<Integer, Object>>values=new ArrayList<Map<Integer, Object>>();

	@Override
	public boolean validate(Map<Integer,Object> valueMap) {
		if(values.contains(valueMap)){
			return false;
		}
		values.add(valueMap);
		return true;
	}

}
