package com.gwamcc.aii.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.InterMethodForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 接口方法对象操作服务类
 * @author 张亚平
 *
 */
public interface MethodService {
	/**
	 * 获取指定应用接口的方法名称列表给下拉框
	 * @param interfaceID 接口id
	 * @return 查询结果
	 */
	public List<DefaultForm> getMethodName(int interfaceID);
	/**
	 * 获取指定应用接口的方法列表
	 * @param interfaceID 接口id
	 * @return 查询结果
	 */
	public List<DefaultForm>getMethodList(int interfaceID);
	/**
	 * 获取所有接口方法类型
	 * @return
	 */
	public List<DefaultForm>getMethodType();
	/**
	 * 分页获取接口方法信息列表
	 * @param interMethod	查询条件
	 * @param page	分页信息
	 * @return  查询结果
	 */
	public PageDataForm getMethodList(DefaultForm interMethod,PageForm page);

	/**
	 * 修改接口方法
	 * @param interMethod 接口方法对象
	 * @return
	 */
	DefaultForm edit(InterMethodForm interMethod);
	/**
	 * 新增接口方法
	 * @param interMethod 接口方法对象
	 * @return
	 */
	DefaultForm append(InterMethodForm interMethod);
	/**
	 * 删除没有子级的接口方法
	 * @param methodID 接口方法对象ID
	 * @return
	 */
	DefaultForm remove(int methodID);

	Object upload(MultipartFile file)throws Exception;

	Object download()throws Exception;


}
