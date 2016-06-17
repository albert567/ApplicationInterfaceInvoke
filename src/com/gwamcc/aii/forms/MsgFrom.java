package com.gwamcc.aii.forms;

/**
 * 执行结果数据对象
 * @author 范大德
 *
 */
public class MsgFrom extends DefaultForm {
	private boolean success;//是否成功
	private String message;//返回消息

	public MsgFrom(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	/**
	 * 获取一个错误消息对象
	 * @param message
	 * @return
	 */
	public static MsgFrom err(String message){
		return new MsgFrom(false, message);
	}

	/**
	 * 获取一个成功消息对象
	 * @param message
	 * @return
	 */
	public static MsgFrom info(String message){
		return new MsgFrom(true, message);
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
