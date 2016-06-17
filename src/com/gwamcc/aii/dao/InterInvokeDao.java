package com.gwamcc.aii.dao;


import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.InterInvokeForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 应用接口调用数据操作接口
 * @author 张亚平
 *
 */
public interface InterInvokeDao {
	/**
	 * 分页获取应用接口调用关联列表
	 * @param interInvoke 查询条件
	 * @param page 分页信息
	 * @return 查询结果
	 */
	PageDataForm getInterInvokeList(DefaultForm interInvoke,PageForm page);

	/**
	 * 变更应用接口调用对象
	 * @param interInvoke 应用接口调用对象
	 * @return
	 */
	DefaultForm edit(InterInvokeForm interInvoke);
	/**
	 * 新增应用接口调用对象
	 * @param interInvoke 应用接口调用对象
	 * @return
	 */
	DefaultForm append(InterInvokeForm interInvoke);
	/**
	 * 删除应用接口调用对象
	 * @param interInvokeID
	 * @return
	 */
	DefaultForm remove(int interInvokeID);
	
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
