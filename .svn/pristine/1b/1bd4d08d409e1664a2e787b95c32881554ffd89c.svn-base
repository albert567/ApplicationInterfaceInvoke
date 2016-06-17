package com.gwamcc.aii.dao.core;

import java.util.ArrayList;
import java.util.List;

import com.gwamcc.aii.util.excel.RowValidator;
import com.gwamcc.aii.util.excel.validator.RowValidateErrorListener;

/**
 * 导入模板验证错误监听
 * @author 范大德
 *
 */
public class UploadValidateErrorAdapter implements RowValidateErrorListener {


	private List<String>msgList=new ArrayList<String>();


	@Override
	public boolean onError(RowValidator validator) throws Exception {
		msgList.add(validator.errMsg());
		return true;
	}



	public List<String> getMsgList() {
		return msgList;
	}

}
