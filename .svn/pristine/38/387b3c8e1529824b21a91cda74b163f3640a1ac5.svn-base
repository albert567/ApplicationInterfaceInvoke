package com.gwamcc.aii.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DbObjRelForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 关联操作接口
 * @author 张亚平
 *
 */
public interface DbObjRelDao {
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
