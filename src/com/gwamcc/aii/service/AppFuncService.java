package com.gwamcc.aii.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.AppFuncForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 应用功能Action
 * @author 范大德
 *
 */
public interface AppFuncService {

	/**
	 * 分页获取指定查询条件的应用功能列表
	 * @param form	查询条件
	 * @param page	分页信息
	 * @return
	 * @throws Exception
	 */
	PageDataForm getList(AppFuncForm form, PageForm page)throws Exception;

	/**
	 * 获取指定应用程序的应用功能列表
	 * @param applicationID	应用ID
	 * @return
	 * @throws Exception
	 */
	List<DefaultForm> getFuncList(int applicationID)throws Exception;

	/**
	 * 获取指定应用程序的应用功能名称列表
	 * @param applicationID
	 * @return
	 * @throws Exception
	 */
	List<DefaultForm> getName(int applicationID)throws Exception;

	/**
	 * 编辑当前应用功能
	 * @param app
	 * @return
	 * @throws Exception
	 */
	Object edit(AppFuncForm app) throws Exception;

	/**
	 * 添加新的应用功能
	 * @param app
	 * @return
	 * @throws Exception
	 */
	Object append(AppFuncForm app)throws Exception;

	/**
	 * 使当前的应用功能有效/失效
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	Object remove(AppFuncForm bean)throws Exception;

	/**
	 * 导入应用功能数据
	 * @param file
	 * @return
	 * @throws Exception
	 */
	Object upload(MultipartFile file)throws Exception;

	/**
	 * 下载应用功能数据模板
	 * @return
	 * @throws Exception
	 */
	Object download()throws Exception;
}
