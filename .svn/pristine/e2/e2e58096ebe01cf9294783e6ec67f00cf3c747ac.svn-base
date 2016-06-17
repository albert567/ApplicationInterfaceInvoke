package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoQuery;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 接口方法对象
 * @author 张亚平
 *
 */
@Table("T_InterfaceMethod")
public class MethodForm extends DefaultForm{
	@PK
	@NoInsert
	@NoUpdate
	private int id;//主键ID
	private String name;//方法名称
	@Left(table="T_Interface",on="ID",fields={"Name"})
	private int interfaceID;//接口ID
	private int rw;//读写,0读,1写
	private String methodTypeID;//方法类型（0301方法，0302存储过程）
	private String description;//方法描述
	private int sn;//排序ID
	private String isValid;//是否有效 1有效 0无效
	private int createEmpID;//创建人员ID
	private String createDate;//创建日期 yyyy-MM-dd
	@NoInsert
	@NoUpdate
	@NoQuery
	private String appName;//导入功能用
	public int getId() {
		return id;
	}
	public MethodForm setId(int id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public MethodForm setName(String name) {
		this.name = name;
		return this;
	}
	public int getInterfaceID() {
		return interfaceID;
	}
	public MethodForm setInterfaceID(int interfaceID) {
		this.interfaceID = interfaceID;
		return this;
	}
	public int getRw() {
		return rw;
	}
	public MethodForm setRw(int rw) {
		this.rw = rw;
		return this;
	}
	public String getMethodTypeID() {
		return methodTypeID;
	}
	public MethodForm setMethodTypeID(String methodTypeID) {
		this.methodTypeID = methodTypeID;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public MethodForm setDescription(String description) {
		this.description = description;
		return this;
	}
	public int getSn() {
		return sn;
	}
	public MethodForm setSn(int sn) {
		this.sn = sn;
		return this;
	}
	public String getIsValid() {
		return isValid;
	}
	public MethodForm setIsValid(String isValid) {
		this.isValid = isValid;
		return this;
	}
	public int getCreateEmpID() {
		return createEmpID;
	}
	public MethodForm setCreateEmpID(int createEmpID) {
		this.createEmpID = createEmpID;
		return this;
	}
	public String getCreateDate() {
		return createDate;
	}
	public MethodForm setCreateDate(String createDate) {
		this.createDate = createDate;
		return this;
	}
	public String getAppName() {
		return appName;
	}
	public MethodForm setAppName(String appName) {
	    this.appName = appName;
		return this;
	}
}
