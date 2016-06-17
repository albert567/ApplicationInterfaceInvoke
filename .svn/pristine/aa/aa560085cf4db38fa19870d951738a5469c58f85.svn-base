package com.gwamcc.aii.forms;

import java.util.ArrayList;
import java.util.List;

/**
 * 导入信息模型
 * @author 范大德
 *
 */
public class UploadMsgForm extends DefaultForm {
	private int total;//成功个数
	private boolean success;//是否成功
	private List<String>errMsg;//错误消息列表


	public int getTotal() {
		return total;
	}
	public UploadMsgForm setTotal(int total) {
		this.total = total;
		return this;
	}
	public boolean getSuccess() {
		return success;
	}
	public UploadMsgForm setSuccess(boolean success) {
		this.success = success;
		return this;
	}
	public List<String> getErrMsg() {
		return errMsg;
	}
	public UploadMsgForm setErrMsg(List<String> errMsg) {
		this.errMsg = errMsg;
		return this;
	}

	public UploadMsgForm addErrMsg(String msg){
		if(errMsg==null)
			errMsg=new ArrayList<String>();
		errMsg.add(msg);
		return this;
	}

}
