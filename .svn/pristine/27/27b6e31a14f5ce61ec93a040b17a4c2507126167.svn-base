package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 应用接口调用对象
 * @author 张亚平
 *
 */
@Table("T_ApplicationInterfaceInvoke")
public class InvokeForm extends DefaultForm{
	@PK
	@NoInsert
	@NoUpdate
	@Field("ID")
	private int interInvokeID;//主键ID
	@Field("InterfaceID")
	private int interID;//接口ID
	@Field("InterfaceMethodID")
	private int methodID;//方法ID
	@Field("InVokeApplicationID")
	private int invokeAppID;//调用方应用ID
	private String isValid;//是否有效 1有效 0无效
	private String description;//描述
	private String createDate;//创建日期
	private int createEmpID;//创建人员
	private int sn;//排序ID
	@NoInsert
	private String stopDate;//停用时间
	public int getInterInvokeID() {
		return interInvokeID;
	}
	public InvokeForm setInterInvokeID(int interInvokeID) {
		this.interInvokeID = interInvokeID;
		return this;
	}
	public int getInterID() {
		return interID;
	}
	public InvokeForm setInterID(int interID) {
		this.interID = interID;
		return this;
	}
	public int getMethodID() {
		return methodID;
	}
	public InvokeForm setMethodID(int methodID) {
		this.methodID = methodID;
		return this;
	}
	public int getInvokeAppID() {
		return invokeAppID;
	}
	public InvokeForm setInvokeAppID(int invokeAppID) {
		this.invokeAppID = invokeAppID;
		return this;
	}
	public String getIsValid() {
		return isValid;
	}
	public InvokeForm setIsValid(String isValid) {
		this.isValid = isValid;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public InvokeForm setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getCreateDate() {
		return createDate;
	}
	public InvokeForm setCreateDate(String createDate) {
		this.createDate = createDate;
		return this;
	}
	public int getCreateEmpID() {
		return createEmpID;
	}
	public InvokeForm setCreateEmpID(int createEmpID) {
		this.createEmpID = createEmpID;
		return this;
	}
	public int getSn() {
		return sn;
	}
	public InvokeForm setSn(int sn) {
		this.sn = sn;
		return this;
	}
	public String getStopDate() {
		return stopDate;
	}
	public InvokeForm setStopDate(String stopDate) {
		this.stopDate = stopDate;
		return this;
	}
}
