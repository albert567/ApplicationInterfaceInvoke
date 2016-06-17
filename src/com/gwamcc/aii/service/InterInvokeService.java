package com.gwamcc.aii.service;


import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.InterInvokeForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 应用接口调用对象操作服务类
 * @author 张亚平
 *
 */
public interface InterInvokeService {
	/**
	 * 分页获取应用接口调用信息列表
	 * @param interInvoke	查询条件
	 * @param page	分页信息
	 * @return  查询结果
	 */
	public PageDataForm getInterInvokeList(DefaultForm interInvoke,PageForm page);

	/**
	 * 修改应用接口调用
	 * @param interInvoke 应用接口调用对象
	 * @return
	 */
	DefaultForm edit(InterInvokeForm interInvoke);
	/**
	 * 新增应用接口调用
	 * @param interInvoke 应用接口调用对象
	 * @return
	 */
	DefaultForm append(InterInvokeForm interInvoke);
	/**
	 * 删除应用接口调用
	 * @param interInvokeID 应用接口调用对象id
	 * @return
	 */
	DefaultForm remove(int interInvokeID);

	Object upload(MultipartFile file)throws Exception;

	Object download()throws Exception;

}
