package com.gwamcc.aii.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.ApplicationForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 应用程序数据操作接口
 * @author 范大德
 *
 */
public interface ApplicationDao {
	/**
	 * 获取应用名称列表
	 * @return
	 */
	List<DefaultForm>getApp();
	/**
	 * 分页获取应用信息列表
	 * @param app	查询条件
	 * @param page	分页信息
	 * @return
	 */
	Object getAppList(ApplicationForm app,PageForm page);

	/**
	 * 新增一条应用程序信息
	 * @param app
	 * @return
	 */
	DefaultForm append(ApplicationForm app);

	/**
	 * 修改一条应用程序信息
	 * @param app
	 * @return
	 */
	DefaultForm edit(ApplicationForm app);

	/**
	 * 删除指定的应用系统信息
	 * @param applicationID	待删除的应用系统
	 * @return
	 */
	DefaultForm remove(int applicationID);
	/**
	 * 获取应用类型
	 * @return
	 */
	Object appType();
	/**
	 * 导入数据
	 * @param file
	 * @return
	 */
	Object upload(MultipartFile file) throws Exception;
	/**
	 * 下载模板
	 * @return
	 */
	Object download() throws Exception;
	/**
	 * 数据展示
	 * @param appID
	 * @return
	 */
	List<DefaultForm> getForceData(int appID);


}
