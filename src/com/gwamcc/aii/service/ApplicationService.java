package com.gwamcc.aii.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.ApplicationForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 应用程序对象操作服务类
 * @author 范大德
 *
 */
public interface ApplicationService {
	/**
	 * 获取所有应用程序名称列表
	 * @return
	 */
	public List<DefaultForm> getApp();
	/**
	 * 分页获取应用程序列表信息
	 * @param app	查询条件
	 * @param page	分页信息
	 * @return
	 */
	public Object getAppList(ApplicationForm app,PageForm page);

	/**
	 * 新增一条应用程序信息
	 * @param app
	 * @return
	 */
	public DefaultForm append(ApplicationForm app);

	/**
	 * 修改一条应用程序信息
	 * @param app
	 * @return
	 */
	public DefaultForm edit(ApplicationForm app);
	/**
	 * 删除没有子级的应用程序
	 * @param app
	 * @return
	 */
	public DefaultForm remove(int applicationID);
	/**
	 * 获取应用类型
	 * @return
	 */
	public Object appType();
	/**
	 * 导入数据
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public Object upload(MultipartFile file) throws Exception;
	/**
	 * 下载导入数据模板
	 * @return
	 * @throws Exception
	 */
	public Object download() throws Exception;
	List<DefaultForm> getForceData(int appID);

}
