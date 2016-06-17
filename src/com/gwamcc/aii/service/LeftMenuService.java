package com.gwamcc.aii.service;

import java.util.List;

import com.gwamcc.aii.forms.DefaultForm;

/**
 * 菜单树对象操作服务类
 * @author 张亚平
 *
 */
public interface LeftMenuService {
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
}
