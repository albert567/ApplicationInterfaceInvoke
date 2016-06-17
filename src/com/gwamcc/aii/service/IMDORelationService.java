package com.gwamcc.aii.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.IMDORelationForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 应用接口方法与数据库对象字段关联关系Action
 * @author 范大德
 *
 */
public interface IMDORelationService {
	/**
	 * 根据对象ID获取方法列表
	 * @param objID
	 * @return
	 */
	List<DefaultForm> getMethodByObjID(int objID);
	/**
	 * 根据方法ID获取数据库对象列表
	 * @param methodID
	 * @return
	 */
	List<DefaultForm> getObjByMeID(int methodID);

	/**
	 * 分页获取关联关系列表
	 * @param form
	 * @param page
	 * @return
	 * @throws Exception
	 */
	PageDataForm getList(IMDORelationForm form, PageForm page)throws Exception;

	/**
	 * 编辑当前关联关系
	 * @param app
	 * @return
	 * @throws Exception
	 */
	Object edit(IMDORelationForm app)throws Exception;

	/**
	 * 添加一条新的关联
	 * @param app
	 * @return
	 * @throws Exception
	 */
	Object append(IMDORelationForm app)throws Exception;

	/**
	 * 使当前关联有效/失效
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	Object remove(IMDORelationForm bean)throws Exception;

	/**
	 * 导入关联关系数据
	 * @param file
	 * @return
	 * @throws Exception
	 */
	Object upload(MultipartFile file)throws Exception;

	/**
	 * 下载关联关系模板
	 * @return
	 * @throws Exception
	 */
	Object download()throws Exception;


}
