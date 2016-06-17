package com.gwamcc.aii.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.InterProcedureForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 存储过程数据库接口操作接口
 * @author 张亚平
 *
 */
public interface InterProcedureDao {
	/**
	 * 获取数据库对象名称列表
	 * @param interID 接口id
	 * @return
	 */
	List<DefaultForm>getDbObj(int interID);
	/**
	 * 获取数据库对象列表
	 * @param interID 接口id
	 * @return
	 */
	List<DefaultForm>getObjList(int interID);
	/**
	 * 分页获取数据库对象信息列表
	 * @param interPro	查询条件
	 * @param page	分页信息
	 * @return
	 */
	Object getDbObjList(InterProcedureForm interPro,PageForm page);
	/**
	 * 新增一条数据库接口信息
	 * @param interPro
	 * @return
	 */
	DefaultForm append(InterProcedureForm interPro);
	/**
	 * 修改一条数据库接口信息
	 * @param interPro
	 * @return
	 */
	DefaultForm edit(InterProcedureForm interPro);
	/**
	 * 删除指定的数据库接口信息
	 * @param dbObjID	待删除的数据库对象
	 * @return
	 */
	DefaultForm remove(int interProID);

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
