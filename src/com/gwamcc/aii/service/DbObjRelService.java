package com.gwamcc.aii.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DbObjRelForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 对象关联操作服务类
 * @author 张亚平
 *
 */
public interface DbObjRelService {
	/**
	 * 获取关联对象名称列表
	 * @return
	 */
	List<DefaultForm>getDbObj(int objID);
	/**
	 * 分页获取关联对象信息列表
	 * @param objRel	查询条件
	 * @param page	分页信息
	 * @return
	 */
	Object getDbObjList(DbObjRelForm objRel,PageForm page);

	/**
	 * 新增一条对象关联信息
	 * @param objRel
	 * @return
	 */
	DefaultForm append(DbObjRelForm objRel);

	/**
	 * 修改一条对象关联信息
	 * @param objRel
	 * @return
	 */
	DefaultForm edit(DbObjRelForm objRel);

	/**
	 * 删除指定关联信息
	 * @param relID	待删除的关联ID
	 * @return
	 */
	DefaultForm remove(int relID);

	Object upload(MultipartFile file) throws Exception;

	Object download() throws Exception;

}
