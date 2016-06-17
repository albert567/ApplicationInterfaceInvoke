package com.gwamcc.aii.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.AppInterForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 应用接口对象操作服务类
 * @author 范大德
 *
 */
public interface AppInterService {
	/**
	 * 分页获取应用接口信息列表
	 * @param form	查询条件
	 * @param page	分页信息
	 * @return
	 */
	PageDataForm getAppInterList(DefaultForm form,PageForm page);
	/**
	 * 获取指定应用所有接口信息列表
	 * @param appId	应用程序Id
	 * @return
	 */
	List<DefaultForm>getInterList(int appId);
	/**
	 * 获取接口类型列表
	 * @return
	 */
	List<DefaultForm>getInterType();
	/**
	 * 获取指定应用程序接口名称列表
	 * @param ApplicationID
	 * @return
	 */
	List<DefaultForm>getInterName(int ApplicationID);
	/**
	 * 修改应用接口
	 * @param app
	 * @return
	 */
	DefaultForm edit(AppInterForm app);
	/**
	 * 新增应用接口
	 * @param app
	 * @return
	 */
	DefaultForm append(AppInterForm app);
	/**
	 * 删除没有子级的接口
	 * @param app
	 * @return
	 */
	DefaultForm remove(int interfaceID);
	/**
	 * 导入数据
	 * @param file
	 * @return
	 */
	Object upload(MultipartFile file)throws Exception;
	/**
	 * 下载导入模板
	 * @return
	 */
	Object download()throws Exception;

}
