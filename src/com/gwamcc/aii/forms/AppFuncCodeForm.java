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
 * 应用功能源码关联数据模型
 * @author 范大德
 *
 */
@Table("T_AppFuncCode")
@QueryForm(title="应用功能与源码")
public class AppFuncCodeForm extends DefaultForm{
	@PK
	@NoInsert
	@NoUpdate
	private int id;//ID
	@Field("FuncID")
	@Left(table="T_ApplicationFunction",on="ID",fields={"NAME"})
	private int funcID;//功能ID
	@NoInsert
	@NoUpdate
	@Field("T_ApplicationFunction.Name as FuncName")
	@Title("功能名称")
	private String funcName;
	@Field("CodeID")
	@Left(table="T_CodeStructure",on="ID",fields={"Name","ParentID"})
	private int codeID;//CodeID
	@Field("T_CodeStructure.Name")
	@NoInsert
	@NoUpdate
	@Title("源码名称")
	private String codeName;
	@Field("dbo.F_GetPath(T_CodeStructure.ID) as CodePath")
	@NoInsert
	@NoUpdate
	private String codePath;
	@Field("T_CodeStructure.ParentID")
	@NoInsert
	@NoUpdate
	private String parentID;//父级ID
	public AppFuncCodeForm(){}
	public AppFuncCodeForm(int funcID,int codeID){
		this.funcID=funcID;
		this.codeID=codeID;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFuncID() {
		return funcID;
	}
	public void setFuncID(int funcID) {
		this.funcID = funcID;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public int getCodeID() {
		return codeID;
	}
	public void setCodeID(int codeID) {
		this.codeID = codeID;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	public String getCodePath() {
		return codePath;
	}
	public void setCodePath(String codePath) {
		this.codePath = codePath;
	}
}
