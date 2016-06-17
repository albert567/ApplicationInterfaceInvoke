package com.gwamcc.aii.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DbObjColForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 字段操作服务类
 * @author 张亚平
 *
 */
public interface DbObjColService {

	/**
	 * 获取字段名称列表
	 * @param objID
	 * @return
	 */
	List<DefaultForm>getDbCol(int objID);
	/**
	 * 获取字段源数据列表
	 * @param colID
	 * @return
	 */
	List<DefaultForm>getColSrcList(int colID);
	/**
	 * 获取字段列表
	 * @return
	 */
	List<DefaultForm>getDbColList(int objID);
	/**
	 * 分页获取字段信息列表
	 * @param dbCol	查询条件
	 * @param page	分页信息
	 * @return
	 */
	Object getDbColList(DbObjColForm dbCol,PageForm page);

	/**
	 * 新增一条字段信息
	 * @param dbCol
	 * @return
	 */
	DefaultForm append(DbObjColForm dbCol);

	/**
	 * 修改一条字段信息
	 * @param dbCol
	 * @return
	 */
	DefaultForm edit(DbObjColForm dbCol);

	/**
	 * 删除指定字段信息
	 * @param colID	待删除的字段ID
	 * @return
	 */
	DefaultForm remove(int colID);
	/**
	 * 获取字段类型
	 * @return
	 */
	Object colType();

	Object upload(MultipartFile file) throws Exception;

	Object download() throws Exception;

}
