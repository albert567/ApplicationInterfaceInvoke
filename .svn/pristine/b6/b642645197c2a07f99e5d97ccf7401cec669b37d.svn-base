package com.gwamcc.aii.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DbObjColForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 字段数据操作接口
 * @author 张亚平
 *
 */
public interface DbObjColDao {
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
