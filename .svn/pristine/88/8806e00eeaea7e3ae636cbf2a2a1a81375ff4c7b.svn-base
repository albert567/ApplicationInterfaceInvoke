package com.gwamcc.aii.dao;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.AppDbForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 应用数据库关联数据操作
 * @author 张亚平
 *
 */
public interface AppDbDao {

	/**
	 *	保存应用数据库关联功能
	 * @param appDb
	 * @return
	 * @throws Exception
	 */
	DefaultForm edit(AppDbForm appDb) throws Exception;

	/**
	 * 添加应用数据库关联功能
	 * @param appDb
	 * @return
	 * @throws Exception
	 */
	DefaultForm append(AppDbForm appDb) throws Exception;

	/**
	 * 删除应用数据库关联
	 * @param appDb
	 * @return
	 * @throws Exception
	 */
	DefaultForm remove(AppDbForm appDb) throws Exception;

	/**
	 * 分页查询应用数据库关联
	 * @param form
	 * @param page
	 * @return
	 * @throws Exception
	 */
	PageDataForm getList(AppDbForm form, PageForm page) throws Exception;
	
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
