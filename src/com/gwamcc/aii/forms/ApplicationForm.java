package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.form.anno.QueryForm;
import com.gwamcc.aii.util.form.anno.Title;
import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 应用信息对象
 * @author 范大德
 *
 */
@Table("T_Application")
@QueryForm(title="应用系统")
public class ApplicationForm extends DefaultForm{
	@PK
	@NoInsert
	private int id;//应用id
	@Title("应用名称")
	private String name;//应用名称
	@Field("NameEn")
	private String nameEn;//应用en名称
	@Left(table="T_Dict",on="DKey",fields={"DValue"})
	@Field("AppType")
	private String appType;//应用类型Key
	@Field("T_Dict.DValue")
	@NoInsert
	@NoUpdate
	@Title("应用类型")
	private String appTypeName;//应用类型名
	@Title("应用描述")
	private String description;//应用描述
	private int sn;
	@Field("IsValid")
	private int valid;//应用有效性
	public ApplicationForm() {	}
	public ApplicationForm(int id, String name, String nameEn, String appType, String description, int sn,
			int valid) {
		super();
		this.id = id;
		this.name = name;
		this.nameEn = nameEn;
		this.appType = appType;
		this.description = description;
		this.sn = sn;
		this.valid = valid;
	}
	public int getId() {
		return id;
	}
	public ApplicationForm setId(int id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
	public String getAppTypeName() {
		return appTypeName;
	}
	public void setAppTypeName(String appTypeName) {
		this.appTypeName = appTypeName;
	}
}
