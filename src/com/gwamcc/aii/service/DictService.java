package com.gwamcc.aii.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.DictForm;

/**
 * 数据字典对象操作服务类
 * @author 范大德
 *
 */
public interface DictService {
	/**
	 * 获取指定类型的有效的数据字典数据
	 * @param dType	数据类型
	 * @return
	 */
	List<DefaultForm>validList(String dType) throws Exception;

	/**
	 * 获取指定条件的数据字典列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	Object getList(DictForm form) throws Exception;

	/**
	 * 编辑当前数据字典信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	Object edit(DictForm form) throws Exception;

	/**
	 * 新增一条新的数据字典
	 * @param form
	 * @return
	 * @throws Exception
	 */
	Object append(DictForm form) throws Exception;

	/**
	 * 使数据字典数据有效/失效
	 * @param form
	 * @return
	 * @throws Exception
	 */
	Object remove(DictForm form) throws Exception;

	/**
	 * 导入数据字典模板数据
	 * @param file
	 * @return
	 * @throws Exception
	 */
	Object upload(MultipartFile file) throws Exception;

	/**
	 * 下载数据导入模板
	 * @return
	 * @throws Exception
	 */
	Object download() throws Exception;

}
