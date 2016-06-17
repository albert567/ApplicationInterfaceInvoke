package com.gwamcc.aii.dao;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.AppFuncObjForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 应用功能数据库对象关联数据操作
 * @author 张亚平
 *
 */
public interface AppFuncObjDao {

	/**
	 *	保存应用功能数据库对象关联功能
	 * @param funcObj
	 * @return
	 * @throws Exception
	 */
	DefaultForm edit(AppFuncObjForm funcObj) throws Exception;

	/**
	 * 添加应用功能数据库对象关联功能
	 * @param funcObj
	 * @return
	 * @throws Exception
	 */
	DefaultForm append(AppFuncObjForm funcObj) throws Exception;

	/**
	 * 删除应用功能数据库对象关联
	 * @param funcObj
	 * @return
	 * @throws Exception
	 */
	DefaultForm remove(AppFuncObjForm funcObj) throws Exception;

	/**
	 * 分页查询应用功能数据库对象关联
	 * @param form
	 * @param page
	 * @return
	 * @throws Exception
	 */
	PageDataForm getList(AppFuncObjForm form, PageForm page) throws Exception;

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
