package com.gwamcc.aii.service;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.AppDbForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 应用数据库关联Action
 * @author 张亚平
 *
 */
public interface AppDbService {

	/**
	 * 分页获取指定查询条件的应用数据库关联列表
	 * @param form	查询条件
	 * @param page	分页信息
	 * @return
	 * @throws Exception
	 */
	PageDataForm getList(AppDbForm form, PageForm page)throws Exception;

	/**
	 * 编辑当前应用数据库关联
	 * @param app
	 * @return
	 * @throws Exception
	 */
	Object edit(AppDbForm app) throws Exception;

	/**
	 * 添加新的应用数据库关联
	 * @param app
	 * @return
	 * @throws Exception
	 */
	Object append(AppDbForm app)throws Exception;

	/**
	 * 删除应用数据库关联
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	Object remove(AppDbForm bean)throws Exception;

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
