package com.gwamcc.aii.dao;

import java.util.List;

import com.gwamcc.aii.forms.AppFuncForm;
import com.gwamcc.aii.forms.AppInterForm;
import com.gwamcc.aii.forms.ApplicationForm;
import com.gwamcc.aii.forms.DataBaseForm;
import com.gwamcc.aii.forms.DataBaseObjForm;
import com.gwamcc.aii.forms.DbObjColForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.DictForm;
import com.gwamcc.aii.forms.InterMethodForm;
import com.gwamcc.aii.forms.MethodParamForm;
import com.gwamcc.aii.forms.ProcedureParamForm;
import com.gwamcc.aii.forms.RoleForm;

/**
 * 菜单树数据操作接口
 * @author 张亚平
 *
 */
public interface LeftMenuDao {
	/**
	 * 获取应用系统
	 * @return
	 */
	List<DefaultForm>getAppList();
	/**
	 * 获取应用菜单
	 * @param appID
	 * @return
	 */
	List<DefaultForm>getAppMenu(int appID);
	/**
	 * 获取应用功能
	 * @param appID
	 * @return
	 */
	List<DefaultForm>getFuncList(int appID);
	/**
	 * 获取子应用功能
	 * @param funcID
	 * @return
	 */
	List<DefaultForm>getFunc(int funcID);
	/**
	 * 获取接口类型
	 * @param interID
	 * @return
	 */
	public List<DefaultForm> getInterType(int appID);
	/**
	 * 获取应用接口
	 * @param appID
	 * @param interTypeID
	 * @return
	 */
	List<DefaultForm>getInterList(int appID,String interTypeID);
	/**
	 * 获取数据库
	 * @param appID
	 * @return
	 */
	List<DefaultForm>getDbList(int appID);
	/**
	 * 获取接口方法
	 * @param interID
	 * @return
	 */
	List<DefaultForm>getMethodList(int interID);
	/**
	 * 获取数据库接口存储过程
	 * @param interID
	 * @return
	 */
	List<DefaultForm> getProcedureList(final int interID);
	
	/**
	 * 获取方法菜单
	 * @param methodID
	 * @return
	 */
	List<DefaultForm>getMethodMenu(int methodID);
	/**
	 * 获取数据库
	 * @return
	 */
	List<DefaultForm>getDbList();
	/**
	 * 获取对象类型
	 * @param dbID
	 * @return
	 */
	List<DefaultForm>getObjType(int dbID);
	/**
	 * 获取数据库对象
	 * @param dbID
	 * @param typeID
	 * @return
	 */
	List<DefaultForm>getObjList(int dbID,String typeID);
	/**
	 * 获取应用系统信息
	 * @param appID
	 * @return
	 */
	ApplicationForm getAppInfo(int appID);
	/**
	 * 获取应用接口信息
	 * @param interID
	 * @return
	 */
	AppInterForm getInterInfo(int interID);
	/**
	 * 获取接口方法信息
	 * @param methodID
	 * @return
	 */
	InterMethodForm getMethodInfo(int methodID);
	/**
	 * 获取应用功能信息
	 * @param funcID
	 * @return
	 */
	AppFuncForm getAppFuncInfo(int funcID);
	/**
	 * 获取方法参数信息
	 * @param paramID
	 * @return
	 */
	MethodParamForm getMethodParamInfo(int paramID);
	/**
	 * 获取存储过程参数信息
	 * @param paramID
	 * @return
	 */
	ProcedureParamForm getProParamInfo(int paramID);
	/**
	 * 获取数据库信息
	 * @param dbID
	 * @return
	 */
	DataBaseForm getDbInfo(int dbID);
	/**
	 * 获取数据库对象信息
	 * @param objID
	 * @return
	 */
	DataBaseObjForm getDbObjInfo(int objID);
	/**
	 * 获取对象字段信息
	 * @param colID
	 * @return
	 */
	DbObjColForm getObjColInfo(int colID);
	/**
	 * 获取数据字典信息
	 * @param dictID
	 * @return
	 */
	DictForm getDictInfo(int dictID);
	/**
	 * 获取用户角色信息
	 * @param roleID
	 * @return
	 */
	RoleForm getRoleInfo(int roleID);
}
