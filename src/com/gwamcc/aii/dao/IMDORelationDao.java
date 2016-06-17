package com.gwamcc.aii.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.IMDORelationForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 应用接口方法与数据库对象字段关联数据操作类
 * @author 范大德
 *
 */
public interface IMDORelationDao {
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
	 * 分页查询关联关系
	 * @param form
	 * @param page
	 * @return
	 * @throws Exception
	 */
	PageDataForm getList(IMDORelationForm form, PageForm page)throws Exception;

	/**
	 * 编辑关联信息
	 * @param app
	 * @return
	 * @throws Exception
	 */
	Object edit(IMDORelationForm app)throws Exception;

	/**
	 * 添加关联信息
	 * @param app
	 * @return
	 * @throws Exception
	 */
	Object append(IMDORelationForm app)throws Exception;

	/**
	 * 使数据有效/失效
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	Object remove(IMDORelationForm bean)throws Exception;

	/**
	 * 导入数据
	 * @param file
	 * @return
	 * @throws Exception
	 */
	Object upload(MultipartFile file)throws Exception;

	/**
	 * 下载模板
	 * @return
	 * @throws Exception
	 */
	Object download()throws Exception;

}
