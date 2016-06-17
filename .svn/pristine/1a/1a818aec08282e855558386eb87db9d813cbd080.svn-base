package com.gwamcc.aii.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.InterMethodForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;

/**
 * 接口方法数据操作接口
 * @author 张亚平
 *
 */
public interface MethodDao {
	/**
	 * 获取指定接口的方法列表给下拉框
	 * @param interfaceID 接口id
	 * @return 查询结果
	 */
	List<DefaultForm>getMethodName(int interfaceID);
	/**
	 * 获取指定接口的方法列表给对话框
	 * @param interfaceID 接口id
	 * @return 查询结果
	 */
	List<DefaultForm>getMethodList(int interfaceID);
	/**
	 * 获取所有方法类型
	 * @return 查询结果
	 */
	List<DefaultForm>getMethodType();
	/**
	 * 分页获取接口方法关联列表
	 * @param interMethod 查询条件
	 * @param page 分页信息
	 * @return 查询结果
	 */
	PageDataForm getMethodList(DefaultForm interMethod,PageForm page);
	/**
	 * 变更接口方法对象
	 * @param iterMethod 接口方法对象
	 * @return
	 */
	DefaultForm edit(InterMethodForm interMethod);
	/**
	 * 新增接口方法对象
	 * @param iterMethod
	 * @return
	 */
	DefaultForm append(InterMethodForm interMethod);
	/**
	 * 删除接口方法对象
	 * @param methodID
	 * @return
	 */
	DefaultForm remove(int methodID);
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
