package com.gwamcc.aii.service;

import java.util.List;

import com.gwamcc.aii.forms.AppFuncCodeForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 应用功能源码关联Action
 * @author 张亚平
 *
 */
public interface AppFuncCodeService {

	/**
	 *	保存应用功能源码关联功能
	 * @param funcID
	 * @param nodes
	 * @return
	 * @throws Exception
	 */
	DefaultForm save(int funcID, int[] nodes) throws Exception;

	/**
	 * 分页查询应用功能源码关联
	 * @param appID
	 * @param funcID
	 * @param parentID
	 * @return
	 * @throws Exception
	 */
	List<DefaultForm> getList(int appID, int funcID, int parentID) throws Exception;
	
	/**
	 * 查询应用功能源码关联
	 * @param form
	 * @param page
	 * @return
	 * @throws Exception
	 */
	PageDataForm getCodeList(AppFuncCodeForm form, PageForm page) throws Exception;
	
	/**
	 * 删除应用功能源码关联
	 * @param form
	 * @return
	 * @throws Exception
	 */
	DefaultForm remove(AppFuncCodeForm form) throws Exception;
}
