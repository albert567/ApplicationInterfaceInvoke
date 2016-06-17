package com.gwamcc.aii.exception;

import com.gwamcc.aii.forms.UploadMsgForm;

/**
 * Excel导入异常
 * @author 范大德
 *
 */
public class ExcelImportException extends DefaultException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	private UploadMsgForm msg;


	public ExcelImportException(UploadMsgForm msg) {
		super("数据导入出错!");
		this.msg=msg;
	}

	public ExcelImportException(UploadMsgForm msg,String message) {
		super(message);
		this.msg=msg;
	}

	public ExcelImportException(String arg0) {
		super(arg0);
	}

	public UploadMsgForm getMsg() {
		return msg;
	}


	@Override
	public String getView() {
		return "/exception/excel-import";
	}



}
