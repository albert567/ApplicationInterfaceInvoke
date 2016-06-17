package com.gwamcc.aii.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.AppFuncForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 应用功能数据操作
 * @author 范大德
 *
 */
public interface AppFuncDao {

	/**
	 *	保存应用功能
	 * @param app
	 * @return
	 * @throws Exception
	 */
	DefaultForm edit(AppFuncForm app) throws Exception;

	/**
	 * 添加应用功能
	 * @param app
	 * @return
	 * @throws Exception
	 */
	DefaultForm append(AppFuncForm app) throws Exception;

	/**
	 * 使应用功能有效/失效
	 * @param form
	 * @return
	 * @throws Exception
	 */
	DefaultForm remove(AppFuncForm form) throws Exception;

	/**
	 * 分页查询应用功能
	 * @param form
	 * @param page
	 * @return
	 * @throws Exception
	 */
	PageDataForm getList(AppFuncForm form, PageForm page) throws Exception;

	/**
	 * 获取指定应用的应用功能列表
	 * @param applicationID
	 * @return
	 * @throws Exception
	 */
	List<DefaultForm> getFuncList(int applicationID) throws Exception;

	/**
	 * 获取指定应用的应用功能名称列表
	 * @param applicationID
	 * @return
	 * @throws Exception
	 */
	List<DefaultForm> getName(int applicationID) throws Exception;

	/**
	 * 导入数据
	 * @param file
	 * @return
	 * @throws Exception
	 */
	Object upload(MultipartFile file) throws Exception;

	/**
	 * 下载模板
	 * @return
	 * @throws Exception
	 */
	Object download() throws Exception;
}
