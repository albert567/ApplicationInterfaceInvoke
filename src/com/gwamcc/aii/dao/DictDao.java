package com.gwamcc.aii.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.DictForm;
/**
 * 数据字典操作接口
 * @author 范大德
 *
 */
public interface DictDao {
	/**
	 * 获取指定类型的有效的数据字典数据
	 * @param dType	数据类型
	 * @return
	 */
	List<DefaultForm>validList(String dType)throws Exception;

	/**
	 * 查询数据字典数据列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	Object getList(DictForm form) throws Exception;

	/**
	 * 编辑数据字典数据
	 * @param form
	 * @return
	 * @throws Exception
	 */
	Object edit(DictForm form)throws Exception;

	/**
	 * 添加数据字典数据
	 * @param form
	 * @return
	 * @throws Exception
	 */
	Object append(DictForm form)throws Exception;

	/**
	 * 使数据有效/失效
	 * @param form
	 * @return
	 * @throws Exception
	 */
	Object remove(DictForm form)throws Exception;

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
	Object download()throws Exception;

}
