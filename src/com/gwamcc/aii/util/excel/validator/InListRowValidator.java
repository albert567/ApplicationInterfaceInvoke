package com.gwamcc.aii.util.excel.validator;

import java.util.List;
import java.util.Map;

import com.gwamcc.aii.util.excel.RowValidator;

/**
 * 验证单元格值是否存在于List中的验证器
 * @author 范大德
 *
 */
public class InListRowValidator extends RowValidator {

	List<Object> list;
	public InListRowValidator(int column,List<Object> list) {
		super(column);
		this.list=list;
		setMsg("不是有效的!");
	}

	@Override
	public boolean validate(Map<Integer, Object>valueMap) {

		for(Object item:list){
			if(item.equals(valueMap.get(getColumn()))){
				return true;
			}
		}
		return false;
	}
}
