package com.gwamcc.aii.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.AppInterForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 应用接口数据操作接口
 * @author 范大德
 *
 */
public interface AppInterDao {
	/**
	 * 分页获取应用程序和接口关联列表
	 * @param form	查询条件
	 * @param page	分页信息
	 * @return	查询结果
	 */
	PageDataForm getAppInterList(DefaultForm form,PageForm page);
	/**
	 * 获取指定应用程序所有的接口列表
	 * @param appId	应用程序id
	 * @return	查询结果
	 */
	List<DefaultForm>getInterList(int appId);
	/**
	 * 获取所有的接口类型
	 * @return	接口类型列表
	 */
	List<DefaultForm>getInterType();
	/**
	 * 获取指定应用程序接口名称列表
	 * @param ApplicationID	应用程序Id
	 * @return	结果列表
	 */
	List<DefaultForm>getInterName(int ApplicationID);
	/**
	 * 变更应用接口对象
	 * @param app
	 * @return
	 */
	DefaultForm edit(AppInterForm app);
	/**
	 * 新增应用接口对象
	 * @param app
	 * @return
	 */
	DefaultForm append(AppInterForm app);
	/**
	 * 删除应用接口对象
	 * @param interfaceID
	 * @return
	 */
	DefaultForm remove(int interfaceID);
	/**
	 * 下载模板
	 * @return
	 */
	Object download()throws Exception;
	/**
	 * 导入数据
	 * @param file
	 * @return
	 */
	Object upload(MultipartFile file)throws Exception;

}
