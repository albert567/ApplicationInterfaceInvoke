package com.gwamcc.aii.forms;

/**
 * 文档搜索参数数据模型
 * @author 张亚平
 *
 */
public class AttachSearchForm extends DefaultForm{
	private String type;//function
	private int appID;//应用系统id
	private int id;//应用功能ID
	private String docType;//文档类型
	private String docName;//文档名称
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
}
