package com.gwamcc.aii.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DbColSrcForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 字段源数据操作服务类
 * @author 张亚平
 *
 */
public interface DbColSrcService {
	/**
	 * 获取字段源数据名称列表
	 * @return
	 */
	List<DefaultForm>getColSrc(int colID);

	/**
	 * 分页获取字段源数据信息列表
	 * @param colSrc	查询条件
	 * @param page	分页信息
	 * @return
	 */

	Object getColSrcList(DbColSrcForm colSrc,PageForm page);

	/**
	 * 新增一条字段源数据信息
	 * @param colSrc
	 * @return
	 */
	DefaultForm append(DbColSrcForm colSrc);

	/**
	 * 修改一条字段源数据信息
	 * @param colSrc
	 * @return
	 */
	DefaultForm edit(DbColSrcForm colSrc);

	/**
	 * 删除指定字段源数据信息
	 * @param id 待删除的关联ID
	 * @return
	 */
	DefaultForm remove(int id);

	Object upload(MultipartFile file) throws Exception;

	Object download() throws Exception;

}
